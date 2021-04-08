/*
package com.temadiplomes.doctorfinder.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.temadiplomes.doctorfinder.dao.RecommendedMahoutTableRepository;
import com.temadiplomes.doctorfinder.entity.RecommendedMahoutTable;

@Service
public class RecommendedMahoutTableServiceImpl implements RecommendedMahoutTableService {

	@Autowired
	private RecommendedMahoutTableRepository recommendedRepository;

	@Override
	public List<RecommendedMahoutTable> findAll() {

		return recommendedRepository.findAll();
	}

	@Override
	public RecommendedMahoutTable findById(int theId) {

		Optional<RecommendedMahoutTable> result = recommendedRepository.findById(theId);
		RecommendedMahoutTable recMahout = null;
		if (result.isPresent()) {
			recMahout = result.get();
		} else {
			return null;
		}

		return recMahout;
	}

	@Override
	public void save(RecommendedMahoutTable theRMT) {

		recommendedRepository.save(theRMT);
	}

	@Override
	public void deleteById(int theId) {

	}
	
	//public List<RecommendedMahoutTable> topFourDoctors(){
		//return recommendedRepository.findTop4ByOrderByPreferenceDesc();
	//}

}
*/
