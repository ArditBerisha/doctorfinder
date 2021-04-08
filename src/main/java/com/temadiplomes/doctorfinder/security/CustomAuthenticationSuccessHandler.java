package com.temadiplomes.doctorfinder.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.temadiplomes.doctorfinder.entity.Users;
import com.temadiplomes.doctorfinder.security.CustomUserService;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	
    @Autowired
    private CustomUserService userService;
	private String string;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		System.out.println("\n\nIn customAuthenticationSuccessHandler\n\n");

		String userName = authentication.getName();
		
		System.out.println("userName=" + userName);

		Users theUser = userService.findByUsername(userName);
		
		// now place in the session
		HttpSession session = request.getSession();
		session.setAttribute("user", theUser);
		
		// forward to home page
		
		string = "ROLE_EMPLOYEE";
		if(theUser.getAuthorities().contains(string)) {
			response.sendRedirect(request.getContextPath() + "/home/");
		}
		
		else {
			response.sendRedirect(request.getContextPath() + "/admin/dashboard/");
		}
		
		
	}

}
