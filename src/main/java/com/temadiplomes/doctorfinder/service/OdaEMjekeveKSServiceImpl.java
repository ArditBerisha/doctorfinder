package com.temadiplomes.doctorfinder.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.temadiplomes.doctorfinder.dao.OdaEMjekeveKSRepository;
import com.temadiplomes.doctorfinder.entity.OdaEMjekeveKS;

@Service
public class OdaEMjekeveKSServiceImpl implements OdaEMjekeveKSService {
	
	@Autowired
	private OdaEMjekeveKSRepository odaMjekeveRepo;

	@Override
	public List<OdaEMjekeveKS> findAll() {
		
		return odaMjekeveRepo.findAllByOrderByEmriAsc();
	}

	@Override
	public OdaEMjekeveKS findById(int theId) {
		
		Optional<OdaEMjekeveKS> result = odaMjekeveRepo.findById(theId);
		
		OdaEMjekeveKS theOMKS = null;
		
		if(result.isPresent()) {
			theOMKS = result.get();
		}else {
			throw new RuntimeException("Did not find the OdaMJekeveKs id - " + theId);
		}
		
		return theOMKS;
	}

	@Override
	public void save(OdaEMjekeveKS theOMKS) {
		
		odaMjekeveRepo.save(theOMKS);
		
	}

	@Override
	public void deleteById(int theId) {
		
		odaMjekeveRepo.deleteById(theId);

	}

	@Override
	public Page<OdaEMjekeveKS> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : 
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1,  pageSize, sort);
		return this.odaMjekeveRepo.findAll(pageable);
	}

}
