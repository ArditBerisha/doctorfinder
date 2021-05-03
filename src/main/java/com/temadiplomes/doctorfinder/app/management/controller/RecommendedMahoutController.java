package com.temadiplomes.doctorfinder.app.management.controller;

import java.util.logging.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.temadiplomes.doctorfinder.entity.RecommendedMahoutTable;
import com.temadiplomes.doctorfinder.entity.Users;
import com.temadiplomes.doctorfinder.security.UsersServiceImpl;
import com.temadiplomes.doctorfinder.service.RecommendedMahoutTableServiceImpl;

@Controller
@RequestMapping("/rating")
public class RecommendedMahoutController {
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	private RecommendedMahoutTableServiceImpl rMTService;
	
	@Autowired
	private UsersServiceImpl userService;
	
	@GetMapping("/save")
	public @ResponseBody String save(@RequestParam("id") int id,@RequestParam("rate") int rate) {
		Users doc = userService.findById(id);
		Users currUser;
		RecommendedMahoutTable rMT = new RecommendedMahoutTable();
		System.out.println(doc.getId());
		System.out.println(rate);
		System.out.println();
		try {
			currUser = currentUser();
		}catch (Exception e){
			currUser = null;
		}
		if(currUser != null) {
			rMT.setDoctor(doc);
			rMT.setUser(currUser);
			rMT.setPreference(rate);
			rMTService.save(rMT);
		}
		return "user/ttt";
	}
	
	public Users currentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Users user = userService.findByUsername(userDetails.getUsername());
		return user;
	}
}
