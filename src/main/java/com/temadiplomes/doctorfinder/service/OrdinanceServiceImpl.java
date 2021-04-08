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

import com.temadiplomes.doctorfinder.dao.OrdinanceRepository;
import com.temadiplomes.doctorfinder.entity.Ordinance;
import com.temadiplomes.doctorfinder.entity.Users;
import com.temadiplomes.doctorfinder.security.UsersServiceImpl;

@Service
public class OrdinanceServiceImpl implements OrdinanceService {
	
	@Autowired
	private OrdinanceRepository ordinanceRepo;
	
	@Autowired
	private UsersServiceImpl userService;

	@Override
	public List<Ordinance> findAll() {


		return ordinanceRepo.findAll();
		
	}

	@Override
	public Ordinance findById(int theId) {
	
		Optional<Ordinance> result = ordinanceRepo.findById(theId);
		Ordinance ordinance = null;
		if(result.isPresent()) {
			ordinance = result.get();
		} else {
			return null;
		}
		
		return ordinance;
		
	}

	@Override
	public void save(Ordinance theOrdinance) {

		ordinanceRepo.save(theOrdinance);

	}

	@Override
	public void deleteById(int theId) {
		

	}

	@Override
	public Page<Ordinance> findPaginated(int pageNo, int pageSize, String sortField, String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

		return this.ordinanceRepo.findByUsers(currentUser(), pageable);
		
	}
	
	public Users currentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Users user = userService.findByUsername(userDetails.getUsername());
		return user;
	}

}
