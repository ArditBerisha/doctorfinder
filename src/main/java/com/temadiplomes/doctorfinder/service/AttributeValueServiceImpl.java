package com.temadiplomes.doctorfinder.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.temadiplomes.doctorfinder.dao.AttributeValueRepository;
import com.temadiplomes.doctorfinder.entity.Attribute;
import com.temadiplomes.doctorfinder.entity.AttributeValue;
import com.temadiplomes.doctorfinder.entity.Language;

import enums.Status;

@Service
public class AttributeValueServiceImpl implements AttributeValueService {

	private AttributeValueRepository attributeValueRepository;
	
	@Autowired
	public AttributeValueServiceImpl(AttributeValueRepository attributeValueRepository) {
		this.attributeValueRepository = attributeValueRepository;
	}
	
	@Autowired
	private AttributeServiceImpl attrService;
	
	@Override
	public List<AttributeValue> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AttributeValue findById(int theId) {
		Optional<AttributeValue> result = attributeValueRepository.findById(theId);
		AttributeValue attributeValue = null;
		if(result.isPresent()) {
			attributeValue = result.get();
		} else {
			return null;
		}
		
		return attributeValue;
	}

	@Override
	public void save(AttributeValue theAttrValue) {
		theAttrValue.setDeleted(true);
		attributeValueRepository.save(theAttrValue);

	}

	@Override
	public void deleteById(int theId) {
		attributeValueRepository.deleteById(theId);

	}

	@Override
	public List<AttributeValue> findByAttribute(Attribute attr) {
		return attributeValueRepository.findByAttribute(attr);
	}



	@Override
	public AttributeValue findByValue(String val) {
		
		return attributeValueRepository.findByValue(val);
		
	}

	@Override
	public void deleteLanguageFromUser(int id) {
		
		AttributeValue attrValue = findById(id);
		attributeValueRepository.delete(attrValue);
			
	}

}
