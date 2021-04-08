package com.temadiplomes.doctorfinder.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.temadiplomes.doctorfinder.entity.Address;
import com.temadiplomes.doctorfinder.entity.Ordinance;

public interface AddressRepository extends JpaRepository<Address, Integer>{

	public Page<Address> findByOrdinance(Ordinance ordinance, Pageable pageable);
	
}
