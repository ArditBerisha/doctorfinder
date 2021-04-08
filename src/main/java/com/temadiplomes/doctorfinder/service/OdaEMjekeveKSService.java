package com.temadiplomes.doctorfinder.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.temadiplomes.doctorfinder.entity.OdaEMjekeveKS;

public interface OdaEMjekeveKSService {

	public List<OdaEMjekeveKS> findAll();
	
	public OdaEMjekeveKS findById(int theId);
	
	public void save(OdaEMjekeveKS theOMKS);
	
	public void deleteById(int theId);
	
	public Page<OdaEMjekeveKS> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
