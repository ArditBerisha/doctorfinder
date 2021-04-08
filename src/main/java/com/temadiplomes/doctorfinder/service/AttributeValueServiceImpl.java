package com.temadiplomes.doctorfinder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.temadiplomes.doctorfinder.dao.AttributeValueRepository;
import com.temadiplomes.doctorfinder.entity.AttributeValue;

public class AttributeValueServiceImpl implements AttributeValueService {

	private AttributeValueRepository attributeValueRepository;
	
	@Autowired
	public AttributeValueServiceImpl(AttributeValueRepository attributeValueRepository) {
		this.attributeValueRepository = attributeValueRepository;
	}
	
	@Override
	public List<AttributeValue> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AttributeValue findById(int theId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(AttributeValue theAttrValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub

	}

}
