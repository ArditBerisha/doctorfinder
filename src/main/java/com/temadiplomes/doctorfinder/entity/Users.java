package com.temadiplomes.doctorfinder.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import enums.Status;

@Entity
@Table(name="users")
@JsonIgnoreProperties(value = { "enabled", "phoneNumbers", "certifications", "education",
		"awards", "comments", "dedicatedTo", "authorities", "languages", "specCategory", "ordinances", "recUser", "recDoctor", "password", "firstName", "lastName",
		"email", "createdOnDate", "lastModifiedOnDate", "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "attrSpec", "id"})
public class Users {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="created_on_date")
	private Date createdOnDate;
	
	@Column(name="last_modified_on_date")
	private Date lastModifiedOnDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name="enabled")
	private Status enabled;
	
	@Column(name="accountNonExpired")
	private boolean accountNonExpired;
	
	@Column(name="accountNonLocked")
	private boolean accountNonLocked;
	
	@Column(name="credentialsNonExpired")
	private boolean credentialsNonExpired;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
		name="user_has_authorities",
		joinColumns=@JoinColumn(name="user_id"),
		inverseJoinColumns=@JoinColumn(name="authority_id")
		)
	private Collection<Authorities> authorities;

	public Users() {
		
	}


	public Users(String username, String password, String firstName, String lastName, String email, Date createdOnDate,
			Date lastModifiedOnDate, Status enabled, boolean accountNonExpired, boolean accountNonLocked,
			boolean credentialsNonExpired) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.createdOnDate = createdOnDate;
		this.lastModifiedOnDate = lastModifiedOnDate;
		this.enabled = enabled;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public Status getEnabled() {
		return enabled;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getCreatedOnDate() {
		return createdOnDate;
	}


	public void setCreatedOnDate(Date createdOnDate) {
		this.createdOnDate = createdOnDate;
	}


	public Date getLastModifiedOnDate() {
		return lastModifiedOnDate;
	}


	public void setLastModifiedOnDate(Date lastModifiedOnDate) {
		this.lastModifiedOnDate = lastModifiedOnDate;
	}


	public Status isEnabled() {
		return enabled;
	}


	public void setEnabled(Status enabled) {
		this.enabled = enabled;
	}


	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}


	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}


	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}


	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}


	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}


	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public Collection<Authorities> getAuthorities() {
		return authorities;
	}


	public void setAuthorities(List<Authorities> authorities) {
		this.authorities = authorities;
	}

	public void addAuthority(Authorities theAuthority) {
		
		if(authorities == null) {
			authorities = new ArrayList<>();
		}
		
		authorities.add(theAuthority);
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", createdOnDate=" + createdOnDate
				+ ", lastModifiedOnDate=" + lastModifiedOnDate + ", enabled=" + enabled + ", accountNonExpired="
				+ accountNonExpired + ", accountNonLocked=" + accountNonLocked + ", credentialsNonExpired="
				+ credentialsNonExpired + "]";
	}
	
}
