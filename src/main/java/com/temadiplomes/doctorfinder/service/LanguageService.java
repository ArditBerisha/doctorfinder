package com.temadiplomes.doctorfinder.service;

import java.util.List;
import com.temadiplomes.doctorfinder.entity.Language;
import com.temadiplomes.doctorfinder.entity.Users;

public interface LanguageService {
	
public List<Language> findAll();
	
	public Language findById(int theId);
	
	public void save(Language lang);
	
	public void deleteById(int theId);
	
	public Language findByLanguageCode(String langCode);
	
	public List<Language> findByUsers(Users user);

	void deleteLanguageFromUser(int userId, String code);
	
}
