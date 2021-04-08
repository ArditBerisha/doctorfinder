package com.temadiplomes.doctorfinder.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="comments")
public class Comments {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="comment")
	private String comment;
	
	@ManyToOne
	@JoinColumn(name="created_by_user")
	private Users createdByUserId;
	
	@ManyToOne
	@JoinColumn(name="for_doctor")
	private Users dedicatedToDoctor;
	
	public Comments() {
		
	}

	public Comments(String comment) {
		this.comment = comment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	

	public Users getCreatedByUserId() {
		return createdByUserId;
	}

	public void setCreatedByUserId(Users createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

	public Users getDedicatedToDoctor() {
		return dedicatedToDoctor;
	}

	public void setDedicatedToDoctor(Users dedicatedToDoctor) {
		this.dedicatedToDoctor = dedicatedToDoctor;
	}

	@Override
	public String toString() {
		return "Comments [id=" + id + ", comment=" + comment + "]";
	}
	
}
