package com.temadiplomes.doctorfinder.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.temadiplomes.doctorfinder.dao.LanguageRepository;
import com.temadiplomes.doctorfinder.entity.Authorities;
import com.temadiplomes.doctorfinder.entity.Language;
import com.temadiplomes.doctorfinder.entity.Users;
import com.temadiplomes.doctorfinder.security.UsersServiceImpl;

@Service
public class LanguageServiceImpl implements LanguageService{

	@Autowired
	private LanguageRepository languageRepo;
	
	@Autowired 
	private UsersServiceImpl userService;
	
	@Override
	public List<Language> findAll() {
		return languageRepo.findAll();
	}

	@Override
	public Language findById(int theId) {
		Optional<Language> result = languageRepo.findById(theId);
		Language language = null;
		if(result.isPresent()) {
			language = result.get();
		} else {
			return null;
		}
		
		return language;
	}

	@Override
	public void save(Language lang) {
		
		languageRepo.save(lang);
		
	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Language findByLanguageCode(String langCode) {
		return languageRepo.findByLanguageCode(langCode);
	}

	@Override
	public List<Language> findByUsers(Users user) {
		return languageRepo.findByUsers(user);
	}
	
	@Override
	public void deleteLanguageFromUser(int userId, String code) {
		Users user = userService.findById(userId);
		
		Language lang = findByLanguageCode(code);
		user.getLanguages().remove(lang);
		userService.save(user);
		
	}

}
