package com.temadiplomes.doctorfinder.service;

import java.util.List;

import com.temadiplomes.doctorfinder.entity.UsersDetail;

public interface UsersDetailService {

	public List<UsersDetail> findAll();
	
	public UsersDetail findById(int theId);
	
	public void save(UsersDetail theUserDetail);
	
	public void deleteById(int theId);
}
