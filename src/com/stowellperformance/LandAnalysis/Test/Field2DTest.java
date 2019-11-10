package com.stowellperformance.LandAnalysis.Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.stowellperformance.LandAnalysis.Domain.Field2D;

public class Field2DTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void markBarrenTest1() throws Exception{
		Field2D field = new Field2D(0,29,0,59);
		
		field.markBarren("{\"14 14 15 15\"}");
		field.printField();
		
		assertFalse(field.getPoint(14, 14).isFertile());
		assertFalse(field.getPoint(15, 14).isFertile());
		assertFalse(field.getPoint(14, 15).isFertile());
		assertFalse(field.getPoint(15, 15).isFertile());
	}
	
	@Test
	public void markBarrenTest2() throws Exception{
		Field2D field = new Field2D(0,399,0,599);
		
		field.markBarren("{\"0 292 399 307\"} ");
		field.printField();
		
		for(int i = 0; i<= 399; i++){
			for(int j = 0; j<=291; j++){
				assertTrue(field.getPoint(i, j).isFertile());
			}
		}
		
		for(int i = 0; i<=399; i++){
			for(int j = 292; j<=307; j++){
				assertFalse(field.getPoint(i, j).isFertile());
			}
		}
		
		for(int i = 0; i<= 399; i++){
			for(int j = 308; j<=599; j++){
				assertTrue(field.getPoint(i, j).isFertile());
			}
		}
	}	

}
