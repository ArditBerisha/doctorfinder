package com.temadiplomes.doctorfinder.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.temadiplomes.doctorfinder.dao.ControlTypeRepository;
import com.temadiplomes.doctorfinder.entity.ControlType;

@Service
public class ControlTypeServiceImpl implements ControlTypeService {

	@Autowired
	private ControlTypeRepository controlTypeRepo;
	
	@Override
	public List<ControlType> findAll() {
		return controlTypeRepo.findAllByOrderByNameAsc();
	}

	@Override
	public ControlType findById(int theId) {

		Optional<ControlType> result = controlTypeRepo.findById(theId);
		
		ControlType cT = null;
		if(result.isPresent()) {
			cT = result.get();
		} else {
			return null;
		}
		return cT;
	}

	@Override
	public void save(ControlType theControlType) {
		
		controlTypeRepo.save(theControlType);
		
	}

	@Override
	public void deleteById(int theId) {
	

	}

	@Override
	public Page<ControlType> findPaginated(int pageNo, int pageSize, String sortField, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending():
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		
		return this.controlTypeRepo.findAll(pageable);
	}

}
