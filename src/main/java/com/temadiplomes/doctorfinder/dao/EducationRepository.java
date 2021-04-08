package com.temadiplomes.doctorfinder.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.temadiplomes.doctorfinder.entity.Education;
import com.temadiplomes.doctorfinder.entity.Users;

public interface EducationRepository extends JpaRepository<Education, Integer> {

	public Page<Education> findByUsers(Users user, Pageable pageable);
	
}
