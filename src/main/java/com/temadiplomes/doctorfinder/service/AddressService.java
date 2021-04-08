package com.temadiplomes.doctorfinder.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.temadiplomes.doctorfinder.entity.Address;
import com.temadiplomes.doctorfinder.entity.Ordinance;

public interface AddressService {

	public List<Address> findAll();
	
	public Address findById(int theId);
	
	public void save(Address theAddress);
	
	public void deleteById(int theId);
	
	public Page<Address> findPaginated(Ordinance ordinance, int pageNo, int pageSize, String sortField, String sortDir);
}
