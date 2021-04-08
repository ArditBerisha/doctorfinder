package com.temadiplomes.doctorfinder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.temadiplomes.doctorfinder.dao.AttributeSpecificationRepository;
import com.temadiplomes.doctorfinder.entity.AttributeSpecification;

public class AttributeSpecificationServiceImpl implements AttributeSpecificationService {

	private AttributeSpecificationRepository attributeSpecificationRepository;
	
	@Autowired
	public AttributeSpecificationServiceImpl(AttributeSpecificationRepository attributeSpecificationRepository){
		this.attributeSpecificationRepository = attributeSpecificationRepository;
	}
	
	@Override
	public List<AttributeSpecification> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AttributeSpecification findById(int theId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(AttributeSpecification theAttrSpec) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		
	}
}
