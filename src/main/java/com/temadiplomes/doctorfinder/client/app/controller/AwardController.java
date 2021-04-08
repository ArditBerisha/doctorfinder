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

import com.temadiplomes.doctorfinder.entity.Award;
import com.temadiplomes.doctorfinder.entity.Certification;
import com.temadiplomes.doctorfinder.entity.Users;
import com.temadiplomes.doctorfinder.security.UsersServiceImpl;
import com.temadiplomes.doctorfinder.service.AwardServiceImpl;

@Controller
@RequestMapping("/admin/awards")
public class AwardController {

	@Autowired
	private AwardServiceImpl awardService;
	
	@Autowired
	private UsersServiceImpl userService;
	
	@GetMapping("/list")
	public String certificationList(Model theModel) {
		return findPaginated(1, 5, "title", "asc", theModel);
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("award") Award theAward) {

		Award award = theAward;
		
		Users currentUser = currentUser();
		
		award.addUser(currentUser);
		
		awardService.save(award);
		
		//currentUser.addCertfication(cert);
		//userService.save(currentUser);

		return "redirect:/admin/awards/list";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		Award award = new Award();
		
		model.addAttribute("award",award);
		
		model.addAttribute("userDetails", currentUser().getUsersDetail());
		
		return "user/doctor/awards/award-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("awardId") int theId, Model theModel) {

		Award award = awardService.findById(theId);

		theModel.addAttribute("award", award);
		theModel.addAttribute("userDetails", currentUser().getUsersDetail());
		
		return "user/doctor/awards/award-form";

	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("specId") int theId) {
		awardService.deleteById(theId);
		return "redirect:/admin/awards/list";
	}
	
	@GetMapping("/list/page")
	public String findPaginated(@RequestParam("pageNo") int pageNo,
			@RequestParam("pageSize") int pageSize,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, 
			Model model) {
		
		Page<Award> page = awardService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Award> awardList = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("awardList", awardList);
		model.addAttribute("userDetails", currentUser().getUsersDetail());
		
		return "user/doctor/awards/award-list";
	}
	
	public Users currentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Users user = userService.findByUsername(userDetails.getUsername());
		return user;
	}
}
