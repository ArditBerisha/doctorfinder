package com.temadiplomes.doctorfinder.app.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.temadiplomes.doctorfinder.entity.ControlType;
import com.temadiplomes.doctorfinder.entity.Users;
import com.temadiplomes.doctorfinder.security.UsersServiceImpl;
import com.temadiplomes.doctorfinder.service.ControlTypeServiceImpl;

@Controller
@RequestMapping("/admin/controlType")
public class ControlTypeController {

	@Autowired
	private ControlTypeServiceImpl controlTyleService;
	
	@Autowired
	private UsersServiceImpl userService;
	
	@GetMapping("/list")
	public String controlTypesList(Model theModel) {
		return findPaginated(1, 5, "name", "asc", theModel);
	}
	
	@GetMapping("/list/page")
	public String findPaginated(@RequestParam("pageNo") int pageNo,
			@RequestParam("pageSize") int pageSize,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, 
			Model model) {
		
		Page<ControlType> page = controlTyleService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<ControlType> listCT = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listCT", listCT);
		model.addAttribute("userDetails", currentUser().getUsersDetail());
		
		return "admin/controlType/control-type-list";
	}
	
	public Users currentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Users user = userService.findByUsername(userDetails.getUsername());
		return user;
	}
	
}
