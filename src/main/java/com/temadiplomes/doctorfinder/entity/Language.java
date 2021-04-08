package com.temadiplomes.doctorfinder.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="languages")
public class Language {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="language_code")
	private String languageCode;
	
	@Column(name="language")
	private String language;
	
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
		name="user_has_languages",
		joinColumns=@JoinColumn(name="language_id"),
		inverseJoinColumns=@JoinColumn(name="user_id")
		)
	private List<Users> users;
	
	public Language() {
		
	}

	public Language(String languageCode, String language, List<Users> users) {
		this.languageCode = languageCode;
		this.language = language;
		this.users = users;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}
	
	public void addUser(Users user) {
		if(users == null) {
			users = new ArrayList<>();
		}
		users.add(user);
	}

	@Override
	public String toString() {
		return "Language [id=" + id + ", languageCode=" + languageCode + ", language=" + language + ", users=" + users
				+ "]";
	}
	
	
}
