package com.temadiplomes.doctorfinder.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.temadiplomes.doctorfinder.dao.UsersRepository;
import com.temadiplomes.doctorfinder.entity.Users;

/**
 * @author Ramesh Fadatare
 *
 */
@Service
@Transactional
public class CustomUserDetailsServiceToDel implements UserDetailsService {

	@Autowired
	private UsersRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
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
	
	
}
