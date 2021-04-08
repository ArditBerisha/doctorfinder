package com.temadiplomes.doctorfinder.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.temadiplomes.doctorfinder.dao.AuthoritiesRepository;
import com.temadiplomes.doctorfinder.dao.UsersRepository;
import com.temadiplomes.doctorfinder.entity.Users;

@Service
public class CustomUserDetailsServicee  {
	
	@Autowired
	private UsersRepository userRepository;
	
	@Autowired
	private AuthoritiesRepository authorityRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	
	public List<Users> findAll() {
		
		return userRepository.findAllByOrderByLastNameAsc();
	}

	
	public Users findById(int theId) {
		
		Optional<Users> result = userRepository.findById(theId);
		
		Users theUser = null;
		
		if(result.isPresent()) {
			
			theUser = result.get();
			
		}
		else {
			
			// we did not find the employee
			throw new RuntimeException("Did not find employee id - " + theId);
		}
		
		return theUser;
	}

	
	public void deleteById(int theId) {

		userRepository.deleteById(theId);

	}

	
	public void save(Users theUser) {

		Users user = new Users();
		user.setId(5);
		user.setUsername(theUser.getUsername());
		user.setPassword(passwordEncoder.encode(theUser.getPassword()));
		user.setFirstName(theUser.getFirstName());
		user.setLastName(theUser.getLastName());
		user.setEmail(theUser.getEmail());
		
		// give user default role of "employee"
		user.setAuthorities(Arrays.asList(authorityRepository.findRoleByAuthority("ROLE_EMPLOYEE")));
		
		userRepository.save(user);
		
	}

	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Users findByUsername(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Users user = userRepository.findByUsername(userName)
				.orElseThrow(() -> new UsernameNotFoundException("Email " + userName + " not found"));
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				getAuthorities(user));
	}

	private static Collection<? extends GrantedAuthority> getAuthorities(Users user) {
		String[] userRoles = user.getAuthorities().stream().map((role) -> role.getAuthority()).toArray(String[]::new);
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
		return authorities;
	}*/

	/*@Override
	public Users findByUserName(String userName) {
		Optional<Users> result = userRepository.findByUsername(userName);
		
		Users user = null;
		
		if(result.isPresent()) {
			user = result.get();
		} else {
			
			throw new RuntimeException("Did not find user - ");
		}
		return user;
		
	}*/

}
