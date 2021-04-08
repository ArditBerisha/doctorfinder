package com.temadiplomes.doctorfinder.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="attribute_value")
public class AttributeValue {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="value")
	private String value;
	
	@Column(name="created_on_date")
	private Date createdOnDate;
	
	@Column(name="last_modified_on_date")
	private Date latModifiedOnDate;
	
	@Column(name="created_by_user_id")
	private int createdByUserId;
	
	@Column(name="last_modified_by_user_id")
	private int lastModifiedByUserId;
	
	@Column(name="is_deleted")
	private boolean isDeleted;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="attribute_id")
	private Attribute attribute;
	
	@OneToMany(mappedBy="attributeValues",
			cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private List<AttributeSpecification> attrSpec;
	
	public AttributeValue() {
		
	}

	public AttributeValue(String value, Date createdOnDate, Date latModifiedOnDate, int createdByUserId,
			int lastModifiedByUserId, boolean isDeleted) {
		this.value = value;
		this.createdOnDate = createdOnDate;
		this.latModifiedOnDate = latModifiedOnDate;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getCreatedOnDate() {
		return createdOnDate;
	}

	public void setCreatedOnDate(Date createdOnDate) {
		this.createdOnDate = createdOnDate;
	}

	public Date getLatModifiedOnDate() {
		return latModifiedOnDate;
	}

	public void setLatModifiedOnDate(Date latModifiedOnDate) {
		this.latModifiedOnDate = latModifiedOnDate;
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

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
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
		theAttrSpec.setAttributeValues(this);
	}

	@Override
	public String toString() {
		return "AttributeValue [id=" + id + ", value=" + value + ", createdOnDate=" + createdOnDate
				+ ", latModifiedOnDate=" + latModifiedOnDate + ", createdByUserId=" + createdByUserId
				+ ", lastModifiedByUserId=" + lastModifiedByUserId + ", isDeleted=" + isDeleted + ", attribute="
				+ attribute + "]";
	}
}
