package com.temadiplomes.doctorfinder.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.temadiplomes.doctorfinder.entity.ControlType;

public interface ControlTypeRepository extends JpaRepository<ControlType, Integer> {
	
	public List<ControlType> findAllByOrderByNameAsc();
	
}
