package com.services.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.services.exception.ConcurrencyException;

//@DynamicUpdate
//@DynamicInsert
@MappedSuperclass
@EntityListeners(EntityListener.class)
public abstract class GenericEntity implements Comparable<GenericEntity>, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String uniqueId;
	private Date creationDate;
	private Date versionDate;
	private Integer versionNumber = 1; 
		
	@Column(nullable = false, updatable = false, unique = true)
	public String getUniqueId(){
		return this.uniqueId;
	}
	
	protected void setUniqueId(String uniqueId){
		this.uniqueId = uniqueId;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	public Date getVersionDate() {
		return versionDate;
	}

	public void setVersionDate(Date versionDate) {
		this.versionDate = versionDate;
	}
	
	@Version
	@Column(nullable = false)
	public Integer getVersionNumber() {
		return versionNumber;
	}
	
	public void setVersionNumber(Integer versionNumber) {
		this.versionNumber = versionNumber;
	}
	
	public void validateVersion(Integer versionNumber) throws ConcurrencyException {
		Integer v = versionNumber == null ? 1 : versionNumber;
		if (!this.versionNumber.equals(v)) {
            throw new ConcurrencyException(String.format("Optimistic lock for object %s and unique id %d", this.getClass().getSimpleName(), this.getUniqueId()));
        }
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		
		if(obj == this){
			return true;
		}

		return new EqualsBuilder()
					.append(this.getClass(), obj.getClass())
					.append(this.getUniqueId(), ((GenericEntity) obj).getUniqueId())
					.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31)
					.append(this.getUniqueId())
					.append(this.getClass().getName())
					.toHashCode();
	}
	
	@Override
	public int compareTo(GenericEntity obj) {
		return this.getVersionDate().compareTo(obj.getVersionDate());
	}
}
