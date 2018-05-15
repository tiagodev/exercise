package com.services.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hamcrest.CoreMatchers;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import com.services.business.VehicleService;
import com.services.configuration.SpringActivator;
import com.services.dto.VehicleDTO;
import com.services.model.Vehicle;

@WebAppConfiguration
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = SpringActivator.class)
public class VehicleServiceTest {

	@Mock
	private EntityManager em;
	
	@Mock
	private TypedQuery<Vehicle> tq;
	
	@InjectMocks
	private VehicleService vehicleService;
	
	@Test
	public void testVehicleService() {
		VehicleDTO mockDto = new VehicleDTO();
		mockDto.setUniqueId(UUID.randomUUID().toString());
		
		Mockito.when(em.createNamedQuery(Matchers.anyString(), Matchers.any(Class.class))).thenReturn(tq);
		Mockito.when(tq.setParameter(Matchers.anyString(), Matchers.any(Class.class))).thenReturn(tq);
		Mockito.when(tq.getResultList()).thenReturn(new ArrayList<>(Arrays.asList(new Vehicle())));
		
		Assert.assertThat(vehicleService.getVehicles(), CoreMatchers.not(IsEmptyCollection.empty()));
		Assert.assertNotNull(vehicleService.getVehicleByUniqueId(mockDto.getUniqueId()));
		Assert.assertNotNull(vehicleService.createVehicle(new VehicleDTO()));
		Assert.assertThat(vehicleService.deleteVehicle(UUID.randomUUID().toString()), CoreMatchers.isA(VehicleDTO.class));
		Assert.assertNotNull(vehicleService.updateVehicle(mockDto));
	}
	
}
