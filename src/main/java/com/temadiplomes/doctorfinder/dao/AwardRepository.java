package com.temadiplomes.doctorfinder.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.temadiplomes.doctorfinder.entity.Award;
import com.temadiplomes.doctorfinder.entity.Users;

public interface AwardRepository extends JpaRepository<Award, Integer> {

	public Page<Award> findByUsers(Users user, Pageable pageable);
	
}
