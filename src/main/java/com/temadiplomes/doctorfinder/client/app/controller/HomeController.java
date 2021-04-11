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
import org.springframework.web.bind.annotation.RequestMapping;

import com.temadiplomes.doctorfinder.entity.Users;
import com.temadiplomes.doctorfinder.security.UsersServiceImpl;

import dto.UsersPhoto;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private UsersServiceImpl userService;

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
		model.addAttribute("top4Doctors", top4Doctors);

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

	@GetMapping("/mjekuspec")
	public String getDoctor(Model model) {
		model.addAttribute("mjekuSpec", userService.findById(99));
		Users user = null;
		try {
			user = currentUser();
		} catch (Exception exc) {
			user = null;
		}

		if (user != null) {
			model.addAttribute("userDetails", user.getUsersDetail());
		}
		model.addAttribute("photoPath", currentUser().getUsersDetail());
		return "user/mjekuspec";
	}

}
