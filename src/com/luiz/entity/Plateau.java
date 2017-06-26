package com.luiz.entity;

public class Plateau {

	private final int defaultRows = 5;			// Default number of rows if none is specified
	private final int defaultColumns = 5;		// Default number of columns if none is specified
	
	private boolean[][] grid;					// A grid to keep track which cells are occupied
	private int rows;							// Number of rows
	private int columns;						// Number of columns
	
	public Plateau(int rows, int columns)
	{
		// valid positions goes from (0, 0) to (rows, columns) inclusive
		setRows(rows + 1);
		setColumns(columns + 1);
		
		grid = new boolean[this.rows][this.columns];
		
		init();
	}
	
	/**
	 * Set all positions of the grid to Available
	 * */
	public void init()
	{
		for(int i = 0; i < this.rows; i++)
		{
			for(int j = 0; j < this.columns; j++)
			{
				grid[i][j] = true;
			}
		}
	}

	// GETTER
	public int getMaxRows() {
		return rows;
	}

	// GETTER
	public int getMaxColumns() {
		return columns;
	}

	// GETTER
	public boolean[][] getGrid(){
		return this.grid;
	}
	
	// SETTER
	private void setRows(int rows) {
		if(rows > 0)
			this.rows = rows;
		else
			this.rows = this.defaultRows;
	}

	// SETTER
	private void setColumns(int columns) {
		if(columns > 0)
			this.columns = columns;
		else
			this.columns = this.defaultColumns;
	}
	
	/**
	 * Check if the specified location is available
	 * */
	public boolean isEmptyCell(int posX, int posY)
	{
		return grid[posX][posY];
	}
	
	/**
	 * Occupy the cell at the specified position
	 * */
	public void takeCell(int posX, int posY)
	{
		grid[posX][posY] = false;
	}
	
	
	
}
