package com.luiz.entity;

public class Rover {
	
	/**
	 * Enumeration representing the orientations
	 * */
	enum Orientation{
		NORTH(0),
		EAST(1),
		SOUTH(2),
		WEST(3);
		
		private final int value;
		private Orientation(int value)
		{
			this.value = value;
		}
		
		public int getValue()
		{
			return this.value;
		}
	}
	
	private int positionX;													// Position of the rover in the x-axis
	private int positionY;													// Position of the rover in the y-axis
	private int currentOrientation;											// Current index of the orientations vector
	private String commands;												// String representing the commands' letters
	private int currentCommand;												// Index to loop through the string of commands
	
	private static final char[] orientations = {'N', 'E', 'S', 'W'};		// Util variable shared among instances of Rover to be easily used to extract the current rover's orientation 
	
	
	
	public Rover(int posX, int posY, char orientation, String commands)
	{	
		this.positionX = posX;
		this.positionY = posY;
		this.commands = commands;
		this.currentCommand = 0;

		// Set the rover's orientation to an integer value according to the character
		setOrientation(Character.toUpperCase(orientation));
	}
	
	/**
	 * This method will loop through the string of commands and perform each command separately
	 * */
	public void performCommands()
	{
		// Loop through the commands
		while(currentCommand < commands.length())
		{
			// For safety reasons, use of the static method toUpperCase in case one of the commands is in lower case
			char command = Character.toUpperCase(commands.charAt(currentCommand));
			
			switch(command)
			{
				case 'L':
					turnLeft();
					break;
				case 'R':
					turnRight();
					break;
				case 'M':
					move();
					break;
				default:
					System.out.println("Command not available!");
					break;
			}
			
			// Take the next command
			currentCommand++;
		}
		
		// This will not allow rovers to move through the cell currently occupied by another rover
		Manager.instance.getPlateau().takeCell(this.positionX, this.positionY);
	}
	
	/**
	 * Turn the rover orientation to the left
	 * */
	public void turnLeft()
	{
		// Wrap around in case the orientation index is pointing to the first element of the vector and the rover turns left
		if(this.currentOrientation - 1 < 0)
			this.currentOrientation = orientations.length - 1;
		else
			this.currentOrientation--;
	}
	
	/**
	 * Turn the rover orientation to the right
	 * */
	public void turnRight()
	{
		// Wrap around in case the orientation index is pointing to the last element of the vector and the rover turns right
		this.currentOrientation = (this.currentOrientation + 1) % orientations.length;
	}

	/**
	 * Make a move attempt 
	 * */
	public void move()
	{	
		// Get the orientation the rover is facing
		switch(getOrientation())
		{
			// (x, y + 1)
			case 'N':
				if(canMove(this.positionX, this.positionY + 1))
					setPositionY(this.positionY + 1);
				else
					System.out.println("The rover could not be moved to the desired cell!");
				break;
			// (x + 1, y)
			case 'E':
				if(canMove(this.positionX + 1, this.positionY))
					setPositionX(this.positionX + 1);
				else
					System.out.println("The rover could not be moved to the desired cell!");
				break;
			// (x, y - 1)
			case 'S':
				if(canMove(this.positionX, this.positionY - 1))
					setPositionY(this.positionY - 1);
				else
					System.out.println("The rover could not be moved to the desired cell!");
				break;
			// (x - 1, y)
			case 'W':
				if(canMove(this.positionX - 1, this.positionY))
					setPositionX(this.positionX - 1);
				else
					System.out.println("The rover could not be moved to the desired cell!");
				break;
		}
	}
	
	/**
	 * Check if the rover can move to the desired position
	 * */
	public boolean canMove(int posX, int posY)
	{
		// First, check if the rover is on the right or left edges of the plateau 
		// 0 <= x < row
		if(posX < 0 || posX > Manager.instance.getPlateau().getMaxRows() - 1)
			return false;
		
		// Then, check if the rover is on the top or bottom edges of the plateau
		// 0 <= y < column
		if(posY < 0 || posY > Manager.instance.getPlateau().getMaxColumns() - 1)
			return false;
		
		// If everything is fine, we also have to check if the desired position is already occupied by a rover
		return Manager.instance.getPlateau().isEmptyCell(posX, posY);
	}
	
	
	
	
	// GETTER
	public int getPositionX() {
		return positionX;
	}
	
	// GETTER
	public int getPositionY() {
		return positionY;
	}

	// GETTER
	public char getOrientation(){
		return orientations[this.currentOrientation];
	}

	// SETTER
	public void setPositionX(int posX){
		if(posX >= 0)
			this.positionX = posX;
	}
	
	// SETTER
	public void setPositionY(int posY){
		if(posY >= 0)
			this.positionY = posY;
	}
	
	// SETTER
	private void setOrientation(char orientation) {
		
		switch(orientation)
		{
			case 'N':
				this.currentOrientation = Orientation.NORTH.getValue();
				break;
			case 'E':
				this.currentOrientation = Orientation.EAST.getValue();
				break;
			case 'S':
				this.currentOrientation = Orientation.SOUTH.getValue();
				break;
			case 'W':
				this.currentOrientation = Orientation.WEST.getValue();
				break;
			default:
				System.out.println("Error: Invalid orientation!");
				break;
		}
	}
}
