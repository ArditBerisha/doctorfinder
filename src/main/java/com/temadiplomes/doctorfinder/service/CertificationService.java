package com.temadiplomes.doctorfinder.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.temadiplomes.doctorfinder.entity.Certification;

public interface CertificationService {

	public List<Certification> findAll();
	
	public Certification findById(int theId);
	
	public void save(Certification theCertification);
	
	public void deleteById(int theId);
	
	public Page<Certification> findPaginated(int pageNo, int pageSize, String sortField, String sortDir);
}
