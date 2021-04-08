package com.temadiplomes.doctorfinder.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.temadiplomes.doctorfinder.dao.AuthoritiesRepository;
import com.temadiplomes.doctorfinder.dao.UsersRepository;
import com.temadiplomes.doctorfinder.entity.Authorities;
import com.temadiplomes.doctorfinder.entity.Users;

@Service
public class AuthoritiesServiceImpl implements AuthoritiesService {
	
	@Autowired
	private AuthoritiesRepository authoritiesRepository;
	
	@Override
	public List<Authorities> findAll() {
	
		return authoritiesRepository.findAll();
	}

	@Override
	public Authorities findById(int theId) {
		Optional<Authorities> result = authoritiesRepository.findById(theId);
		
		Authorities auth = null;
		
		if(result.isPresent()) {
			auth = result.get();
		}
		else {
			throw new RuntimeException("Did not find authority id - " + theId);
		}
		return auth;
	}

	@Override
	public void save(Authorities theAuthority) {
		
		Authorities auth = new Authorities();
		auth.setAuthority(theAuthority.getAuthority());
		authoritiesRepository.save(auth);

	}

	@Override
	public void deleteById(int theId) {
	

	}

	@Override
	public Page<Authorities> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending():
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		
		return this.authoritiesRepository.findAll(pageable);
	}

}
