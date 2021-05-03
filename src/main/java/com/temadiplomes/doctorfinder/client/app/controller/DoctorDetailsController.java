package com.temadiplomes.doctorfinder.client.app.controller;

import java.util.List;

import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.temadiplomes.doctorfinder.entity.Award;
import com.temadiplomes.doctorfinder.entity.Certification;
import com.temadiplomes.doctorfinder.entity.Education;
import com.temadiplomes.doctorfinder.entity.Language;
import com.temadiplomes.doctorfinder.entity.Ordinance;
import com.temadiplomes.doctorfinder.entity.SpecialitiesCategory;
import com.temadiplomes.doctorfinder.entity.Users;
import com.temadiplomes.doctorfinder.entity.UsersDetail;
import com.temadiplomes.doctorfinder.security.UsersServiceImpl;
import com.temadiplomes.doctorfinder.service.SpecialitiesCategoryServiceImpl;

import dto.DoctorDetailsDTO;

@Controller
@RequestMapping("/details")
public class DoctorDetailsController {

	@Autowired
	SpecialitiesCategoryServiceImpl specialitiesCategoryServiceImpl;

	@Autowired
	private UsersServiceImpl userService;

	@GetMapping("/doc")
	public String getDoctor(@RequestParam("docId") int docId, Model model) {
		
		StringBuilder sb = null;
		DoctorDetailsDTO doctorDTO = new DoctorDetailsDTO();

		Users doctor = userService.findById(docId);
		List<SpecialitiesCategory> speciality = doctor.getSpecCategory();
		List<Award> award = doctor.getAwards();
		List<Certification> certifications = doctor.getCertifications();
		List<Ordinance> ordinances = doctor.getOrdinances();
		List<Language> lang = doctor.getLanguages();
		List<Education> education = doctor.getEducation();
		UsersDetail userDetails = doctor.getUsersDetail();

		String spec = null;
		String awards = null;
		String certification = null;
		String edu = null;
		String languages = null;

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

		for (int i = 0; i < speciality.size(); i++) {

			sb = new StringBuilder();

			sb.append(speciality.get(i).getName());
			spec += sb.toString();

			if (i != speciality.size() - 1) {
				sb.append(", ");
				spec += sb.toString();
			}

		}

		for (int i = 0; i < award.size(); i++) {

			sb = new StringBuilder();

			sb.append(award.get(i).getTitle());
			awards += sb.toString();

			if (i != award.size() - 1) {
				sb.append(", ");
				awards += sb.toString();
			}

		}

		for (int i = 0; i < certifications.size(); i++) {

			sb = new StringBuilder();

			sb.append(certifications.get(i).getTitleOfCertification());
			certification += sb.toString();

			if (i != certifications.size() - 1) {
				sb.append(", ");
				certification += sb.toString();
			}

		}

		for (int i = 0; i < education.size(); i++) {
			
			sb = new StringBuilder();
			
			sb.append(education.get(i).getProgram());
			edu += sb.toString();

			if (i != education.size() - 1) {
				sb.append(", ");
				edu += sb.toString();
			}

		}

		for (int i = 0; i < lang.size(); i++) {
			
			sb = new StringBuilder();
			
			sb.append(lang.get(i).getLanguage());
			languages += sb.toString();

			if (i != lang.size() - 1) {
				sb.append(", ");
				languages += sb.toString();
			}

		}

		doctorDTO.setFirstName(doctor.getFirstName());
		doctorDTO.setLastName(doctor.getFirstName());
		doctorDTO.setAward(awards);
		doctorDTO.setSpeciality(spec);
		doctorDTO.setCertification(certification);
		doctorDTO.setEducation(edu);
		doctorDTO.setLang(languages);
		doctorDTO.setBiography(userDetails.getBiography());
		
		List<Ordinance> o = doctor.getOrdinances();
		
		for (Ordinance ordinance : o) {
			List<com.temadiplomes.doctorfinder.entity.Address> ad = ordinance.getAddress();
			
			for(com.temadiplomes.doctorfinder.entity.Address add : ad) {
				System.out.println("*****************");
				System.out.println(add.getAddress1());
			}
		}

		model.addAttribute("mjekuSpec", doctor);
		model.addAttribute("photoProfile", doctor.getUsersDetail().getPhotoPath());
		return "user/mjekuspec";
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

}
