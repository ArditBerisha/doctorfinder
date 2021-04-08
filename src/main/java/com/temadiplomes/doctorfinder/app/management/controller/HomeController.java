package com.temadiplomes.doctorfinder.app.management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
public class HomeController {

	@GetMapping("/home")
	public String home() {
		
		return "app-management/dashboard";
	}
	
	@GetMapping("/test")
	public String test() {
		
		return "app-management/test";
	}
	
	
}
