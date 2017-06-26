package com.luiz.entity;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.luiz.util.Reader;

public class Manager {

	public static Manager instance;			// Singleton design pattern used to avoid two or more instances of Manager in the same context
	
	private Reader reader;					// A reference the util class that is responsible to read the file
	private Plateau plateau;				// Information about the length of the Mars plateau, both on the x-axis and y-axis
	private ArrayList<Rover> rovers;		// A collection of rovers
	
	// Make the constructor private so the static method getInstance is used instead
	private Manager(String fileName) throws FileNotFoundException
	{
		reader = new Reader(fileName);
		rovers = new ArrayList<Rover>();
	}
	
	/**
	 * Get the unique instance of this Manager class
	 * */
	public static Manager getInstance(String fileName)
	{
		// If no instance of Manager has been created yet, create a new one
		// Otherwise, use the manager that has already been instantiated
		if(instance == null)
		{
			try {
				instance = new Manager(fileName);
			} catch (FileNotFoundException e) {
				System.out.println("Error: Manager could not be created because the file you specified could not be found.");
			}
		}
		
		return instance;
	}
	
	/**
	 * Overloaded method, it will try to find a text file named test
	 * */
	public static Manager getInstance()
	{
		if(instance == null)
		{
			try {
				instance = new Manager("data");
			} catch (FileNotFoundException e) {
				System.out.println("Error: Manager could not be created because the file you specified could not be found.");
			}
		}
		
		return instance;
	}
	
	public void init() throws Exception
	{
		// Initialize the plateau
		initPlateau();
		
		// Initialize the rovers
		initRovers();
	}
	
	/**
	 * Perform all the commands for each rover of the manager
	 * */
	public void start()
	{
		for(int i = 0; i < rovers.size(); i++)
		{
			Rover rover = rovers.get(i);	
			rover.performCommands();
		}
		
	}
	
	/**
	 * Initialize the plateau information
	 * */
	public void initPlateau()
	{
		// Read the first line of the file
		// Use of regex in case there are more spaces between the data
		String[] pos = reader.readLine().split("\\s+");
		plateau = new Plateau(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]));
	}
	
	/**
	 * Initialize as many rovers as it is specified in the file
	 * @throws Exception if no commands in the file have been found for the rover
	 * */
	private void initRovers() throws Exception
	{
		String roverPos;
		String roverCommands;
		
		// Take the next rover information (provided by two lines)
		while((roverPos = reader.readLine()) != null){
			
			// Check if there is a second line for the rover's commands
			if((roverCommands = reader.readLine()) != null)
			{
				// Add the new created rover
				rovers.add(createRover(roverPos, roverCommands));
			}
			else
			{
				//In case there is no line for the rover's commands
				throw new Exception("There are no commands for this rover. Mission aborted!");
			}
		}
	}
	
	/**
	 * Create a rover from the info provided in the location string and the commands string
	 * */
	public Rover createRover(String location, String commands)
	{
		// Retrieve the information contained in the 'location'
		String[] info = location.split("\\s+");
		int posX = Integer.parseInt(info[0]);
		int posY = Integer.parseInt(info[1]);
		char orientation = info[2].charAt(0);
		
		// Create a new rover according to the information retrieved previously
		Rover rover = new Rover(posX, posY, orientation, commands);
		return rover;
		
	}
	
	// GETTER
	public Plateau getPlateau(){
		return this.plateau;
	}
	
	// GETTER
	public ArrayList<Rover> getRovers(){
		return rovers;
	}
	
}
