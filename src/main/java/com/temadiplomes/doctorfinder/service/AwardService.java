package com.temadiplomes.doctorfinder.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.temadiplomes.doctorfinder.entity.Award;

public interface AwardService {

	public List<Award> findAll();
	
	public Award findById(int theId);
	
	public void save(Award theAward);
	
	public void deleteById(int theId);
	
	public Page<Award> findPaginated(int pageNo, int pageSize, String sortField, String sortDir);
}
