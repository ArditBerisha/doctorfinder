package com.temadiplomes.doctorfinder.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.temadiplomes.doctorfinder.entity.Ordinance;
import com.temadiplomes.doctorfinder.entity.Users;

public interface OrdinanceRepository extends JpaRepository<Ordinance, Integer> {

	public Page<Ordinance> findByUsers(Users user, Pageable pageable);
	
}
