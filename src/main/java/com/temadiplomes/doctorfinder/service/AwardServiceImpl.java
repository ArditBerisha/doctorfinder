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

import com.temadiplomes.doctorfinder.dao.AwardRepository;
import com.temadiplomes.doctorfinder.entity.Award;
import com.temadiplomes.doctorfinder.entity.Users;
import com.temadiplomes.doctorfinder.security.UsersServiceImpl;

@Service
public class AwardServiceImpl implements AwardService {

	@Autowired
	private AwardRepository awardRepository;
	
	@Autowired
	private UsersServiceImpl userService;
	
	@Override
	public List<Award> findAll() {
		
		return awardRepository.findAll();
	}

	@Override
	public Award findById(int theId) {
		
		Optional<Award> result = awardRepository.findById(theId);
		Award award = null;
		if(result.isPresent()) {
			award = result.get();
		} else {
			return null;
		}
		
		return award;
	}

	@Override
	public void save(Award theAward) {
		
		awardRepository.save(theAward);

	}

	@Override
	public void deleteById(int theId) {
		

	}

	@Override
	public Page<Award> findPaginated(int pageNo, int pageSize, String sortField, String sortDir) {
		
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

		return this.awardRepository.findByUsers(currentUser(), pageable);
	}
	
	public Users currentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Users user = userService.findByUsername(userDetails.getUsername());
		return user;
	}

}
