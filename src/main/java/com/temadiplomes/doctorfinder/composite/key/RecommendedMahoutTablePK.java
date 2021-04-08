package com.temadiplomes.doctorfinder.composite.key;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class RecommendedMahoutTablePK implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected int createdByUSer;
	
	protected int dedicatedToDoctor;
	
	public RecommendedMahoutTablePK() {
		
	}

	public RecommendedMahoutTablePK(int createdByUSer, int dedicatedToDoctor) {
		this.createdByUSer = createdByUSer;
		this.dedicatedToDoctor = dedicatedToDoctor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + createdByUSer;
		result = prime * result + dedicatedToDoctor;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecommendedMahoutTablePK other = (RecommendedMahoutTablePK) obj;
		if (createdByUSer != other.createdByUSer)
			return false;
		if (dedicatedToDoctor != other.dedicatedToDoctor)
			return false;
		return true;
	}
	
	
}
