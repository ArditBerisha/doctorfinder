package com.temadiplomes.doctorfinder.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="attribute_specification")
public class AttributeSpecification {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="attribute_value")
	private String attributeValue;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="user_id")
	private Users user;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="attribute_id")
	private Attribute attribute;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="attribute_value_id")
	private AttributeValue attributeValues;
	
	public AttributeSpecification() {
		
	}

	public AttributeSpecification(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public AttributeValue getAttributeValues() {
		return attributeValues;
	}

	public void setAttributeValues(AttributeValue attributeValues) {
		this.attributeValues = attributeValues;
	}

	@Override
	public String toString() {
		return "AttributeSpecification [id=" + id + ", attributeValue=" + attributeValue + "]";
	}
	
}
