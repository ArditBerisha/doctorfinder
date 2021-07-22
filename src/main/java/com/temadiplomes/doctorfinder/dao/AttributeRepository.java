package com.temadiplomes.doctorfinder.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.temadiplomes.doctorfinder.entity.Attribute;

import enums.Status;

public interface AttributeRepository extends JpaRepository<Attribute, Integer> {

	public List<Attribute> findAllByOrderByNameAsc();
	
	Page<Attribute> findByIsDeleted(Status status, Pageable pageable);
	
	List<Attribute> findByShowInFilter(boolean showInFilter);
}
