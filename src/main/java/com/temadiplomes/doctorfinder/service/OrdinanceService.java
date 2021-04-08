package com.temadiplomes.doctorfinder.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.temadiplomes.doctorfinder.entity.Ordinance;

public interface OrdinanceService {

	public List<Ordinance> findAll();
	
	public Ordinance findById(int theId);
	
	public void save(Ordinance theOrdinance);
	
	public void deleteById(int theId);
	
	public Page<Ordinance> findPaginated(int pageNo, int pageSize, String sortField, String sortDir);
}
