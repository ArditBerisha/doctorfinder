package com.temadiplomes.doctorfinder.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.temadiplomes.doctorfinder.dao.UsersDetailRepository;
import com.temadiplomes.doctorfinder.entity.UsersDetail;

@Service
public class UsersDetailServiceImpl implements UsersDetailService {

	@Autowired
	private UsersDetailRepository userDetailsRepo;
	
	@Override
	public List<UsersDetail> findAll() {
		
		return userDetailsRepo.findAll();
	}

	@Override
	public UsersDetail findById(int theId) {
		Optional<UsersDetail> result = userDetailsRepo.findById(theId);
		
		UsersDetail auth = null;
		
		if(result.isPresent()) {
			auth = result.get();
		}
		else {
			throw new RuntimeException("Did not find Details id - " + theId);
		}
		return auth;
	}

	@Override
	public void save(UsersDetail theUserDetail) {
		userDetailsRepo.save(theUserDetail);

	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub

	}

}
