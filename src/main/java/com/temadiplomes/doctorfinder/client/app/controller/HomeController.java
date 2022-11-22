package com.temadiplomes.doctorfinder.client.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.temadiplomes.doctorfinder.entity.Users;
import com.temadiplomes.doctorfinder.security.UsersServiceImpl;
import com.temadiplomes.doctorfinder.service.AuthoritiesService;

import dto.UsersPhoto;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private UsersServiceImpl userService;
	
	@Autowired
	private AuthoritiesService authService;

	
	private final boolean ShowInFilter = true;

	@GetMapping("/")
	public String home(Model model) {
		Users user = null;
		try {
			user = currentUser();
		} catch (Exception exc) {
			user = null;
		}

		if (user != null) {
//			model.addAttribute("userDetails", user.getUsersDetail());
//			model.addAttribute("photoPath", currentUser().getUsersDetail());
		}

		return "/user/home/home";
		//return findPaginated(1, 100, "username", "asc", model);
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
//		uPhoto.setBiography(user.getUsersDetail().getBiography());
//		model.addAttribute("userDetailsPhoto", currentUser().getUsersDetail());
		model.addAttribute("theUser", currentUser());
		model.addAttribute("usersPhoto", uPhoto);

		return "shared/my-profile";
	}

	@GetMapping("/details")
	public String getDoctor(@RequestParam("docId") int docId,Model model) {
		model.addAttribute("mjekuSpec", userService.findById(docId));
		Users user = null;
		try {
			user = currentUser();
		} catch (Exception exc) {
			user = null;
		}

		if (user != null) {
//			model.addAttribute("userDetails", user.getUsersDetail());
		}
		//model.addAttribute("photoPath", currentUser().getUsersDetail());
		return "user/mjekuspec";
	}
	
	@PostMapping("/doctors-by-category")
	public String doctors(@RequestParam("spe") String specName, Model model) {
		Users user = currentUser();
		

		user = null;
		try {
			user = currentUser();
		} catch (Exception exc) {
			user = null;
		}

		if (user != null) {
//			model.addAttribute("userDetails", user.getUsersDetail());
//			model.addAttribute("photoPath", currentUser().getUsersDetail());
		}

		return "/user/home/filter-doctors";
		
	}
	
	/*@GetMapping("/list/page")
	public String findPaginated(@RequestParam("pageNo") int pageNo,
			@RequestParam("pageSize") int pageSize,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, 
			Model model) {
		
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
		List<SpecialitiesCategory> specs = specialitiesService.findAll();
		model.addAttribute("top4Doctors", top4Doctors);
		model.addAttribute("specialities",specs);
		
		Authorities auth = authService.findById(3);
		
		System.out.println("-----******" + auth.toString());
		
		Page<Users> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Users> listUsers = page.getContent();
		Stream<Users> s = listUsers.stream();
		listUsers = s.collect(Collectors.toList());
		List<Users> filterUsers = null;
		for(Users u : listUsers) {
			if(u.getAuthorities().contains(auth)) {
				System.out.println("****************");
				System.out.println(u.toString());
			}
		}
		
		
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalPages", page.getTotalPages());
		
		//model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listUsers", listUsers);
			
	
		for(Users temp : listUsers) {
			System.out.println(temp.toString());
		}
		
		return "/user/home/home";

	}*/

	

}
