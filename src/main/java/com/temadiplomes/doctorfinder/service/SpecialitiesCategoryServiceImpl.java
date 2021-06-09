package com.temadiplomes.doctorfinder.service;

import java.text.SimpleDateFormat;
import java.sql.Date;
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

import com.temadiplomes.doctorfinder.dao.AuthoritiesRepository;
import com.temadiplomes.doctorfinder.dao.SpecialitiesCategoryRepository;
import com.temadiplomes.doctorfinder.entity.SpecialitiesCategory;
import com.temadiplomes.doctorfinder.entity.Users;
import com.temadiplomes.doctorfinder.security.UsersServiceImpl;

import enums.Status;

@Service
public class SpecialitiesCategoryServiceImpl implements SpecialitiesCategoryService {

	@Autowired
	private SpecialitiesCategoryRepository specRepository;

	@Autowired
	private UsersServiceImpl userService;

	@Autowired
	private AuthoritiesRepository authorityRepository;

	@Override
	public List<SpecialitiesCategory> findAll() {

		return specRepository.findAll();
	}

	@Override
	public SpecialitiesCategory findById(int theId) {

		Optional<SpecialitiesCategory> result = specRepository.findById(theId);

		SpecialitiesCategory theSpec = null;

		if (result.isPresent()) {

			theSpec = result.get();

		} else {

			theSpec = null;
		}

		return theSpec;
	}

	@Override
	public void save(SpecialitiesCategory theSpec) {
		theSpec.setDeleted(Status.ACTIVE);
		specRepository.save(theSpec);
	}

	@Override
	public void deleteById(int theId) {

		Optional<SpecialitiesCategory> result = specRepository.findById(theId);

		SpecialitiesCategory theSpec = null;

		if (result.isPresent()) {

			theSpec = result.get();

		} else {

			throw new RuntimeException("Did not find Speciality id - " + theId);
		}

		theSpec.setDeleted(Status.ACTIVE);
		specRepository.save(theSpec);

	}

	@Override
	public Page<SpecialitiesCategory> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

		if (currentUser().getAuthorities().contains(authorityRepository.findRoleByAuthority("ROLE_ADMIN"))) {
			
			return this.specRepository.findByIsDeleted(Status.ACTIVE, pageable);

		} else {
			
			return this.specRepository.findByUsers(currentUser(), pageable);
		}
	}

	@Override
	public String findParentName(int theId) {

		Optional<SpecialitiesCategory> result = specRepository.findById(theId);

		SpecialitiesCategory theSpec = null;

		if (result.isPresent()) {

			theSpec = result.get();

		} else {

			throw new RuntimeException("Did not find Speciality id - " + theId);
		}

		return theSpec.getName();
	}

	public Users currentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Users user = userService.findByUsername(userDetails.getUsername());
		return user;
	}

	@Override
	public SpecialitiesCategory findSpecByUserId(int theId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SpecialitiesCategory> findByUsers(Users user) {
		
		return specRepository.findByUsers(currentUser());
		
	}
	
	@Override
	public SpecialitiesCategory findByName(String name) {
		Optional<SpecialitiesCategory> result = Optional.of(specRepository.findByName(name));

		SpecialitiesCategory theSpec = null;

		if (result.isPresent()) {

			theSpec = result.get();

		} else {

			theSpec = null;
		}

		return theSpec;
	}

}
