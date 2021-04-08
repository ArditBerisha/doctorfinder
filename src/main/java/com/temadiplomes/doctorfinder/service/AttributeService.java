package com.temadiplomes.doctorfinder.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.temadiplomes.doctorfinder.entity.Attribute;

public interface AttributeService {

	public List<Attribute> findAll();
	
	public Attribute findById(int theId);
	
	public void save(Attribute theAttribute);
	
	public void deleteById(int theId);
	
	public String findControlTypeName(int theId);
	
	public Page<Attribute> findPaginated(int pageNo, int pageSize, String sortField, String sortDir);
}
