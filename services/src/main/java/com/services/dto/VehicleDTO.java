package com.services.dto;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class VehicleDTO extends GenericDTO {

	private static final long serialVersionUID = 1L;
	
	private String brand;
	private String model;
	private String licensePlate;
	private BigDecimal fuelConsumption;
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getLicensePlate() {
		return licensePlate;
	}
	
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	
	public BigDecimal getFuelConsumption() {
		return fuelConsumption;
	}
	
	public void setFuelConsumption(BigDecimal fuelConsumption) {
		this.fuelConsumption = fuelConsumption;
	}	
}
