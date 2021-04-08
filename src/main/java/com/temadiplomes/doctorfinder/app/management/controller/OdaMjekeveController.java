package com.temadiplomes.doctorfinder.app.management.controller;

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

import com.temadiplomes.doctorfinder.entity.OdaEMjekeveKS;
import com.temadiplomes.doctorfinder.entity.Users;
import com.temadiplomes.doctorfinder.security.UsersServiceImpl;
import com.temadiplomes.doctorfinder.service.OdaEMjekeveKSService;

@Controller
@RequestMapping("/admin/odaMjekesise")
public class OdaMjekeveController {

	@Autowired
	private OdaEMjekeveKSService oMKSServices;
	
	@Autowired
	private UsersServiceImpl userService;
	
	@GetMapping("/list")
	public String odaMjekeveList(Model theModel) {

		//List<OdaEMjekeveKS> oDM = oMKSServices.findAll();

		//theModel.addAttribute("odaMjekeveList", oDM);

		//return "admin/oda-mjekeve-list";
		return findPaginated(1, 5, "emri", "asc", theModel);

	}

	@PostMapping("/save")
	public String saveOdaMjekeve(@ModelAttribute("anetari") OdaEMjekeveKS anetari) {

		// save the anetari

		oMKSServices.save(anetari);
		// use a redirect to prevent duplicate submissions
		return "redirect:/admin/odaMjekesise/list";

	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		OdaEMjekeveKS oDM = new OdaEMjekeveKS();

		theModel.addAttribute("anetari", oDM);
		theModel.addAttribute("userDetails", currentUser().getUsersDetail());

		return "admin/odaMjekeve/oda-mjekeve-form";

	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("odaMjekeveId") int theId, Model theModel) {

		OdaEMjekeveKS oDM = oMKSServices.findById(theId);

		theModel.addAttribute("anetari", oDM);
		theModel.addAttribute("userDetails", currentUser().getUsersDetail());

		return "admin/odaMjekeve/oda-mjekeve-form";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("odaMjekeveId") int theId) {

		oMKSServices.deleteById(theId);

		return "redirect:/admin/odaMjekesise/list";
	}
	
	@GetMapping("/list/page")
	public String findPaginated(@RequestParam("pageNo") int pageNo,
			@RequestParam("pageSize") int pageSize,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, 
			Model model) {
		
	//int pageSize = 5;
		
		Page<OdaEMjekeveKS> page = oMKSServices.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<OdaEMjekeveKS> listOdaEMjekeveKS = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("odaMjekeveList", listOdaEMjekeveKS);
		model.addAttribute("userDetails", currentUser().getUsersDetail());
		
		return "admin/odaMjekeve/oda-mjekeve-list";
	}
	
	
	public Users currentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Users user = userService.findByUsername(userDetails.getUsername());
		return user;
	}

}
