package com.temadiplomes.doctorfinder.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users_detail")
public class UsersDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="biography")
	private String biography;
	
	@Column(name="photo_path", columnDefinition = "BLOB")
	private String photoPath;

	
	public UsersDetail() {
		
	}


	public UsersDetail(String biography, String photoPath) {
		super();
		this.biography = biography;
		this.photoPath = photoPath;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getBiography() {
		return biography;
	}


	public void setBiography(String biography) {
		this.biography = biography;
	}


	public String getPhotoPath() {
		return photoPath;
	}


	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}


	@Override
	public String toString() {
		return "UsersDetail [id=" + id + ", biography=" + biography + ", photoPath=" + photoPath + "]";
	}
	
	
}
