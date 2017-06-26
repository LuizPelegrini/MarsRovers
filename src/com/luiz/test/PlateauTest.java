package com.luiz.test;

import org.junit.Test;

import com.luiz.entity.Plateau;

import junit.framework.TestCase;

public class PlateauTest extends TestCase{

	@Test
	public void testPlateau() {
		int rows = 5;
		int columns = 5;
		
		Plateau plateau = new Plateau(rows, columns);
		assertEquals(plateau.getMaxRows(), rows + 1);
		assertEquals(plateau.getMaxColumns(), columns + 1);
	}
	
	@Test
	public void testInit(){
		Plateau plateau = new Plateau(5, 5);
		
		for(int i = 0; i < plateau.getMaxRows(); i++)
		{
			for(int j = 0; j < plateau.getMaxColumns(); j++)
			{
				assertEquals(true, plateau.getGrid()[i][j]);
			}
		}
	}

}
