package com.services.business;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.services.dto.VehicleDTO;
import com.services.mappers.VehicleMapper;
import com.services.model.Vehicle;

@Service
@Transactional
public class VehicleService {
	
	@PersistenceContext
	private EntityManager em;
	
	public VehicleDTO getVehicleByUniqueId(String uniqueId) {
		if(StringUtils.isEmpty(uniqueId)) {
			return null;
		}
		
		TypedQuery<Vehicle> q = em.createNamedQuery("Vehicle.getByUniqueId", Vehicle.class);
		q.setParameter("uniqueId", uniqueId);
		
		List<Vehicle> vehicles = q.getResultList();
		if(!CollectionUtils.isEmpty(vehicles)) {
			return new VehicleMapper().map(vehicles.get(0), em);
		}
		
		return null;
	}
	
	public List<VehicleDTO> getVehicles() {
		return new VehicleMapper().mapToDtos(em.createNamedQuery("Vehicle.getAll", Vehicle.class).getResultList(), em);
	}

	public VehicleDTO createVehicle(VehicleDTO vehicleDTO) {
		Vehicle vehicle = new Vehicle();
		new VehicleMapper().map(vehicleDTO, vehicle, em);
		em.persist(vehicle);
		return new VehicleMapper().map(vehicle, em);
	}
	
	public VehicleDTO updateVehicle(VehicleDTO vehicleDTO) {
		if(StringUtils.isEmpty(vehicleDTO.getUniqueId())){
			return null;
		}
		
		TypedQuery<Vehicle> q = em.createNamedQuery("Vehicle.getByUniqueId", Vehicle.class);
		q.setParameter("uniqueId", vehicleDTO.getUniqueId());
		
		List<Vehicle> vehicles = q.getResultList();
		if(!CollectionUtils.isEmpty(vehicles)) {
			Vehicle vehicle = vehicles.get(0);
			new VehicleMapper().map(vehicleDTO, vehicle, em);
			em.merge(vehicle);
			return new VehicleMapper().map(vehicle, em);
		}
		
		return null;
	}
	
	public VehicleDTO deleteVehicle(String uniqueId) {		
		if(StringUtils.isEmpty(uniqueId)){
			return null;
		}
		
		TypedQuery<Vehicle> q = em.createNamedQuery("Vehicle.getByUniqueId", Vehicle.class);
		q.setParameter("uniqueId", uniqueId);
		
		List<Vehicle> vehicles = q.getResultList();
		if(!CollectionUtils.isEmpty(vehicles)) {
			em.remove(vehicles.get(0));
			return new VehicleMapper().map(vehicles.get(0), em);
		}
		
		return null;
	}
}
