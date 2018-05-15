package com.services.mappers;

import javax.persistence.EntityManager;

import com.services.dto.VehicleDTO;
import com.services.model.Vehicle;

public class VehicleMapper extends GenericMapper<VehicleDTO, Vehicle>{

	private static final long serialVersionUID = 1L;

	@Override
	public VehicleDTO map(Vehicle entity, EntityManager em) {
		VehicleDTO dto = new VehicleDTO();
		
		dto.setBrand(entity.getBrand());
		dto.setModel(entity.getModel());
		dto.setLicensePlate(entity.getLicensePlate());
		dto.setFuelConsumption(entity.getFuelConsumption());
		
		dto.setUniqueId(entity.getUniqueId());
		dto.setId(entity.getId());
		dto.setVersionDate(entity.getVersionDate());
		dto.setCreationDate(entity.getCreationDate());
		dto.setVersionNumber(entity.getVersionNumber());
		
		return dto;
	}

	@Override
	public Vehicle map(VehicleDTO dto, Vehicle entity, EntityManager em) {
		if(entity == null) {
			return null;
		}
		
		entity.setBrand(dto.getBrand());
		entity.setModel(dto.getModel());
		entity.setLicensePlate(dto.getLicensePlate());
		entity.setFuelConsumption(dto.getFuelConsumption());
		
		entity.setCreationDate(dto.getCreationDate());
		entity.setVersionDate(dto.getVersionDate());
		entity.setVersionNumber(dto.getVersionNumber());
		
		return entity;
	}
}
