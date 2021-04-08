package com.temadiplomes.doctorfinder.service;

import java.util.List;

import com.temadiplomes.doctorfinder.entity.AttributeValue;

public interface AttributeValueService {

	public List<AttributeValue> findAll();
	
	public AttributeValue findById(int theId);
	
	public void save(AttributeValue theAttrValue);
	
	public void deleteById(int theId);
}
