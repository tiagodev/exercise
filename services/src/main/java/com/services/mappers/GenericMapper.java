package com.services.mappers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.services.dto.GenericDTO;
import com.services.model.GenericEntity;

public abstract class GenericMapper<T1 extends GenericDTO, T2 extends GenericEntity> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public abstract T1 map(T2 entity, EntityManager em);
	
	public abstract T2 map(T1 dto, T2 entity, EntityManager em);
	
	public List<T1> mapToDtos(List<T2> list, EntityManager em){
		List<T1> mappedList = new ArrayList<>();
		
		for(T2 o: list) {
			mappedList.add(map(o, em));
		}
		return mappedList;
	}
}
