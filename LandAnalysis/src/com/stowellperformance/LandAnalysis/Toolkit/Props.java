package com.stowellperformance.LandAnalysis.Toolkit;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Props {

	public static String getProperty(String propName) throws IOException{
		InputStream input = new FileInputStream("resources/landAnalysis.properties");
        Properties p = new Properties();
        p.load(input);
        String prop = p.getProperty(propName);
        return prop;
	}
}
