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
@Table(name="education")
public class Education {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="school_name")
	private String schoolName;
	
	@Column(name="program")
	private String program;
	
	@Column(name="study_place")
	private String studyPlace;
	
	@Column(name="school_email")
	private String schoolEmail;
	
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
		name="usar_has_education",
		joinColumns=@JoinColumn(name="education_id"),
		inverseJoinColumns=@JoinColumn(name="user_id")
		)
	private List<Users> users;
	
	public Education() {
		
	}

	public Education(String schoolName, String program, String studyPlace, String schoolEmail) {
		this.schoolName = schoolName;
		this.program = program;
		this.studyPlace = studyPlace;
		this.schoolEmail = schoolEmail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getStudyPlace() {
		return studyPlace;
	}

	public void setStudyPlace(String studyPlace) {
		this.studyPlace = studyPlace;
	}

	public String getSchoolEmail() {
		return schoolEmail;
	}

	public void setSchoolEmail(String schoolEmail) {
		this.schoolEmail = schoolEmail;
	}
	

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Education [id=" + id + ", schoolName=" + schoolName + ", program=" + program + ", studyPlace="
				+ studyPlace + ", schoolEmail=" + schoolEmail + "]";
	}
	
	public void addUser(Users user) {
		if(users == null) {
			users = new ArrayList<>();
		}
		users.add(user);
	}
	
	
}
