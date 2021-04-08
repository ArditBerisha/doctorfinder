package com.temadiplomes.doctorfinder.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.temadiplomes.doctorfinder.dao.CertificationRepository;
import com.temadiplomes.doctorfinder.entity.Certification;
import com.temadiplomes.doctorfinder.entity.Users;
import com.temadiplomes.doctorfinder.security.UsersServiceImpl;

@Service
public class CertificationServiceImpl implements CertificationService {

	@Autowired
	private CertificationRepository certificationRepo;
	
	@Autowired
	private UsersServiceImpl userService;
	
	@Override
	public List<Certification> findAll() {
		
		return certificationRepo.findAll();
	}

	@Override
	public Certification findById(int theId) {
		Optional<Certification> result = certificationRepo.findById(theId);
		Certification certification = null;
		if(result.isPresent()) {
			certification = result.get();
		} else {
			return null;
		}
		
		return certification;
	}

	@Override
	public void save(Certification theCertification) {
		
		certificationRepo.save(theCertification);

	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<Certification> findPaginated(int pageNo, int pageSize, String sortField, String sortDir) {
		
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

		return this.certificationRepo.findByUsers(currentUser(), pageable);
		
	}
	
	public Users currentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Users user = userService.findByUsername(userDetails.getUsername());
		return user;
	}

}
