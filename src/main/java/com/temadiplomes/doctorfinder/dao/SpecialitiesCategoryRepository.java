package com.temadiplomes.doctorfinder.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.temadiplomes.doctorfinder.entity.SpecialitiesCategory;
import com.temadiplomes.doctorfinder.entity.Users;

import enums.Status;

public interface SpecialitiesCategoryRepository extends JpaRepository<SpecialitiesCategory, Integer> {

	public List<SpecialitiesCategory> findAllByOrderByNameAsc();
	
	public SpecialitiesCategory findByName(String name);
	
	Page<SpecialitiesCategory> findByIsDeleted(Status status, Pageable pageable);
	
	public Page<SpecialitiesCategory> findByUsers(Users user, Pageable pageable);
	
	public List<SpecialitiesCategory> findByUsers(Users user); 
	
	//public List<Users> findBySpecialitiesCategory(SpecialitiesCategory specCategory);
}
