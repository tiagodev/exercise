package com.services.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.services.configuration.SpringActivator;

@WebAppConfiguration
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = SpringActivator.class)
public class VehicleManagementControllerTest {
	
	// TODO
	
	private MockMvc mockMvc;
	
	@Test
	public void testVehicleManagementController() {		
		
	}
	
}
