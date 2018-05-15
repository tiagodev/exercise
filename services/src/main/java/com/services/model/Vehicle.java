package com.services.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

@Entity
@NamedQueries({
	@NamedQuery(
			name="Vehicle.getByUniqueId",
			query="select e from Vehicle e where e.uniqueId = :uniqueId"),
	@NamedQuery(
			name="Vehicle.getAll",
			query="from Vehicle e")
})
public class Vehicle extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String brand;
	private String model;
	private String licensePlate;
	private BigDecimal fuelConsumption;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_gen")
	@SequenceGenerator(name = "vehicle_gen", sequenceName = "vehicle_seq")
	public Long getId() {
		return this.id;
	}
	
	protected void setId(Long id) {
		this.id = id;
	}

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
