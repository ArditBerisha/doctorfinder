package com.temadiplomes.doctorfinder.client.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FileUploadController {

	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";
	
	@GetMapping("/upload")
	public String UploadPage(Model theModel) {
		return "uploadview";
	}
	
}
