package com.temadiplomes.doctorfinder.client.app.controller;

import java.util.List;

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

import com.temadiplomes.doctorfinder.dao.EducationRepository;
import com.temadiplomes.doctorfinder.entity.Certification;
import com.temadiplomes.doctorfinder.entity.Education;
import com.temadiplomes.doctorfinder.entity.Users;
import com.temadiplomes.doctorfinder.security.UsersServiceImpl;
import com.temadiplomes.doctorfinder.service.EducationServiceImpl;

@Controller
@RequestMapping("/admin/education")
public class EducationController {
	
	@Autowired
	private EducationServiceImpl educationService;
	
	@Autowired
	private UsersServiceImpl userService;
	
	@GetMapping("/list")
	public String certificationList(Model theModel) {
		return findPaginated(1, 5, "program", "asc", theModel);
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("education") Education theEdu) {

		Education education = theEdu;
		
		Users currentUser = currentUser();
		
		education.addUser(currentUser);
		
		educationService.save(education);
		
		//currentUser.addCertfication(cert);
		//userService.save(currentUser);

		return "redirect:/admin/education/list";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		Education education = new Education();
		
		model.addAttribute("education",education);
		
		model.addAttribute("userDetails", currentUser().getUsersDetail());
		
		return "user/doctor/education/education-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("eduId") int theId, Model theModel) {

		Education education = educationService.findById(theId);

		theModel.addAttribute("education", education);
		theModel.addAttribute("userDetails", currentUser().getUsersDetail());
		
		return "user/doctor/education/education-form";

	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("eduId") int theId) {
		educationService.deleteById(theId);
		return "redirect:/admin/education/list";
	}
	
	@GetMapping("/list/page")
	public String findPaginated(@RequestParam("pageNo") int pageNo,
			@RequestParam("pageSize") int pageSize,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, 
			Model model) {
		
		Page<Education> page = educationService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Education> educationList = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("educationList", educationList);
		model.addAttribute("userDetails", currentUser().getUsersDetail());
		
		return "user/doctor/education/education-list";
	}
	
	public Users currentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Users user = userService.findByUsername(userDetails.getUsername());
		return user;
	}
	
}
