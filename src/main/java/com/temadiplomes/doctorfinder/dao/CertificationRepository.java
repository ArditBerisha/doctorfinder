package com.temadiplomes.doctorfinder.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.temadiplomes.doctorfinder.entity.Certification;
import com.temadiplomes.doctorfinder.entity.Users;

import enums.Status;

public interface CertificationRepository extends JpaRepository<Certification, Integer> {

	public Page<Certification> findByUsers(Users user, Pageable pageable);
	
}
