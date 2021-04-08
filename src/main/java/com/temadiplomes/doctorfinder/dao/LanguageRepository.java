package com.temadiplomes.doctorfinder.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.temadiplomes.doctorfinder.entity.Language;
import com.temadiplomes.doctorfinder.entity.Users;

public interface LanguageRepository extends JpaRepository<Language, Integer>{
	
	public List<Language> findByUsers(Users user);
	
	public Language findByLanguageCode(String langCode);
}
