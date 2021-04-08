package com.temadiplomes.doctorfinder.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.temadiplomes.doctorfinder.entity.Authorities;

public interface AuthoritiesService {

	public List<Authorities> findAll();
	
	public Authorities findById(int theId);
	
	public void save(Authorities theAuthority);
	
	public void deleteById(int theId);
	
	public Page<Authorities> findPaginated(int pageNo, int pageSize, String sortField, String sortDir);
}
