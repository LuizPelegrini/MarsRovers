package com.luiz.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {
	
	/**
	 * Suite responsible to run all the tests
	 * */
	public static Test suite()
	{
		TestSuite suite = new TestSuite("Main suite for the tests");
		
		suite.addTestSuite(PlateauTest.class);
		suite.addTestSuite(ManagerTest.class);
		suite.addTestSuite(RoverTest.class);
		
		return suite;
	}
	
}
