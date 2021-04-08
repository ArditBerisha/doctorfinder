package com.temadiplomes.doctorfinder.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import enums.Status;

@Entity
@Table(name="attribute")
public class Attribute {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="show_in_filter")
	private boolean showInFilter;
	
	@Column(name="is_range")
	private boolean isRange;
	
	@Column(name="created_on_date")
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
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="control_type_id")
	private ControlType controlType;
	
	@OneToMany(mappedBy="attribute",
			cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private List<AttributeValue> attributeValues;
	
	@OneToMany(mappedBy="attribute",
			cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private List<AttributeSpecification> attrSpec;
	
	public Attribute() {
		
	}

	public Attribute(String name, boolean showInFilter, boolean isRange, Date createdOnDate, Date lastModifiedOnDate,
			int createdByUserId, int lastModifiedByUserId, Status isDeleted) {
		this.name = name;
		this.showInFilter = showInFilter;
		this.isRange = isRange;
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
	
	public boolean isShowInFilter() {
		return showInFilter;
	}

	public boolean getIsShowInFilter() {
		return showInFilter;
	}

	public void setShowInFilter(boolean showInFilter) {
		this.showInFilter = showInFilter;
	}

	public boolean getIssRange() {
		return isRange;
	}

	public void setRange(boolean isRange) {
		this.isRange = isRange;
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
	
	public Status getDeleted() {
		return isDeleted;
	}

	public void setDeleted(Status isDeleted) {
		this.isDeleted = isDeleted;
	}

	public ControlType getControlType() {
		return controlType;
	}

	public void setControlType(ControlType controlType) {
		this.controlType = controlType;
	}

	public List<AttributeValue> getAttributeValues() {
		return attributeValues;
	}

	public void setAttributeValues(List<AttributeValue> attributeValues) {
		this.attributeValues = attributeValues;
	}

	public List<AttributeSpecification> getAttrSpec() {
		return attrSpec;
	}

	public void setAttrSpec(List<AttributeSpecification> attrSpec) {
		this.attrSpec = attrSpec;
	}
	
	public void addAttrSpec(AttributeSpecification theAttrSpec) {
		if(attrSpec == null) {
			attrSpec = new ArrayList<>();
		}
		
		attrSpec.add(theAttrSpec);
		theAttrSpec.setAttribute(this);
	}

	@Override
	public String toString() {
		return "Attribute [id=" + id + ", name=" + name + ", showInFilter=" + showInFilter + ", is_range=" + isRange
				+ ", createdOnDate=" + createdOnDate + ", lastModifiedOnDate=" + lastModifiedOnDate
				+ ", createdByUserId=" + createdByUserId + ", lastModifiedByUserId=" + lastModifiedByUserId
				+ ", is_deleted=" + isDeleted + "]";
	}
	
	
}
