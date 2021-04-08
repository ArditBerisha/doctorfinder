package com.temadiplomes.doctorfinder.client.app.controller;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.temadiplomes.doctorfinder.entity.Attribute;
import com.temadiplomes.doctorfinder.entity.Certification;
import com.temadiplomes.doctorfinder.entity.ControlType;
import com.temadiplomes.doctorfinder.entity.Users;
import com.temadiplomes.doctorfinder.security.UsersServiceImpl;
import com.temadiplomes.doctorfinder.service.CertificationServiceImpl;

import enums.Status;

@Controller
@RequestMapping("/admin/certification")
public class CertificationController {

	@Autowired
	private CertificationServiceImpl certificationService;
	
	@Autowired
	private UsersServiceImpl userService;
	
	@GetMapping("/list")
	public String certificationList(Model theModel) {
		return findPaginated(1, 5, "titleOfCertification", "asc", theModel);
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("certification") Certification theCert) {

		Certification cert = theCert;
		
		Users currentUser = currentUser();
		
		cert.addUser(currentUser);
		
		certificationService.save(cert);
		
		//currentUser.addCertfication(cert);
		//userService.save(currentUser);

		return "redirect:/admin/certification/list";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		Certification certification = new Certification();
		
		model.addAttribute("certification",certification);
		
		model.addAttribute("userDetails", currentUser().getUsersDetail());
		
		return "user/doctor/certification/certification-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("certId") int theId, Model theModel) {

		Certification certification = certificationService.findById(theId);

		theModel.addAttribute("certification", certification);
		theModel.addAttribute("userDetails", currentUser().getUsersDetail());
		
		return "user/doctor/certification/certification-form";

	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("specId") int theId) {
		certificationService.deleteById(theId);
		return "redirect:/admin/certification/list";
	}
	
	@GetMapping("/list/page")
	public String findPaginated(@RequestParam("pageNo") int pageNo,
			@RequestParam("pageSize") int pageSize,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, 
			Model model) {
		
		Page<Certification> page = certificationService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Certification> certificationList = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("certificationList", certificationList);
		model.addAttribute("userDetails", currentUser().getUsersDetail());
		
		return "user/doctor/certification/certification-list";
	}
	
	public Users currentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Users user = userService.findByUsername(userDetails.getUsername());
		return user;
	}
	
}
