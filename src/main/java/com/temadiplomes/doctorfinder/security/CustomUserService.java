package com.temadiplomes.doctorfinder.security;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.temadiplomes.doctorfinder.entity.SpecialitiesCategory;
import com.temadiplomes.doctorfinder.entity.Users;
import com.temadiplomes.doctorfinder.entity.UsersDetail;

public interface CustomUserService extends UserDetailsService{

	public List<Users> findAll();
	
	public Users findById(int theId);
	
	public void save(Users theUser);
	
	public void deleteById(int theId);
	
	public Users findByUsername(String userName);
	
	public Users findByEmail(String email);
	
	public void addAuthority(String username, String authName);
	
	public void addUserDetails(UsersDetail userDetail);
	
	public void deleteAuthFromUser(String username, String authName);
	
	public List<Users> findTop4Doctors(Pageable pageable);
	
	public Page<Users> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	
	public List<Users> findBySpecCategory(SpecialitiesCategory specCategory);

}
