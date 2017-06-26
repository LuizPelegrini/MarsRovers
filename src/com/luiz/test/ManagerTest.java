package com.luiz.test;

import org.junit.Test;

import com.luiz.entity.Manager;
import com.luiz.entity.Rover;

import junit.framework.TestCase;

public class ManagerTest extends TestCase{

	@Test
	public void testCreateRover()
	{
		Manager manager = Manager.getInstance();
		
		assertEquals(manager.getRovers().size(), 0);
		
		Rover rover = manager.createRover("1 2 N", "LRMMLRMRR");
		
		manager.getRovers().add(rover);
		
		assertEquals(manager.getRovers().size(), 1);
	}
	
	@Test
	public void testInitPlateau()
	{
		Manager manager = Manager.getInstance();
		
		assertNull(manager.getPlateau());
		
		manager.initPlateau();
		
		assertNotNull(manager.getPlateau());
	}
	
}
