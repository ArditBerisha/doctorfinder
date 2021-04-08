package com.temadiplomes.doctorfinder.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.temadiplomes.doctorfinder.entity.Authorities;
import com.temadiplomes.doctorfinder.entity.Users;

/**
 * @author Ardit Berisha
 *
 */
public class AuthenticatedUser extends org.springframework.security.core.userdetails.User
{

	private static final long serialVersionUID = 1L;
	private Users user;
	
	public AuthenticatedUser(Users user)
	{
		super(user.getEmail(), user.getPassword(), getAuthorities(user));
		this.user = user;
	}
	
	public Users getUser()
	{
		return user;
	}
	
	private static Collection<? extends GrantedAuthority> getAuthorities(Users user)
	{
		Set<String> roleAndPermissions = new HashSet<>();
		List<Authorities> roles = (List<Authorities>) user.getAuthorities();
		
		for (Authorities role : roles)
		{
			roleAndPermissions.add(role.getAuthority());
		}
		String[] roleNames = new String[roleAndPermissions.size()];
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(roleAndPermissions.toArray(roleNames));
		return authorities;
	}
}
