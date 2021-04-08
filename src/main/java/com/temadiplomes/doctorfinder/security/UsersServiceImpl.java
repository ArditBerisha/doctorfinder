package com.temadiplomes.doctorfinder.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.temadiplomes.doctorfinder.dao.AuthoritiesRepository;
import com.temadiplomes.doctorfinder.dao.UsersRepository;
import com.temadiplomes.doctorfinder.entity.Authorities;
import com.temadiplomes.doctorfinder.entity.Users;
import com.temadiplomes.doctorfinder.entity.UsersDetail;

import enums.Status;

@Service
public class UsersServiceImpl implements CustomUserService {
	
	@Autowired
	private UsersRepository userRepository;
	
	@Autowired 
	private AuthoritiesRepository authorityRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Users findByUsername(String userName) {
		// check the database if the user already exists
		return userRepository.findByUsername(userName);
	}
	
	@Override
	public Users findByEmail(String email) {
		
		return userRepository.findByEmail(email);
	}


	@Override
	public List<Users> findAll() {
		
		return userRepository.findAllByOrderByLastNameAsc();
	}

	@Override
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

	@Override
	public void save(Users theUser) {
		Users user = new Users();
		
		//user.setId(theUser.getId());
		/*user.setUsername(theUser.getUsername());
		user.setPassword(passwordEncoder.encode(theUser.getPassword()));
		user.setFirstName(theUser.getFirstName());
		user.setLastName(theUser.getLastName());
		user.setEmail(theUser.getEmail());
		user.setEnabled(Status.ACTIVE);
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setUsersDetail(theUser.getUsersDetail());
		// give user default role of "employee"
		user.setAuthorities(Arrays.asList(authorityRepository.findRoleByAuthority("ROLE_EMPLOYEE")));*/
		//user.addAuthority(authorityRepository.findRoleByAuthority("ROLE_EMPLOYEE"));
		
		userRepository.save(theUser);
		
	}
	
	public void update(Users theUser) {
		userRepository.save(theUser);
	}

	@Override
	public void deleteById(int theId) {

		Optional<Users> result = userRepository.findById(theId);
		
		Users user = null;
		
	
		if(result.isPresent()) {
			
			user = result.get();
			
		}else {
			
			throw new RuntimeException("Did not find user id - " + theId);
		}
		
		user.setEnabled(Status.INACTIVE);
		userRepository.save(user);
	}


	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Users user = userRepository.findByUsername(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				mapRolesToAuthorities(user.getAuthorities()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Authorities> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toList());
	}


	@Override
	public Page<Users> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending():
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.userRepository.findByEnabled(Status.ACTIVE,pageable);
	}


	@Override
	public void addAuthority(String username, String authName) {
		Users user = userRepository.findByUsername(username);
		
		Authorities auth = authorityRepository.findRoleByAuthority(authName);
		user.addAuthority(auth);
		userRepository.save(user);
		
	}
	
	@Override
	public void deleteAuthFromUser(String username, String authName) {
		Users user = userRepository.findByUsername(username);
		
		Authorities auth = authorityRepository.findRoleByAuthority(authName);
		user.getAuthorities().remove(auth);
		userRepository.save(user);
		
	}

	@Override
	public void addUserDetails(UsersDetail userDetail) {
		
		
	}

	@Override
	public List<Users> findTop4Doctors(Pageable pageable) {
		
		return userRepository.findTop4Doctors(pageable);
	}
	
	

}
