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
@Table(name="certifications")
public class Certification {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title_of_certification")
	private String titleOfCertification;
	
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
		name="user_has_certification",
		joinColumns=@JoinColumn(name="certification_id"),
		inverseJoinColumns=@JoinColumn(name="user_id")
		)
	private List<Users> users;
	
	public Certification() {
		
	}

	public Certification(String titleOfCertification) {
		this.titleOfCertification = titleOfCertification;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitleOfCertification() {
		return titleOfCertification;
	}

	public void setTitleOfCertification(String titleOfCertification) {
		this.titleOfCertification = titleOfCertification;
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
	
}
