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
@Table(name="ordinance")
public class Ordinance {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	
	@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
		name="ordinance_has_address",
		joinColumns=@JoinColumn(name="ordinance_id"),
		inverseJoinColumns=@JoinColumn(name="address_id")
		)
	private List<Address> address;
	
	@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
		name="user_has_ordinance",
		joinColumns=@JoinColumn(name="ordinance_id"),
		inverseJoinColumns=@JoinColumn(name="user_id")
		)
	private List<Users> users;
	
	public Ordinance() {
		
	}

	public Ordinance(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}

	public void addAddress(Address theAddress) {
		if(address == null) {
			address = new ArrayList<>();
		}
		
		address.add(theAddress);
	}
	
	public void addUser(Users user) {
		if(users == null) {
			users= new ArrayList<>();
		}
		
		users.add(user);
	}
	
	@Override
	public String toString() {
		return "Ordinance [id=" + id + ", name=" + name + "]";
	}
	
}
