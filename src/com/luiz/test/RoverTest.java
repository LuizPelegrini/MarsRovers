package com.luiz.test;

import org.junit.Test;

import com.luiz.entity.Manager;
import com.luiz.entity.Rover;

import junit.framework.TestCase;

public class RoverTest extends TestCase{

	@Test
	public void testSetOrientation()
	{
		Rover rover = new Rover(0, 0, 'N', "LLMMRR");
		
		assertEquals(rover.getOrientation(), 'N');
	}
	
	@Test
	public void testTurnLeft()
	{
		Rover rover = new Rover(0, 0, 'N', "LLMMRR");
		
		rover.turnLeft();
		assertEquals('W', rover.getOrientation());
		rover.turnLeft();
		assertEquals('S', rover.getOrientation());
		rover.turnLeft();
		assertEquals('E', rover.getOrientation());
		rover.turnLeft();
		assertEquals('N', rover.getOrientation());
	}
	
	@Test
	public void testTurnRight()
	{
		Rover rover = new Rover(0, 0, 'N', "LLMMRR");
		
		rover.turnRight();
		assertEquals('E', rover.getOrientation());
		rover.turnRight();
		assertEquals('S', rover.getOrientation());
		rover.turnRight();
		assertEquals('W', rover.getOrientation());
		rover.turnRight();
		assertEquals('N', rover.getOrientation());
	}
	
	@Test
	public void testMove()
	{
		int previousPosX;
		int previousPosY;

		Rover rover = new Rover(0, 0, 'N', "LLMMRR");
		previousPosX = rover.getPositionX();
		previousPosY = rover.getPositionY();
		
		// Move two cells upwards
		rover.move();
		rover.move();
		
		// Check
		assertEquals(previousPosY + 2, rover.getPositionY());
		
		// Turn left and move
		rover.turnLeft();
		rover.move();
		
		// No change, since the rover can't get out from the plateau
		assertEquals(previousPosX, rover.getPositionX());
		
		// Turn right twice to face East
		rover.turnRight();
		rover.turnRight();
		
		// Move two cells to the right
		rover.move();
		rover.move();
		
		// Now the rover can move since it will not get out from the plateau
		assertEquals(previousPosX + 2, rover.getPositionX());
	}
	
	/**
	 * Notice: I purposely let this test fail
	 * */
	@Test
	public void testCanMove()
	{
		Manager manager = Manager.getInstance();
		
		Rover roverA = manager.createRover("0 0 N", "RMMLM");
		Rover roverB = manager.createRover("0 0 N", "");
		
		assertTrue("Invalid position (either it is out of the plateau or occupied by a rover)", roverA.canMove(0, 0));
	
		// Perform the movement commands of RoverA
		// Final position (2, 1)		
		roverA.performCommands();
		
		// Try to move RoverB to where RoverA is
		// I purposely let this to show the right behaviour of the program
		assertTrue("Invalid position (either it is out of the plateau or occupied by a rover)", roverB.canMove(2, 1));
	}
	
	@Test
	public void testPerformCommands()
	{
		int finalPosX = 1;
		int finalPosY = 1;
		
		Manager manager = Manager.getInstance();
		
		Rover roverA = manager.createRover("0 0 N", "RMLM");
		
		// Perform the commands
		// Final position (1, 1)
		roverA.performCommands();
		
		// Check
		assertEquals(finalPosX, roverA.getPositionX());
		assertEquals(finalPosY, roverA.getPositionY());
	}
}
