package com.temadiplomes.doctorfinder.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.temadiplomes.doctorfinder.entity.Education;

public interface EducationService {

	public List<Education> findAll();
	
	public Education findById(int theId);
	
	public void save(Education theEducation);
	
	public void deleteById(int theId);
	
	public Page<Education> findPaginated(int pageNo, int pageSize, String sortField, String sortDir);
}
