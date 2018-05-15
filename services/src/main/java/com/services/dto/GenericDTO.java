package com.services.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class GenericDTO implements Comparable<GenericDTO>, Serializable {

	private static final long serialVersionUID = 1L;
	
	private String uniqueId;
	
	@JsonIgnore
	private Long id;
	
	@JsonIgnore
	private Date creationDate;
	
	@JsonIgnore
	private Date versionDate;
	
	@JsonIgnore
	private Integer versionNumber;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUniqueId() {
		return uniqueId;
	}
	
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public Date getVersionDate() {
		return versionDate;
	}
	
	public void setVersionDate(Date versionDate) {
		this.versionDate = versionDate;
	}
	
	public Integer getVersionNumber() {
		return versionNumber;
	}
	
	public void setVersionNumber(Integer versionNumber) {
		this.versionNumber = versionNumber;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj == this) {
			return true;
		}

		return new EqualsBuilder()
				.append(this.getClass(), obj.getClass())
				.append(this.getUniqueId(), ((GenericDTO) obj).getUniqueId())
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31)
				.append(this.getClass().getName())
				.append(getUniqueId())
				.toHashCode();
	}

	@Override
	public int compareTo(GenericDTO obj) {
		return this.getUniqueId().compareTo(obj.getUniqueId());
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
