package com.temadiplomes.doctorfinder.app.management.controller;

import java.sql.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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

import com.temadiplomes.doctorfinder.entity.Attribute;
import com.temadiplomes.doctorfinder.entity.ControlType;
import com.temadiplomes.doctorfinder.entity.Users;
import com.temadiplomes.doctorfinder.security.UsersServiceImpl;
import com.temadiplomes.doctorfinder.service.AttributeServiceImpl;
import com.temadiplomes.doctorfinder.service.ControlTypeServiceImpl;

import enums.Status;

@Controller
@RequestMapping("/admin/attributes")
public class AttributesController {

	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	private AttributeServiceImpl attrService;
	
	@Autowired
	private UsersServiceImpl userService;
	
	@Autowired
	private ControlTypeServiceImpl cTService;
	
	@GetMapping("/list")
	public String attributeList(Model theModel) {
		return findPaginated(1, 5, "name", "asc", theModel);
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		boolean showInFilter = false;

		Attribute attribute = new Attribute();

		List<Attribute> attrList = attrService.findAll().stream().filter(spec -> spec.isDeleted() == Status.ACTIVE)
				.collect(Collectors.toList());
		
		List<ControlType> cTList = cTService.findAll();
		
		model.addAttribute("cTList", cTList);

		model.addAttribute("attrList", attrList);

		model.addAttribute("attribute", attribute);

		model.addAttribute("showInFilter", showInFilter);
		
		model.addAttribute("userDetails", currentUser().getUsersDetail());

		return "admin/attributes/attribute-form";
		
		
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("attribute") Attribute theAttr,
			@RequestParam("specId") int specId) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Users user = userService.findByUsername(userDetails.getUsername());
		long millis = System.currentTimeMillis();  
		
		Date date = new java.sql.Date(millis);  
		
		ControlType cT = null;
		Attribute result = attrService.findById(theAttr.getId());
		
		if(result == null) {
			cT = cTService.findById(specId);
			theAttr.setControlType(cT);
			theAttr.setCreatedOnDate(date);
			theAttr.setCreatedByUserId(user.getId());
			theAttr.setLastModifiedOnDate(date);
			theAttr.setLastModifiedByUserId(user.getId());
		} else {
			cT = cTService.findById(specId);
			theAttr.setControlType(cT);
			theAttr.setLastModifiedOnDate(date);
			theAttr.setLastModifiedByUserId(user.getId());
		}
		
		attrService.save(theAttr);

		return "redirect:/admin/attributes/list";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("specId") int theId, Model theModel) {

		// Users user = userService.findByUsername(username);
		// List<Authorities> userAuth = (List<Authorities>)user.getAuthorities();
		// List<Authorities> listAuth = authorityService.findAll();
		// List<Authorities> auth = (List<Authorities>) user.getAuthorities();

		// listAuth.removeAll(userAuth);

		// theModel.addAttribute("user", user);
		// theModel.addAttribute("intersectAuth", listAuth);
		// theModel.addAttribute("authId","");
		// theModel.addAttribute("authorities",auth);
		Attribute attribute = attrService.findById(theId);
		
		//String cTName = attrService.findControlTypeName(attribute.getControlType().getId());
		
		ControlType cT = cTService.findById(attribute.getControlType().getId());

		List<Attribute> attrList = attrService.findAll().parallelStream().filter(spec -> spec.isDeleted() == Status.ACTIVE)
				.collect(Collectors.toList());
		
		List<ControlType> cTList = cTService.findAll();
		// Stream<SpecialitiesCategory> s = specList.stream().filter(spec ->
		// !spec.isDeleted());
		// specList = s.collect(Collectors.toList());

		theModel.addAttribute("cTList", cTList);
		theModel.addAttribute("attrList", attrList);
		theModel.addAttribute("cTList", cTList);
		theModel.addAttribute("attribute", attribute);
		theModel.addAttribute("cT", cT);
		theModel.addAttribute("userDetails", currentUser().getUsersDetail());
		
		return "admin/attributes/attribute-form";

	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("specId") int theId) {
		attrService.deleteById(theId);
		return "redirect:/admin/attributes/list";
	}
	
	@GetMapping("/list/page")
	public String findPaginated(@RequestParam("pageNo") int pageNo,
			@RequestParam("pageSize") int pageSize,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, 
			Model model) {
		
		Page<Attribute> page = attrService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Attribute> attrList = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("attrList", attrList);
		model.addAttribute("userDetails", currentUser().getUsersDetail());
		
		return "admin/attributes/attributes-list";
	}
	
	public Users currentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Users user = userService.findByUsername(userDetails.getUsername());
		return user;
	}
	
}
