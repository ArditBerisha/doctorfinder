package com.temadiplomes.doctorfinder.app.management.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.temadiplomes.doctorfinder.service.RecommendedMahoutTableServiceImpl;

@Controller
@RequestMapping("/rating")
public class RecommendedMahoutController {
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	private RecommendedMahoutTableServiceImpl rMTService;
	
	@PostMapping("/save")
	@ResponseBody
	public String save(HttpServletRequest request) {
		System.out.println("*****************");
		System.out.println(request.getParameter("id"));
		System.out.println(request.getParameter("rate"));
		return "user/ttt";
	}
}
