package com.temadiplomes.doctorfinder.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.temadiplomes.doctorfinder.entity.OdaEMjekeveKS;

public interface OdaEMjekeveKSRepository extends JpaRepository<OdaEMjekeveKS, Integer> {
	
	public List<OdaEMjekeveKS> findAllByOrderByEmriAsc();

}
