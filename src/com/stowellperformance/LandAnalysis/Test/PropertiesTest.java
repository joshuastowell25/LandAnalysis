package com.stowellperformance.LandAnalysis.Test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PropertiesTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws IOException {
        InputStream input = new FileInputStream("resources/landAnalysis.properties");
        Properties prop = new Properties();
        prop.load(input);
        String xMin = prop.getProperty("field.xMin");
        System.out.println("xMin: "+xMin);
        assertNotNull(xMin);
	}

}
