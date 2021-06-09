package com.temadiplomes.doctorfinder.client.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.temadiplomes.doctorfinder.entity.Language;
import com.temadiplomes.doctorfinder.entity.SpecialitiesCategory;
import com.temadiplomes.doctorfinder.entity.Users;
import com.temadiplomes.doctorfinder.security.UsersServiceImpl;
import com.temadiplomes.doctorfinder.service.SpecialitiesCategoryService;

import dto.UsersPhoto;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private UsersServiceImpl userService;
	
	@Autowired
	private SpecialitiesCategoryService specialitiesService;

	@GetMapping("/")
	public String home(Model model) {
		Users user = null;
		try {
			user = currentUser();
		} catch (Exception exc) {
			user = null;
		}

		if (user != null) {
			model.addAttribute("userDetails", user.getUsersDetail());
			model.addAttribute("photoPath", currentUser().getUsersDetail());
		}

		List<Users> top4Doctors = userService.findTop4Doctors(PageRequest.of(0, 4));
		List<SpecialitiesCategory> specs = specialitiesService.findAll();
		model.addAttribute("top4Doctors", top4Doctors);
		model.addAttribute("specialities",specs);
		
		for(SpecialitiesCategory spec : specs) {
			System.out.println(spec.getName());
		}

		return "/user/home/home";
	}

	public Users currentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getPrincipal() == "") {
			return null;
		}
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		Users user = userService.findByUsername(userDetails.getUsername());

		if (user == null) {
			return null;
		}
		return user;
	}

	@GetMapping("/myProfile")
	public String myProfile(Model model) {
		Users user = currentUser();
		UsersPhoto uPhoto = new UsersPhoto();
		uPhoto.setBiography(user.getUsersDetail().getBiography());
		model.addAttribute("userDetailsPhoto", currentUser().getUsersDetail());
		model.addAttribute("theUser", currentUser());
		model.addAttribute("usersPhoto", uPhoto);

		return "shared/my-profile";
	}

	@GetMapping("/details")
	public String getDoctor(@RequestParam("docId") int docId,Model model) {
		model.addAttribute("mjekuSpec", userService.findById(docId));
		Users user = null;
		try {
			user = currentUser();
		} catch (Exception exc) {
			user = null;
		}

		if (user != null) {
			model.addAttribute("userDetails", user.getUsersDetail());
		}
		//model.addAttribute("photoPath", currentUser().getUsersDetail());
		return "user/mjekuspec";
	}
	
	@PostMapping("/doctors-by-category")
	public String doctors(@RequestParam("spe") String specName, Model model) {
		Users user = currentUser();
		
		SpecialitiesCategory speciality = specialitiesService.findByName(specName);
		
		user = null;
		try {
			user = currentUser();
		} catch (Exception exc) {
			user = null;
		}

		if (user != null) {
			model.addAttribute("userDetails", user.getUsersDetail());
			model.addAttribute("photoPath", currentUser().getUsersDetail());
		}
		
		SpecialitiesCategory category = specialitiesService.findByName(specName);
		List<Users> doctorsByCategory = userService.findBySpecCategory(category);

		List<SpecialitiesCategory> specs = specialitiesService.findAll();
		
		model.addAttribute("top4Doctors", doctorsByCategory);
		model.addAttribute("specialities",specs);
		
		for(SpecialitiesCategory spec : specs) {
			System.out.println(spec.getName());
		}

		return "/user/home/home";
		
	}
	

}
