package com.luiz.main;

import java.util.ArrayList;

import com.luiz.entity.Manager;
import com.luiz.entity.Rover;

public class Main {
	
	public static ArrayList<Rover> rovers;	// List of final positions and orientations of each rover

	public static void main(String[] args) 
	{
		try{
			// Instantiate a new manager
			Manager manager = Manager.getInstance("data");
			
			// Initialize its main variables
			manager.init();
			
			// Start performing the commands of each rover
			manager.start();
			
			rovers = manager.getRovers();
			
			// Print the result of each rover
			for(int i = 0; i < rovers.size(); i++)
			{
				Rover rover = rovers.get(i);
				
				System.out.println(rover.getPositionX() + " " + rover.getPositionY() + " " + rover.getOrientation());
			}
		
		}catch(Exception e){
			// In case there is a location data, but no commands for the rover are provided
			e.printStackTrace();
		}
	}

}
