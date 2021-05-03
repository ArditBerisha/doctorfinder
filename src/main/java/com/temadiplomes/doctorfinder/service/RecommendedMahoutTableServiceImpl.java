
package com.temadiplomes.doctorfinder.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.temadiplomes.doctorfinder.dao.RecommendedMahoutTableRepository;
import com.temadiplomes.doctorfinder.entity.Attribute;
import com.temadiplomes.doctorfinder.entity.RecommendedMahoutTable;

@Service
public class RecommendedMahoutTableServiceImpl implements RecommendedMahoutTableService {
	
	@Autowired
	private RecommendedMahoutTableRepository rMTRepository;

	@Override
	public List<RecommendedMahoutTable> findAll() {
		return rMTRepository.findAll();
	}

	@Override
	public RecommendedMahoutTable findById(int theId) {
		Optional<RecommendedMahoutTable> result = rMTRepository.findById(theId);

		RecommendedMahoutTable attr = null;
		if (result.isPresent()) {
			attr = result.get();
		} else {
			return null;
		}
		return attr;
	}

	@Override
	public void save(RecommendedMahoutTable theRMT) {
		rMTRepository.save(theRMT);
	}

	@Override
	public void deleteById(int theId) {
		System.out.println("sdsd");
		
	}

	

}

