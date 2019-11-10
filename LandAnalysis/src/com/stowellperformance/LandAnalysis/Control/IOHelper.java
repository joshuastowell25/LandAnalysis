package com.stowellperformance.LandAnalysis.Control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOHelper {
	public static String inputPattern ="\\s*\\{(\\s*\\d+\\s+\\d+\\s+\\d+\\s+\\d+\\s*,*\\s*)+\\}\\s*";

	/**
	 * Separate method in case we ever want to change input strategy later
	 * @return The string input from the user
	 * @throws IOException 
	 */
	public static String getInput() throws IOException{
		System.out.println("Enter barren map (e.g. {“48 192 351 207”, “48 392 351 407”})");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
	    String input = reader.readLine(); 
	    reader.close();
	    return input;         
	}
	
	/**
	 * Removes common unicode characters
	 * @param input
	 * @return cleansed input
	 */
	public static String cleanseInput(String input){
		input = input.replaceAll(""+((char)28), "");
		input = input.replaceAll(""+((char)29), "");
		input = input.replaceAll("“", "");
		input = input.replaceAll("”", "");
		
		return input;
	}

	/**
	 * Checks the input against regex
	 * @param input string representing infertile areas
	 * @return boolean representing whether or not input was valid
	 * @throws Exception
	 */
	public static boolean validateInput(String input) throws Exception{
		boolean retVal = false;
		if(input.matches(inputPattern)){
			retVal = true;
		}else{
			throw new Exception("Ill-formed barren map.");
		}
		
		return retVal;
	}
	
	/**
	 * Separate method in case we ever want to extend this to print elsewhere
	 * @param output
	 */
	public static void printOutput(String output){
		System.out.println(output);
	}
	
	public static void printStats(long start, long end){
		long elapsed = end - start;
		System.out.println("Elapsed time: "+elapsed+"ms");
	}
}
