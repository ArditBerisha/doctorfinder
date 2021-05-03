package com.temadiplomes.doctorfinder.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.temadiplomes.doctorfinder.entity.SpecialitiesCategory;

public interface SpecialitiesCategoryService {

	public List<SpecialitiesCategory> findAll();
	
	public SpecialitiesCategory findById(int theId);
	
	public SpecialitiesCategory findSpecByUserId(int theId);
	
	public void save(SpecialitiesCategory theSpec);
	
	public void deleteById(int theId);
	
	public String findParentName(int theId);
	
	public Page<SpecialitiesCategory> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
