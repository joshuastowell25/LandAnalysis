package com.stowellperformance.LandAnalysis.Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.stowellperformance.LandAnalysis.Control.IOHelper;

public class InputTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	/**
	 * This test deals with special characters
	 */
	public void inputPatternTest1() {
		String good = "{48 192 351 207, 48 392 351 407, 120 52 135 547, 260 52 275 547} ";
		boolean test = new String(good).matches(IOHelper.inputPattern);
		assertTrue(test);
		
		//this string may look similar, but it actually contains special characters (ascii 28 and 29). 
		String bad = "{48 192 351 207, 48 392 351 407, 120 52 135 547, 260 52 275 547} ";
		String better = IOHelper.cleanseInput(bad);
		test = new String(better).matches(IOHelper.inputPattern);
		assertTrue(test);
		
		//different quotes
		String bad2 = "{“48 192 351 207”, “48 392 351 407”, “120 52 135 547”, “260 52 275 547”} ";
		test = IOHelper.cleanseInput(bad2).matches(IOHelper.inputPattern);
		assertTrue(test);
	}
	
	@Test
	/**
	 * Normal input test
	 */
	public void inputPatternTest2() {
		String testInput =  "{48 192 351 207, 48 192 351 207, 48 192 351 207, 48 192 351 207 } ";
		assertTrue(testInput.matches(IOHelper.inputPattern));
	}
	
	@Test
	/**
	 * empty input test
	 */
	public void inputPatternTest3() {
		String testInput = "{} ";
		assertFalse(testInput.matches(IOHelper.inputPattern));
	}

}
