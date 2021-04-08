package com.temadiplomes.doctorfinder.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.temadiplomes.doctorfinder.entity.Authorities;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Integer> {

	public Authorities findRoleByAuthority(String theRoleName);
	
	public List<Authorities> findAllByOrderByAuthorityAsc();
}
