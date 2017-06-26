package com.luiz.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reader {

	private FileInputStream file;
	private InputStreamReader input;
	private BufferedReader reader;
	
	public Reader(String fileName) throws FileNotFoundException
	{
		file = new FileInputStream(fileName);
		input = new InputStreamReader(file);
		reader = new BufferedReader(input);
	}
	
	public String readLine()
	{
		String line = null;
		
		try 
		{
			line = reader.readLine();
		} 
		catch (IOException e) 
		{
			System.out.println("Error: File could not be read.");
		}
		
		return line;
	}
	
	public void closeResources()
	{
		try{
			reader.close();
			input.close();
			file.close();
		}catch(IOException e){
			System.out.println("Error: Could not close file resources.");
		}
	}

	public FileInputStream getFile() {
		return file;
	}

	public InputStreamReader getInput() {
		return input;
	}

	public BufferedReader getReader() {
		return reader;
	}
	
	
	
}
