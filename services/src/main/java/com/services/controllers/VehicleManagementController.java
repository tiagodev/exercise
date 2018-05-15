package com.services.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.services.business.VehicleService;
import com.services.dto.VehicleDTO;

@Controller
@RequestMapping(value="/vehicle-management")
public class VehicleManagementController {
	
	private final VehicleService vehicleService; 
	
	@Autowired
	public VehicleManagementController(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}
	
	@RequestMapping(value="/vehicles/{uniqueId}", method = RequestMethod.GET)
	public ResponseEntity<VehicleDTO> getVehicle(@PathVariable("uniqueId") String uniqueId) {
		return new ResponseEntity<VehicleDTO>(vehicleService.getVehicleByUniqueId(uniqueId), HttpStatus.OK);
	}
	
	@RequestMapping(value="/vehicles", method = RequestMethod.GET)
	public ResponseEntity<List<VehicleDTO>> listVehicles(){
		return new ResponseEntity<List<VehicleDTO>>(vehicleService.getVehicles(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/vehicles", method = {RequestMethod.POST})
	public ResponseEntity<VehicleDTO> createVehicle(@RequestBody VehicleDTO vehicleDTO) {
		return new ResponseEntity<VehicleDTO>(vehicleService.createVehicle(vehicleDTO), HttpStatus.OK);
	}
	
	@RequestMapping(value="/vehicles", method = {RequestMethod.PUT})
	public ResponseEntity<VehicleDTO> updateVehicle(@RequestBody VehicleDTO vehicleDTO) {
		return new ResponseEntity<VehicleDTO>(vehicleService.updateVehicle(vehicleDTO), HttpStatus.OK);
	}
	
	@RequestMapping(value="/vehicles/{uniqueId}", method = {RequestMethod.DELETE})
	public ResponseEntity<VehicleDTO> deleteVehicle(@PathVariable("uniqueId") String uniqueId) {
		return new ResponseEntity<VehicleDTO>(vehicleService.deleteVehicle(uniqueId), HttpStatus.OK);
	}
	
}
