package com.temadiplomes.doctorfinder.app.management.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

import com.temadiplomes.doctorfinder.entity.Authorities;
import com.temadiplomes.doctorfinder.entity.SpecialitiesCategory;
import com.temadiplomes.doctorfinder.entity.Users;
import com.temadiplomes.doctorfinder.security.UsersServiceImpl;
import com.temadiplomes.doctorfinder.service.SpecialitiesCategoryServiceImpl;

import enums.Status;

@Controller
@RequestMapping("/admin/specialities")
public class SpecialitiesController {

	@Autowired
	private SpecialitiesCategoryServiceImpl specService;
	
	@Autowired
	private UsersServiceImpl userService;

	@GetMapping("/list")
	public String specialitiesList(Model model) {
		return findPaginated(1, 5, "name", "asc", model);
	}
	
	

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		boolean isPublished = false;

		SpecialitiesCategory speciality = new SpecialitiesCategory();

		List<SpecialitiesCategory> specList = specService.findAll();

		model.addAttribute("specList", specList);

		model.addAttribute("speciality", speciality);

		model.addAttribute("isPublished", isPublished);
		
		model.addAttribute("userDetails", currentUser().getUsersDetail());

		return "admin/specialities/specialities-form";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("speciality") SpecialitiesCategory theSpeciality,
			@RequestParam("specId") int specId) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Users user = userService.findByUsername(userDetails.getUsername());
		long millis = System.currentTimeMillis();  
		
		Date date = new java.sql.Date(millis);  
		
		SpecialitiesCategory theSpec = null;
		SpecialitiesCategory parent = null;
		SpecialitiesCategory result = specService.findById(theSpeciality.getId());
		
		if(result == null) {
			parent = specService.findById(specId);
			theSpeciality.setSpecCat(parent);
			theSpeciality.setCreatedOnDate(date);
			theSpeciality.setCreatedByUserId(user.getId());
			theSpeciality.setLastModifiedOnDate(date);
			theSpeciality.setLastModifiedByUserId(user.getId());
			theSpeciality.addUser(user);
		} else {
			parent = specService.findById(specId);
			theSpeciality.setSpecCat(parent);
			theSpeciality.setLastModifiedOnDate(date);
			theSpeciality.setLastModifiedByUserId(user.getId());
		}
		
		specService.save(theSpeciality);

		return "redirect:/admin/specialities/list";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("specId") int theId, Model theModel) {

		// Users user = userService.findByUsername(username);
		// List<Authorities> userAuth = (List<Authorities>)user.getAuthorities();
		// List<Authorities> listAuth = authorityService.findAll();
		// List<Authorities> auth = (List<Authorities>) user.getAuthorities();

		// listAuth.removeAll(userAuth);

		// theModel.addAttribute("user", user);
		// theModel.addAttribute("intersectAuth", listAuth);
		// theModel.addAttribute("authId","");
		// theModel.addAttribute("authorities",auth);
		SpecialitiesCategory speciality = specService.findById(theId);
		
		String parentName = specService.findParentName(speciality.getSpecCat().getId());
		
		SpecialitiesCategory parentSpec = specService.findById(speciality.getSpecCat().getId());

		List<SpecialitiesCategory> specList = specService.findAll().stream().filter(spec -> spec.isDeleted() == Status.ACTIVE)
				.collect(Collectors.toList());
		// Stream<SpecialitiesCategory> s = specList.stream().filter(spec ->
		// !spec.isDeleted());
		// specList = s.collect(Collectors.toList());

		theModel.addAttribute("specList", specList);
		theModel.addAttribute("speciality", speciality);
		theModel.addAttribute("parentSpec", parentSpec);
		theModel.addAttribute("userDetails", currentUser().getUsersDetail());
		
		return "admin/specialities/specialities-form";

	}

	@GetMapping("/delete")
	public String delete(@RequestParam("specId") int theId) {
		specService.deleteById(theId);
		return "redirect:/admin/specialities/list";
	}

	@GetMapping("/list/page")
	public String findPaginated(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize,
			@RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir, Model model) {

		Page<SpecialitiesCategory> page = specService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<SpecialitiesCategory> listSpec = page.getContent().stream().filter(spec -> spec.isDeleted() == Status.ACTIVE).collect(Collectors.toList());
				
		// Stream<SpecialitiesCategory> s = listSpec.stream().filter(spec ->
		// !spec.isDeleted());
		// listSpec = s.collect(Collectors.toList());

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listSpec", listSpec);
		model.addAttribute("userDetails", currentUser().getUsersDetail());

		return "admin/specialities/specialities-list";
	}
	
	
	public Users currentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Users user = userService.findByUsername(userDetails.getUsername());
		return user;
	}

}
