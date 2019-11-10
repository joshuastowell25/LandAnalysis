package com.stowellperformance.LandAnalysis.Control;

import com.stowellperformance.LandAnalysis.Domain.Field2D;
import com.stowellperformance.LandAnalysis.Toolkit.Props;

public class Controller {
	public static void main(String[] args) {
		try{
			String input = IOHelper.getInput();
			long start = System.currentTimeMillis();
			input = IOHelper.cleanseInput(input);
			IOHelper.validateInput(input);
			
			int xMin = Integer.parseInt(Props.getProperty("field.xMin"));
			int xMax = Integer.parseInt(Props.getProperty("field.xMax"));
			int yMin = Integer.parseInt(Props.getProperty("field.yMin"));
			int yMax = Integer.parseInt(Props.getProperty("field.yMax"));
			
			Field2D field = new Field2D(xMin, xMax, yMin, yMax);
			field.markBarren(input);
			
			boolean printField = false;
			if(printField == true){
				field.printField();
			}
			
			Surveyor2D s = new Surveyor2D();
			s.surveyField(field);
			String output = s.getResults(field);
			long end = System.currentTimeMillis();
			IOHelper.printOutput(output);
			
			if(Props.getProperty("printStats").equals("true")){
				IOHelper.printStats(start, end);
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}