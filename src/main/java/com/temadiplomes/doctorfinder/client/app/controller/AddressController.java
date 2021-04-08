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

import com.temadiplomes.doctorfinder.entity.Address;
import com.temadiplomes.doctorfinder.entity.Ordinance;
import com.temadiplomes.doctorfinder.entity.Users;
import com.temadiplomes.doctorfinder.security.UsersServiceImpl;
import com.temadiplomes.doctorfinder.service.AddressServiceImpl;
import com.temadiplomes.doctorfinder.service.OrdinanceServiceImpl;

@Controller
@RequestMapping("/admin/address")
public class AddressController {

	@Autowired
	private AddressServiceImpl addressService;
	
	@Autowired
	private UsersServiceImpl userService;
	
	@Autowired
	private OrdinanceServiceImpl ordinanceService;
	
	private Ordinance currentOrdinance;
	
	@GetMapping("/list")
	public String certificationList(Model theModel, @RequestParam("ordId") int ordId) {
		return findPaginated(ordId, 1, 5, "country", "asc", theModel);
	}
	
	@PostMapping("/save")
	public String save(@RequestParam("ordId") int ordId, @ModelAttribute("address") Address theAddress) {

		Address cert = theAddress;
		
		Ordinance ord = ordinanceService.findById(ordId);
		
		cert.addOrdinance(ord);
		
		addressService.save(cert);

		return "redirect:/admin/address/list?ordId=" + currentOrdinance.getId();
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		Address address = new Address();
		
		model.addAttribute("address",address);
		
		model.addAttribute("userDetails", currentUser().getUsersDetail());
		
		model.addAttribute("currentOrdinance",currentOrdinance);
		System.out.println(currentOrdinance);
		
		return "user/doctor/address/address-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("addressId") int theId, Model theModel) {

		System.out.println(currentOrdinance);
		
		Address address = addressService.findById(theId);

		theModel.addAttribute("currentOrdinance",currentOrdinance);
		theModel.addAttribute("address", address);
		theModel.addAttribute("userDetails", currentUser().getUsersDetail());
		
		return "user/doctor/address/address-form";

	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("addressId") int theId) {
		//certificationService.deleteById(theId);
		return "redirect:/admin/address/list";
	}
	
	@GetMapping("/list/page")
	public String findPaginated(
			@RequestParam("ordId") int ordId,
			@RequestParam("pageNo") int pageNo,
			@RequestParam("pageSize") int pageSize,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, 
			Model model) {
		
		currentOrdinance = ordinanceService.findById(ordId);
		
		Ordinance ord = ordinanceService.findById(ordId);
		
		Page<Address> page = addressService.findPaginated(ord, pageNo, pageSize, sortField, sortDir);
		List<Address> addressList = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("addressList", addressList);
		model.addAttribute("userDetails", currentUser().getUsersDetail());
		
		return "user/doctor/address/address-list";
	}
	
	public Users currentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Users user = userService.findByUsername(userDetails.getUsername());
		return user;
	}
	
}
