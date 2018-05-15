package com.services.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.lang3.StringUtils;

public class EntityListener {
	
	@PrePersist
	protected void onCreate(GenericEntity entity) {
		entity.setCreationDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		entity.setVersionDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		
		if (StringUtils.isEmpty(entity.getUniqueId())) {
			entity.setUniqueId(UUID.randomUUID().toString());
	    }
	}

	@PreUpdate
	protected void onUpdate(GenericEntity entity) {
		entity.setVersionDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
	}
}