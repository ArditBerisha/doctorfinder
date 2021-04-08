package com.temadiplomes.doctorfinder.app.management.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
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
import com.temadiplomes.doctorfinder.entity.Users;
import com.temadiplomes.doctorfinder.security.UsersServiceImpl;
import com.temadiplomes.doctorfinder.service.AuthoritiesServiceImpl;

import dto.UsersPhoto;
import enums.Status;

@Controller
@RequestMapping("/admin/perdoruesit")
public class UsersController {
	
	private Logger logger = Logger.getLogger(getClass().getName());

	@Autowired
	private UsersServiceImpl userService;
	
	@Autowired
	private AuthoritiesServiceImpl authorityService;
	
	public Users currentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Users user = userService.findByUsername(userDetails.getUsername());
		return user;
	}
	//model.addAttribute("userDetailsPhoto", user.getUsersDetail());
	
	@GetMapping("/list")
	public String usersList(Model model) {
		
		return findPaginated(1, 5, "username", "asc", model);
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		Users user = new Users();
		
		model.addAttribute("user", user);
		model.addAttribute("userDetails", currentUser().getUsersDetail());
		
		return "admin/users/user-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("username") String username, Model theModel) {
		
		
		Users user = userService.findByUsername(username);
		List<Authorities> userAuth = (List<Authorities>)user.getAuthorities();
		List<Authorities> listAuth = authorityService.findAll();
		List<Authorities> auth = (List<Authorities>) user.getAuthorities();
		
		listAuth.removeAll(userAuth);
		
		theModel.addAttribute("user", user);
		theModel.addAttribute("intersectAuth", listAuth);
		theModel.addAttribute("authId","");
		theModel.addAttribute("authorities",auth);
		theModel.addAttribute("userDetails", currentUser().getUsersDetail());
		
		return "admin/users/user-form";
		
	}
	
	/*@GetMapping("/save")
	public String save(@ModelAttribute("user") Users theUser, @RequestParam("authName") String authName) {
		
		//int authId = 1;
		userService.addAuthority(theUser.getUsername(), authName);
		
		return "redirect:/admin/perdoruesit/list";
	}*/
	
	
	
	@PostMapping("/save")
	public String save(@ModelAttribute("user") Users theUser, @RequestParam("authName") String authName) {
		//int authId = 1;
		//theUser.setPassword("123465");
		userService.addAuthority(theUser.getUsername(), authName);
		//userService.save(user);
		//userService.save(user);
		
		return "redirect:/admin/perdoruesit/list";
	}
	
	
	
	@PostMapping("/update")
	public String update(@ModelAttribute("theUser") Users theUser) {
		System.out.println("******************************************");
		System.out.println(theUser);
		System.out.println("*******************************************");
		Users user = userService.findById(theUser.getId());
		user.setFirstName(theUser.getFirstName());
		user.setLastName(theUser.getLastName());
		userService.save(user);

		//return "redirect:/admin/perdoruesit/myProfile";
		return "redirect:/user/myProfile";
	}
	
	@GetMapping("/delete/authority")
	public String deleteAuthority(@ModelAttribute("userName") String userName, @RequestParam("authName") String authName) {
		
		userService.deleteAuthFromUser(userName, authName);
		
		return "redirect:/admin/perdoruesit/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("userId") int theId) {
		userService.deleteById(theId);
		return "redirect:/admin/perdoruesit/list";
	}

	@GetMapping("/list/page")
	public String findPaginated(@RequestParam("pageNo") int pageNo,
			@RequestParam("pageSize") int pageSize,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, 
			Model model) {
		
		Page<Users> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Users> listUsers = page.getContent();
		Stream<Users> s = listUsers.stream();
		listUsers = s.collect(Collectors.toList());
		
		model.addAttribute("userDetails", currentUser().getUsersDetail());
		
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalPages", page.getTotalPages());
		
		//model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listUsers", listUsers);
		
		
		return "admin/users/users-list";
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
	
	@GetMapping("/languages")
	public String languages(Model model) {
		model.addAttribute("theUser", currentUser());
		return "shared/my-profile";
	}
}
