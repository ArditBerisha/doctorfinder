package com.temadiplomes.doctorfinder.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.temadiplomes.doctorfinder.entity.Attribute;
import com.temadiplomes.doctorfinder.entity.AttributeValue;

public interface AttributeValueRepository extends JpaRepository<AttributeValue, Integer> {

	public List<AttributeValue> findByAttribute(Attribute attr);
	
	public AttributeValue findByValue(String val);
	
}
