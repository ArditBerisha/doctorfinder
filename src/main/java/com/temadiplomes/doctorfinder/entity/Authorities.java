package com.temadiplomes.doctorfinder.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="authorities")
public class Authorities {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="authority")
	private String authority;
	
	
	@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
		name="user_has_authorities",
		joinColumns=@JoinColumn(name="authority_id"),
		inverseJoinColumns=@JoinColumn(name="user_id")
		)
	private List<Users> users;
	
	public Authorities() {
		
	}

	public Authorities(String authority) {
		this.authority = authority;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Authorities [id=" + id + ", authority=" + authority + "]";
	}
	
	public void addUsers(Users user) {
		if(users == null) {
			users = new ArrayList<>();
		}
		users.add(user);
	}

}
