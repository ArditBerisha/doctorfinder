package com.temadiplomes.doctorfinder.client.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

import com.temadiplomes.doctorfinder.entity.Attribute;
import com.temadiplomes.doctorfinder.entity.AttributeValue;
import com.temadiplomes.doctorfinder.entity.Authorities;
import com.temadiplomes.doctorfinder.entity.Language;
import com.temadiplomes.doctorfinder.entity.SpecialitiesCategory;
import com.temadiplomes.doctorfinder.entity.Users;
import com.temadiplomes.doctorfinder.security.UsersServiceImpl;
import com.temadiplomes.doctorfinder.service.AttributeService;
import com.temadiplomes.doctorfinder.service.AttributeValueService;
import com.temadiplomes.doctorfinder.service.AuthoritiesService;
import com.temadiplomes.doctorfinder.service.SpecialitiesCategoryService;

import dto.UsersPhoto;
import enums.Status;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private UsersServiceImpl userService;
	
	@Autowired
	private SpecialitiesCategoryService specialitiesService;
	
	@Autowired
	private AuthoritiesService authService;
	
	@Autowired
	private AttributeService attrService;
	
	@Autowired
	private AttributeValueService attrValueService;
	
	private final boolean ShowInFilter = true;

	@GetMapping("/")
	public String home(Model model) {
		Users user = null;
		
		Attribute attributeKey = null;
		
		List<Attribute> attrInFilters = attrService.findByShowInFilter(ShowInFilter);
		
		List<AttributeValue> attributeValues;
		
		List<AttributeValue> attributeValues1 = null;
		
		HashMap<Attribute, List<AttributeValue>> filters = new HashMap<>();
		
		
		
		for (Attribute attr:attrInFilters) {
			if(attr.getDeleted() == Status.ACTIVE) {
				System.out.println("Ardit: " + attr.getName());
				attributeKey = attr;
				attributeValues = attrValueService.findByAttribute(attr);
				attributeValues1 = attrValueService.findByAttribute(attr);
				System.out.println("TEST ATTRIBUTE");
				filters.put(attributeKey, attributeValues);
				attributeValues = null;
			}
		}
		
		System.out.println("HashMap toString:" + filters.toString());
		try {
			user = currentUser();
		} catch (Exception exc) {
			user = null;
		}

		if (user != null) {
			model.addAttribute("userDetails", user.getUsersDetail());
			model.addAttribute("photoPath", currentUser().getUsersDetail());
		}
		
		Authorities auth = authService.findById(3);

		List<Users> top4Doctors = userService.findByAuthorities(auth);
		List<SpecialitiesCategory> specs = specialitiesService.findAll();
		model.addAttribute("top4Doctors", top4Doctors);
		model.addAttribute("specialities",specs);
		model.addAttribute("filters", filters);
		model.addAttribute("attributeValues1",attributeValues1);
		
		for(SpecialitiesCategory spec : specs) {
			System.out.println(spec.getName());
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
		uPhoto.setBiography(user.getUsersDetail().getBiography());
		model.addAttribute("userDetailsPhoto", currentUser().getUsersDetail());
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
			model.addAttribute("userDetails", user.getUsersDetail());
		}
		//model.addAttribute("photoPath", currentUser().getUsersDetail());
		return "user/mjekuspec";
	}
	
	@PostMapping("/doctors-by-category")
	public String doctors(@RequestParam("spe") String specName, Model model) {
		Users user = currentUser();
		
		SpecialitiesCategory speciality = specialitiesService.findByName(specName);
		
		user = null;
		try {
			user = currentUser();
		} catch (Exception exc) {
			user = null;
		}

		if (user != null) {
			model.addAttribute("userDetails", user.getUsersDetail());
			model.addAttribute("photoPath", currentUser().getUsersDetail());
		}
		
		SpecialitiesCategory category = specialitiesService.findByName(specName);
		List<Users> doctorsByCategory = userService.findBySpecCategory(category);

		List<SpecialitiesCategory> specs = specialitiesService.findAll();
		
		model.addAttribute("top4Doctors", doctorsByCategory);
		model.addAttribute("specialities",specs);
		
		for(SpecialitiesCategory spec : specs) {
			System.out.println(spec.getName());
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
