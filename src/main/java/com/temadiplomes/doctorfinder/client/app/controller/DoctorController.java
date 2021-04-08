package com.temadiplomes.doctorfinder.client.app.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.temadiplomes.doctorfinder.dao.LanguageRepository;
import com.temadiplomes.doctorfinder.entity.Authorities;
import com.temadiplomes.doctorfinder.entity.Language;
import com.temadiplomes.doctorfinder.entity.OdaEMjekeveKS;
import com.temadiplomes.doctorfinder.entity.Users;
import com.temadiplomes.doctorfinder.entity.UsersDetail;
import com.temadiplomes.doctorfinder.security.UsersServiceImpl;
import com.temadiplomes.doctorfinder.service.LanguageService;
import com.temadiplomes.doctorfinder.service.OdaEMjekeveKSServiceImpl;
import com.temadiplomes.doctorfinder.service.UsersDetailService;

import dto.ConfirmDoctorDTO;
import dto.UserDTO;
import dto.UsersPhoto;
import enums.Status;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
	private UsersServiceImpl usersService;

	@Autowired
	private OdaEMjekeveKSServiceImpl oMKSService;
	
	@Autowired
	private UsersServiceImpl userService;
	
	@Autowired
	private LanguageService langService;

	@Autowired
	private UsersDetailService userDetailService;

	@Autowired
	private PlatformTransactionManager transactionManager;
	

	@PostMapping("/edit/details")
	public String editoFoton(@ModelAttribute("usersPhoto") UsersPhoto thePhoto, Model model) {
		Users user = currentUser();
		String bio = thePhoto.getBiography();
		
		try {
			if(thePhoto.getFile() != null) {
				user.getUsersDetail().setPhotoPath(Base64.getEncoder().encodeToString(thePhoto.getFile().getBytes()));
			}
			if(bio != null) {
				user.getUsersDetail().setBiography(bio);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		usersService.save(user);
		
		//return "redirect:/admin/perdoruesit/myProfile";
		
		return "redirect:/user/myProfile";
	}
	
	@PostMapping("/processConfirmation")
	public String scheduleRelease(@ModelAttribute("confirmDoctorDTO") ConfirmDoctorDTO theDoctor, Model model) {
		TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
		try {

			Users user = new Users();
			user.setFirstName(theDoctor.getFirstName());
			user.setLastName(theDoctor.getLastName());
			user.setEmail(theDoctor.getEmail());
			user.setUsername(theDoctor.getUsername());
			user.setPassword(theDoctor.getPassword());

			UsersDetail userDetail = new UsersDetail();
			String fileName = StringUtils.cleanPath(theDoctor.getFile().getOriginalFilename());
			if (fileName.contains("..")) {
				System.out.println("Not a valid file");
			}

			try {
				userDetail.setPhotoPath(Base64.getEncoder().encodeToString(theDoctor.getFile().getBytes()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			user.setUsersDetail(userDetail);
			// me ja vendos rolin e mjekut
			usersService.save(user);

			OdaEMjekeveKS oEMK = new OdaEMjekeveKS();
			oEMK.setEmri(user.getFirstName());
			oEMK.setMbiemri(user.getLastName());
			oEMK.setNrAnetarit(theDoctor.getNrAnetarit());
			oEMK.setIdentifyNo(theDoctor.getNrIdentifikues());
			oEMK.setLicenceNO(theDoctor.getNrLicences());

			oMKSService.save(oEMK);

			transactionManager.commit(transactionStatus);
			// show login page//ose redirect tek faqja qe ka me ju paraqit mjekut
		} catch (RuntimeException ex) {
			// Rollback if transaction failed
			transactionManager.rollback(transactionStatus);
			// show failed create a profle

		}
		List<UsersDetail> uD = userDetailService.findAll();

		model.addAttribute("uD", uD);

		return "/user/doctor/uploadview";
	}

	@GetMapping("/img")
	public String showImg(Model model) {
		List<UsersDetail> uD = userDetailService.findAll();

		model.addAttribute("uD", uD);

		return "/user/doctor/uploadview";
	}

	@GetMapping("/confirm")
	public String confirmDoctor(Model theModel) {
		ObjectMapper mapper = new ObjectMapper();
		String listUsers = null;
		String listOMKS = null;
		try {
			listUsers = mapper.writeValueAsString(usersService.findAll().stream()
					.filter(user -> user.getEnabled() == Status.ACTIVE).collect(Collectors.toList()));
			listOMKS = mapper.writeValueAsString(oMKSService.findAll());

			// System.out.println(listOMKS);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// listUsers = listUsers.replace("[", "\'");
		// listUsers = listUsers.replace("[", "\'");

		// List<Users> listUserss = usersService.findAll().stream().filter(user ->
		// user.getEnabled() == Status.ACTIVE).collect(Collectors.toList());
		// List<OdaEMjekeveKS> listOMKSs = oMKSService.findAll();
		theModel.addAttribute("listUsers", listUsers);
		theModel.addAttribute("listOMKS", listOMKS);
		theModel.addAttribute("confirmDoctorDTO", new ConfirmDoctorDTO());

		return "user/doctor/confirm-doctor";
	}
	
	@GetMapping("/languages")
	public String languages(Model model) {
		
		Users user = currentUser();
		List<Language> toDisplay = langService.findAll();
		toDisplay.removeAll(langService.findByUsers(user));
		
	
		
		model.addAttribute("user", user);
		model.addAttribute("userDetails", toDisplay);
		model.addAttribute("userPhoto",currentUser().getUsersDetail());
		model.addAttribute("userLang", langService.findByUsers(user));
		
		return "/user/doctor/languages/languages";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("user") Users theUser, @RequestParam("langCode") String langCode) {
		Users user = currentUser();
		
		Language language = langService.findByLanguageCode(langCode);
		language.addUser(user);

		langService.save(language);
	
		
		return "redirect:/doctor/languages";
	}
	
	@GetMapping("/delete/language")
	public String deleteAuthority(@ModelAttribute("userId") int userId, @RequestParam("langCode") String langCode) {
		
		langService.deleteLanguageFromUser(userId, langCode);
		
		return "redirect:/doctor/languages";
	}
	
	public Users currentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Users user = userService.findByUsername(userDetails.getUsername());
		return user;
	}

}
