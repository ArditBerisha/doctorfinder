package com.temadiplomes.doctorfinder.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.temadiplomes.doctorfinder.dao.EducationRepository;
import com.temadiplomes.doctorfinder.entity.Education;
import com.temadiplomes.doctorfinder.entity.Users;
import com.temadiplomes.doctorfinder.security.UsersServiceImpl;

@Service
public class EducationServiceImpl implements EducationService {

	@Autowired
	private EducationRepository educationRepo;
	
	@Autowired
	private UsersServiceImpl userService;

	@Override
	public List<Education> findAll() {

		return educationRepo.findAll();
	}

	@Override
	public Education findById(int theId) {
		Optional<Education> result = educationRepo.findById(theId);
		Education education = null;
		if (result.isPresent()) {
			education = result.get();
		} else {
			return null;
		}

		return education;
	}

	@Override
	public void save(Education theEducation) {
		
		educationRepo.save(theEducation);

	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<Education> findPaginated(int pageNo, int pageSize, String sortField, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

		return this.educationRepo.findByUsers(currentUser(), pageable);
	}
	
	public Users currentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Users user = userService.findByUsername(userDetails.getUsername());
		return user;
	}

}
