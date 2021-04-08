package com.temadiplomes.doctorfinder.app.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DemoController {
	
	@GetMapping("/")
	public String showHome() {
		
		return "home";
	}
	
	// add request mapping for /leaders

	@GetMapping("/leaders")
	public String showLeaders() {
		
		return "leaders";
	}
	
	// add request mapping for /systems
	
	@GetMapping("/systems")
	public String showSystems() {
		
		return "systems";
	}
	
	@GetMapping("/home")
	public String home() {
		
		return "app-management/dashboard";
	}
	
	@GetMapping("/test")
	public String test() {
		
		return "app-management/oda-mjekeve/oda-mjekeve-list";
	}
	
	
		

	
}










