package com.temadiplomes.doctorfinder.service;

import java.util.List;

import com.temadiplomes.doctorfinder.entity.AttributeSpecification;

public interface AttributeSpecificationService {

	public List<AttributeSpecification> findAll();
	
	public AttributeSpecification findById(int theId);
	
	public void save(AttributeSpecification theAttrSpec);
	
	public void deleteById(int theId);
}
