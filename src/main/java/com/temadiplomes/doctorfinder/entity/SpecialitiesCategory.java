package com.temadiplomes.doctorfinder.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import enums.Status;

@Entity
@Table(name="specialities_category")
public class SpecialitiesCategory {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	//@Column(name="parent_id")
	//private int parentId;
	
	@Column(name="description")
	private String description;
	
	@Column(name="display_order")
	private int displayOrder;
	
	@Column(name="is_published")
	private boolean isPublished;
	
	@Column(name="cretaed_on_date")
	private Date createdOnDate;
	
	@Column(name="last_modified_on_date")
	private Date lastModifiedOnDate;
	
	@Column(name="created_by_user_id")
	private int createdByUserId;
	
	@Column(name="last_modified_by_user_id")
	private int lastModifiedByUserId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="is_deleted")
	private Status isDeleted;
	
	@ManyToOne
	@JoinColumn(name="parent_id")
	private SpecialitiesCategory specCat;
	
	@OneToMany(mappedBy="specCat")
	private List<SpecialitiesCategory> inverseParent;
	
	@ManyToMany
	@JoinTable(
		name="user_spec_category",
		joinColumns=@JoinColumn(name="category_id"),
		inverseJoinColumns=@JoinColumn(name="user_id")
		)
	private List<Users> users;
	
	public SpecialitiesCategory() {
		
	}

	public SpecialitiesCategory(String name, String description, int displayOrder, boolean isPublished,
			Date createdOnDate, Date lastModifiedOnDate, int createdByUserId, int lastModifiedByUserId,
			Status isDeleted) {
		this.name = name;
		this.description = description;
		this.displayOrder = displayOrder;
		this.isPublished = isPublished;
		this.createdOnDate = createdOnDate;
		this.lastModifiedOnDate = lastModifiedOnDate;
		this.createdByUserId = createdByUserId;
		this.lastModifiedByUserId = lastModifiedByUserId;
		this.isDeleted = isDeleted;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public void setPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}
	
	public boolean isPublished() {
		return isPublished;
	}

	public boolean getIsPublished() {
		return isPublished;
	}

	public void setIsPublished(boolean isPublished) {
		this.isPublished = isPublished;
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

	public int getCreatedByUserId() {
		return createdByUserId;
	}

	public void setCreatedByUserId(int createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

	public int getLastModifiedByUserId() {
		return lastModifiedByUserId;
	}

	public void setLastModifiedByUserId(int lastModifiedByUserId) {
		this.lastModifiedByUserId = lastModifiedByUserId;
	}

	public Status isDeleted() {
		return isDeleted;
	}

	public void setDeleted(Status isDeleted) {
		this.isDeleted = isDeleted;
	}

	public SpecialitiesCategory getSpecCat() {
		return specCat;
	}

	public void setSpecCat(SpecialitiesCategory specCat) {
		this.specCat = specCat;
	}

	public List<SpecialitiesCategory> getInverseParent() {
		return inverseParent;
	}

	public void setInverseParent(List<SpecialitiesCategory> inverseParent) {
		this.inverseParent = inverseParent;
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
		return "SpecialitiesCategory [id=" + id + ", name=" + name + ", description=" + description + ", displayOrder="
				+ displayOrder + ", isPublished=" + isPublished + ", createdOnDate=" + createdOnDate
				+ ", lastModifiedOnDate=" + lastModifiedOnDate + ", createdByUserId=" + createdByUserId
				+ ", lastModifiedByUserId=" + lastModifiedByUserId + ", isDeleted=" + isDeleted + ", specCat=" + /*specCat
				+*/ ", inverseParent=" + /*inverseParent + */", users=" + users + "]";
	}
	
	
}
