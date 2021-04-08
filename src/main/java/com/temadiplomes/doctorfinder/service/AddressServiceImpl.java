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

import com.temadiplomes.doctorfinder.dao.AddressRepository;
import com.temadiplomes.doctorfinder.entity.Address;
import com.temadiplomes.doctorfinder.entity.Ordinance;
import com.temadiplomes.doctorfinder.entity.Users;
import com.temadiplomes.doctorfinder.security.UsersServiceImpl;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UsersServiceImpl userService;
	
	
	@Override
	public List<Address> findAll() {
	
		return addressRepository.findAll();
	}

	@Override
	public Address findById(int theId) {
		Optional<Address> result = addressRepository.findById(theId);
		Address address = null;
		if(result.isPresent()) {
			address = result.get();
		} else {
			return null;
		}
		
		return address;
	}

	@Override
	public void save(Address theAddress) {

		addressRepository.save(theAddress);

	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<Address> findPaginated(Ordinance ordinance, int pageNo, int pageSize, String sortField, String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

		return this.addressRepository.findByOrdinance(ordinance, pageable);
	}
	
}
