package com.temadiplomes.doctorfinder.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.temadiplomes.doctorfinder.entity.Users;

import enums.Status;

public interface UsersRepository extends JpaRepository<Users, Integer> {

	//public Optional<Users> findByEmail(String email);
	
	public Users findByEmail(String email);
	
	// add a method to sort by last name
	public List<Users> findAllByOrderByLastNameAsc();
	
	public Users findByUsername(String username);
	
	public Page<Users> findByEnabled(Status status, Pageable pageable);

	@Query("SELECT u from Users u join u.recDoctor rec Group By rec.doctor Order By AVG(rec.preference) DESC")
	public List<Users> findTop4Doctors(Pageable pageable);
	
}
