package com.temadiplomes.doctorfinder.service;

import java.util.List;

import com.temadiplomes.doctorfinder.entity.PhoneNumber;

public interface PhoneNumberService {

	public List<PhoneNumber> findAll();
	
	public PhoneNumber findById(int theId);
	
	public void save(PhoneNumber theNumber);
	
	public void deleteById(int theId);
}
