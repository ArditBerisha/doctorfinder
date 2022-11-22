package com.temadiplomes.doctorfinder.app.management.controller;

import java.util.Arrays;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.temadiplomes.doctorfinder.dao.AuthoritiesRepository;
import com.temadiplomes.doctorfinder.entity.Users;
import com.temadiplomes.doctorfinder.security.CustomUserService;

import dto.UserDTO;
import enums.Status;



@Controller
@RequestMapping("/register")
public class RegistrationController {
	
    @Autowired
    private CustomUserService usersService;
    
    @Autowired
	private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired 
	private AuthoritiesRepository authorityRepository;
	
    private Logger logger = Logger.getLogger(getClass().getName());
    
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@GetMapping("/showRegistrationForm")
	public String showMyLoginPage(Model theModel) {
		
		theModel.addAttribute("userDTO", new UserDTO());
		theModel.addAttribute("theUser", new Users());
		
		return "/admin/register/registerForm";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
				@Valid @ModelAttribute("userDTO") UserDTO theUser, 
				BindingResult theBindingResult, 
				Model theModel) {
		
		String userName = theUser.getUsername();
		logger.info("Processing registration form for: " + userName);
		
		// form validation
		 if (theBindingResult.hasErrors()){
			 return "/admin/register/registrationForm";
	        }

		// check the database if user already exists
        Users existing = usersService.findByUsername(userName);
       
        if (existing != null){
        	theModel.addAttribute("theUser", new Users());
			theModel.addAttribute("registrationError", "User name already exists.");

			logger.warning("User name already exists.");
			return "/admin/register/registrationForm";
        }

        Users user = new Users();
        user.setLastName(theUser.getLastName());
        user.setFirstName(theUser.getFirstName());
        user.setEmail(theUser.getEmail());
        user.setUsername(theUser.getUsername());
        user.setPassword(passwordEncoder.encode(theUser.getPassword()));
        user.setEnabled(Status.ACTIVE);
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setAuthorities(Arrays.asList(authorityRepository.findRoleByAuthority("ROLE_ADMIN")));
        
        // create user account        						
        usersService.save(user);
        
        logger.info("Successfully created user: " + userName);
        
		return "/admin/login/logintest";
	}
}
