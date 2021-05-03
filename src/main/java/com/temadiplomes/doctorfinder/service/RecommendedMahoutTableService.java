
package com.temadiplomes.doctorfinder.service;

import java.util.List;

import com.temadiplomes.doctorfinder.entity.RecommendedMahoutTable;

public interface RecommendedMahoutTableService {

	public List<RecommendedMahoutTable> findAll();
	
	public RecommendedMahoutTable findById(int theId);
	
	public void save(RecommendedMahoutTable theRMT);
	
	public void deleteById(int theId);
}

