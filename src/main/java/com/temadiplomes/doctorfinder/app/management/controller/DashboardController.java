package com.temadiplomes.doctorfinder.app.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.temadiplomes.doctorfinder.entity.Users;
import com.temadiplomes.doctorfinder.security.UsersServiceImpl;

@Controller
@RequestMapping("/admin/dashboard")
public class DashboardController {
	
	@Autowired
	private UsersServiceImpl userService;
	
	@GetMapping("/")
	public String dashboard(Model model) {
		model.addAttribute("userDetails", currentUser().getUsersDetail());
		return "admin/dashboard/dashboard";
	}

	
	public Users currentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Users user = userService.findByUsername(userDetails.getUsername());
		return user;
	}
}
