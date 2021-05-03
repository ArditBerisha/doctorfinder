
package com.temadiplomes.doctorfinder.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.temadiplomes.doctorfinder.composite.key.RecommendedMahoutTablePK;

@Entity
@Table(name="recommended_mahout_table")
public class RecommendedMahoutTable{

	
	
	//private static final long serialVersionUID = 1L;

	//@EmbeddedId
   // private RecommendedMahoutTablePK maohoutPK;
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="preference")
	private int preference;
	
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="user_id")
	private Users user;
	
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="doctor_id")
	private Users doctor;
	
	public RecommendedMahoutTable() {
		
	}

	public RecommendedMahoutTable(int preference, Users user, Users doctor) {
		this.preference = preference;
		this.user = user;
		this.doctor = doctor;
	}

	public RecommendedMahoutTable(int preference) {
		
		this.preference = preference;
	}

	public int getPreference() {
		return preference;
	}

	public void setPreference(int preference) {
		this.preference = preference;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Users getDoctor() {
		return doctor;
	}

	public void setDoctor(Users doctor) {
		this.doctor = doctor;
	}

	@Override
	public String toString() {
		return "RecommendedMahoutTable [preference=" + preference + ", user=" + user + ", doctor=" + doctor + "]";
	}
		
}


