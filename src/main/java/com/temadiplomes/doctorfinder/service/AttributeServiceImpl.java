package com.temadiplomes.doctorfinder.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.temadiplomes.doctorfinder.dao.AttributeRepository;
import com.temadiplomes.doctorfinder.entity.Attribute;
import com.temadiplomes.doctorfinder.entity.ControlType;
import com.temadiplomes.doctorfinder.entity.Users;
import com.temadiplomes.doctorfinder.security.UsersServiceImpl;

import enums.Status;

@Service
public class AttributeServiceImpl implements AttributeService {

	@Autowired
	private AttributeRepository attributeRepository;

	@Autowired
	private UsersServiceImpl userService;

	@Autowired
	private ControlTypeServiceImpl cTService;

	@Override
	public List<Attribute> findAll() {

		return attributeRepository.findAllByOrderByNameAsc();
	}

	@Override
	public Attribute findById(int theId) {
		Optional<Attribute> result = attributeRepository.findById(theId);

		Attribute attr = null;
		if (result.isPresent()) {
			attr = result.get();
		} else {
			return null;
		}
		return attr;
	}

	@Override
	public void save(Attribute theAttribute) {

		theAttribute.setDeleted(Status.ACTIVE);
		attributeRepository.save(theAttribute);

	}

	@Override
	public void deleteById(int theId) {
		Optional<Attribute> result = attributeRepository.findById(theId);

		Attribute attr = null;
		if (result.isPresent()) {
			attr = result.get();
		} else {
			throw new RuntimeException("Did not find Attribute id - " + theId);
		}
		attr.setDeleted(Status.INACTIVE);
		attributeRepository.save(attr);

	}

	@Override
	public Page<Attribute> findPaginated(int pageNo, int pageSize, String sortField, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

		return this.attributeRepository.findByIsDeleted(Status.ACTIVE,pageable);
	}

	@Override
	public String findControlTypeName(int theId) {
		ControlType result = cTService.findById(theId);

		if (result == null) {

			throw new RuntimeException("Did not find ControlType id - " + theId);

		}

		return result.getName();
	}

}
