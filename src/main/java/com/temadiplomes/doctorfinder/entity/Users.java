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
@JsonIgnoreProperties(value = { "enabled","usersDetail", "phoneNumbers", "certifications", "education", 
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
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_biography")
	private UsersDetail usersDetail;
	
	@ManyToMany(fetch=FetchType.LAZY,
				cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
		name="user_has_phone",
		joinColumns=@JoinColumn(name="user_id"),
		inverseJoinColumns=@JoinColumn(name="phone_id")
		)
	private List<PhoneNumber> phoneNumbers;
	
	
	@ManyToMany(fetch=FetchType.LAZY,
				cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
		name="user_has_certification",
		joinColumns=@JoinColumn(name="user_id"),
		inverseJoinColumns=@JoinColumn(name="certification_id")
		)
	private List<Certification> certifications;
	
	
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
		name="usar_has_education",
		joinColumns=@JoinColumn(name="user_id"),
		inverseJoinColumns=@JoinColumn(name="education_id")
		)
	private List<Education> education;
	
	
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
		name="user_has_award",
		joinColumns=@JoinColumn(name="user_id"),
		inverseJoinColumns=@JoinColumn(name="award_id")
		)
	private List<Award> awards;
	
	
	@OneToMany(fetch=FetchType.LAZY ,cascade=CascadeType.ALL)
	@JoinColumn(name="created_by_user")
	private List<Comments> comments;
	
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="for_doctor")
	private List<Comments> dedicatedTo;
	
	
	@ManyToMany(fetch = FetchType.EAGER,cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
		name="user_has_authorities",
		joinColumns=@JoinColumn(name="user_id"),
		inverseJoinColumns=@JoinColumn(name="authority_id")
		)
	private Collection<Authorities> authorities;
	
	@OneToMany(mappedBy="user",
			cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private List<AttributeSpecification> attrSpec;
	
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
		name="user_has_languages",
		joinColumns=@JoinColumn(name="user_id"),
		inverseJoinColumns=@JoinColumn(name="language_id")
		)
	private List<Language> languages;
	
	@ManyToMany
	@JoinTable(
		name="user_spec_category",
		joinColumns=@JoinColumn(name="user_id"),
		inverseJoinColumns=@JoinColumn(name="category_id")
		)
	private List<SpecialitiesCategory> specCategory;
	
	@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
		name="user_has_ordinance",
		joinColumns=@JoinColumn(name="user_id"),
		inverseJoinColumns=@JoinColumn(name="ordinance_id")
		)
	private List<Ordinance> ordinances;
	
	
	/*@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
		name="recommended_mahout_table",
		joinColumns=@JoinColumn(name="user_id"),
		inverseJoinColumns=@JoinColumn(name="doctor_id")
		)
	private List<Users> recUser;*/
	
	
	/*@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
		name="recommended_mahout_table",
		joinColumns=@JoinColumn(name="doctor_id"),
		inverseJoinColumns=@JoinColumn(name="user_id")
		)
	private List<Users> recDoctor;*/
	
	@OneToMany(mappedBy="user",
			cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private List<RecommendedMahoutTable> recUser;
	
	@OneToMany(mappedBy="doctor",
			cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private List<RecommendedMahoutTable> recDoctor;
	
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


	public UsersDetail getUsersDetail() {
		return usersDetail;
	}


	public void setUsersDetail(UsersDetail usersDetail) {
		this.usersDetail = usersDetail;
	}
	


	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}


	public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	

	public List<Certification> getCertifications() {
		return certifications;
	}

	public void setCertifications(List<Certification> certifications) {
		this.certifications = certifications;
	}

	public List<Education> getEducation() {
		return education;
	}


	public void setEducation(List<Education> education) {
		this.education = education;
	}

	public List<Award> getAwards() {
		return awards;
	}


	public void setAwards(List<Award> awards) {
		this.awards = awards;
	}


	public Collection<Authorities> getAuthorities() {
		return authorities;
	}


	public void setAuthorities(List<Authorities> authorities) {
		this.authorities = authorities;
	}


	public List<Comments> getComments() {
		return comments;
	}


	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}


	public List<Comments> getDedicatedTo() {
		return dedicatedTo;
	}


	public void setDedicatedTo(List<Comments> dedicatedTo) {
		this.dedicatedTo = dedicatedTo;
	}


	public List<AttributeSpecification> getAttrSpec() {
		return attrSpec;
	}


	public void setAttrSpec(List<AttributeSpecification> attrSpec) {
		this.attrSpec = attrSpec;
	}

	public List<Language> getLanguages() {
		return languages;
	}


	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}


	public List<SpecialitiesCategory> getSpecCategory() {
		return specCategory;
	}


	public void setSpecCategory(List<SpecialitiesCategory> specCategory) {
		this.specCategory = specCategory;
	}


	public List<Ordinance> getOrdinances() {
		return ordinances;
	}


	public void setOrdinances(List<Ordinance> ordinances) {
		this.ordinances = ordinances;
	}


	public List<RecommendedMahoutTable> getRecUser() {
		return recUser;
	}


	public void setRecUser(List<RecommendedMahoutTable> recUser) {
		this.recUser = recUser;
	}


	public List<RecommendedMahoutTable> getRecDoctor() {
		return recDoctor;
	}


	public void setRecDoctor(List<RecommendedMahoutTable> recDoctor) {
		this.recDoctor = recDoctor;
	}


	// te gjitha convenience methods m'i shkru pastaj ne service
	public void addPhoneNumber(PhoneNumber theNumber) {
		
		if(phoneNumbers == null) {
			phoneNumbers = new ArrayList<>();
		}
		
		phoneNumbers.add(theNumber);
	}
	
	public void addCertfication(Certification theCertification) {
		
		if(certifications == null) {
			certifications = new ArrayList<>();
		}
		
		certifications.add(theCertification);
	}
	
	public void addEducation(Education theEducation) {
		
		if(education == null) {
			education = new ArrayList<>();
		}
		
		education.add(theEducation);
	}
	
	public void addAward(Award theAward) {
		
		if(awards == null) {
			awards = new ArrayList<>();
		}
		
		awards.add(theAward);
	}
	
	public void addComment(Comments theComments) {
		
		if(comments == null) {
			comments = new ArrayList<>();
		}
		
		comments.add(theComments);
	}
	
	public void addDedicatedTo(Comments theDedicatedTo) {
		
		if(dedicatedTo == null) {
			dedicatedTo = new ArrayList<>();
		}
		
		dedicatedTo.add(theDedicatedTo);
	}

	public void addAuthority(Authorities theAuthority) {
		
		if(authorities == null) {
			authorities = new ArrayList<>();
		}
		
		authorities.add(theAuthority);
	}
	
	public void addAttrSpec(AttributeSpecification theAttrSpec) {
		if(attrSpec == null) {
			attrSpec = new ArrayList<>();
		}
		
		attrSpec.add(theAttrSpec);
		theAttrSpec.setUser(this);
	}
	
	public void addUserCat(SpecialitiesCategory theSpecCat) {
		
		if(specCategory == null) {
			specCategory = new ArrayList<>();
		}
		
		specCategory.add(theSpecCat);
	}
	
	public void addOrdinance(Ordinance theOrdinance) {
		
		if(ordinances == null) {
			ordinances = new ArrayList<>();
		}
		
		ordinances.add(theOrdinance);
	}
	
	public void addRecUser(Users theUser) {
		if(recUser == null) {
			recUser = new ArrayList<>();
		}
		
		//recUser.add(theUser);
		//theUser.setRecUser(theUser);
		
	}
	
	public void addRecDoctor(Users theDoctor) {
		if(recDoctor == null) {
			recDoctor = new ArrayList<>();
		}
		
		//recDoctor.add(theDoctor);
		//theDoctor.setDoctor(this);
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", createdOnDate=" + createdOnDate
				+ ", lastModifiedOnDate=" + lastModifiedOnDate + ", enabled=" + enabled + ", accountNonExpired="
				+ accountNonExpired + ", accountNonLocked=" + accountNonLocked + ", credentialsNonExpired="
				+ credentialsNonExpired + ", usersDetail=" + usersDetail + "]";
	}
	
}
