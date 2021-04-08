package com.temadiplomes.doctorfinder.client.app.controller;

import java.util.List;

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

import com.temadiplomes.doctorfinder.entity.Certification;
import com.temadiplomes.doctorfinder.entity.Ordinance;
import com.temadiplomes.doctorfinder.entity.Users;
import com.temadiplomes.doctorfinder.security.UsersServiceImpl;
import com.temadiplomes.doctorfinder.service.OrdinanceServiceImpl;

@Controller
@RequestMapping("/admin/ordinances")
public class OrdinanceController {

	@Autowired
	private OrdinanceServiceImpl ordinanceService;
	
	@Autowired
	private UsersServiceImpl userService;
	
	@GetMapping("/list")
	public String certificationList(Model theModel) {
		return findPaginated(1, 5, "name", "asc", theModel);
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("ordinance") Ordinance theOrd) {

		Ordinance ordinance = theOrd;
		
		Users currentUser = currentUser();
		
		ordinance.addUser(currentUser);
		
		ordinanceService.save(ordinance);
		
		//currentUser.addCertfication(cert);
		//userService.save(currentUser);

		return "redirect:/admin/ordinances/list";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		Ordinance ordinance = new Ordinance();
		
		model.addAttribute("ordinance",ordinance);
		
		model.addAttribute("userDetails", currentUser().getUsersDetail());
		
		return "user/doctor/ordinances/ordinance-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("ordinanceId") int theId, Model theModel) {

		Ordinance ordinance = ordinanceService.findById(theId);

		theModel.addAttribute("ordinance", ordinance);
		theModel.addAttribute("userDetails", currentUser().getUsersDetail());
		
		return "user/doctor/ordinances/ordinance-form";

	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("ordinanceId") int theId) {
		ordinanceService.deleteById(theId);
		return "redirect:/admin/ordinances/list";
	}
	
	@GetMapping("/list/page")
	public String findPaginated(@RequestParam("pageNo") int pageNo,
			@RequestParam("pageSize") int pageSize,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, 
			Model model) {
		
		Page<Ordinance> page = ordinanceService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Ordinance> ordinanceList = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("ordinanceList", ordinanceList);
		model.addAttribute("userDetails", currentUser().getUsersDetail());
		
		return "user/doctor/ordinances/ordinance-list";
	}
	
	public Users currentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Users user = userService.findByUsername(userDetails.getUsername());
		return user;
	}
	
}
