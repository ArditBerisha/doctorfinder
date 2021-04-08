package com.temadiplomes.doctorfinder.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.temadiplomes.doctorfinder.entity.ControlType;

public interface ControlTypeService {

    public List<ControlType> findAll();
	
	public ControlType findById(int theId);
	
	public void save(ControlType theControlType);
	
	public void deleteById(int theId);
	
	public Page<ControlType> findPaginated(int pageNo, int pageSize, String sortField, String sortDir);
}
