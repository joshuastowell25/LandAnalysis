package com.stowellperformance.LandAnalysis.Domain;

import java.util.HashMap;
import java.util.Map;

/**
 * A 2D representation of a field. Each square meter having it's own coordinate.
 * Coordinates not limited to starting at the origin.
 * @author Josh Stowell
 * @Since November 2019
 *
 */
public class Field2D implements IField {
	private int xMax;
	private int yMax;
	private int xMin;
	private int yMin;

	Map<String,Point> points;
	
	public Field2D(int xMin, int xMax, int yMin, int yMax) throws Exception{
		if(xMin >= xMax){
			throw new Exception("Field cannot be created with zero or negative length");
		}
		if(yMin >= yMax){
			throw new Exception("Field cannot be created with zero or negative width");
		}
		
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
		
		points = new HashMap<String,Point>();
		
		for(int i=xMin; i<=xMax; i++){
			for(int j=yMin; j<=yMax; j++){
				points.put(i+","+j, new Point2D(i,j));
			}
		}
	}
	
	public void fertilizeField(){	
		for(int i=xMin; i<=xMax; i++){
			for(int j=yMin; j<=yMax; j++){
				points.get(i+","+j).setFertile(true);
			}
		}
	}
	
	/**
	 * This function allows the caller to mark barren space within this field
	 * @param barrenMap The input marking barren space e.g. "{"0 292 399 307"}"
	 * @throws Exception if the barrenMap is outside the bounds of the field
	 */
	public void markBarren(String barrenMap) throws Exception{
		byte[] ascii = new byte[barrenMap.length()];

		for (int i = 0; i < barrenMap.length(); i++) {
			ascii[i] = (byte) barrenMap.charAt(i);
		}
				
		barrenMap = barrenMap.replace("{","");
		barrenMap = barrenMap.replace("}","");
		String[] allSquares;
		String[] singleSquare;
		String key;
		
		allSquares = barrenMap.split(",");
		
		for(int i = 0; i< allSquares.length; i++){
			allSquares[i] = allSquares[i].replaceAll("\"", "");
			allSquares[i] = allSquares[i].trim();
			singleSquare = allSquares[i].split("\\s");
			int bottomLeftX = Integer.parseInt(singleSquare[0]);
			int bottomLeftY = Integer.parseInt(singleSquare[1]);
			int topRightX =   Integer.parseInt(singleSquare[2]);
			int topRightY =   Integer.parseInt(singleSquare[3]);
			
			if(bottomLeftX < xMin || bottomLeftX > xMax || bottomLeftY < yMin || bottomLeftY > yMax){
				throw new Exception("Point: ("+bottomLeftX+","+bottomLeftY+") is out of bounds for this field");
			} else if(topRightX < xMin || topRightX > xMax || topRightY < yMin || topRightY > yMax){
				throw new Exception("Point: ("+topRightX+","+topRightY+") is out of bounds for this field");
			} else if(topRightX < bottomLeftX){
				throw new Exception("Ill-formed barren map. Length of barren plot must be greater than 0.");
			} else if(topRightY < bottomLeftY){
				throw new Exception("Ill-formed barren map. Width of barren plot must be greater than 0.");
			} else{ //the point is in bounds of this field
				
				for(int j=bottomLeftX; j<=topRightX; j++){
					for(int k=bottomLeftY; k<=topRightY; k++){
						key = j+","+k;
						points.get(key).setFertile(false);
					}
				}
			}
		}
	}
	
	/**
	 * Prints the field to standard out
	 */
	public void printField(){
		String row;
		for(int j = yMax; j>=yMin; j--){
			row = "";
			for(int i = xMin; i<= xMax; i++){
				if(points.get(i+","+j).isFertile()){
					row += "O";
				}else{
					row += "X";
				}
			}
			
			System.out.println(row);
		}
	}
	
	public Point2D getPoint(int x, int y){
		Point2D p = null;
		if(x >= xMin && x <= xMax && y >= yMin && y <= yMax){
			if(points != null){
				p = (Point2D) points.get(x+","+y);
			}
		}
		return p;
	}

	/**
	 * @return the xMax
	 */
	public int getxMax() {
		return xMax;
	}

	/**
	 * @param xMax the xMax to set
	 */
	public void setxMax(int xMax) {
		this.xMax = xMax;
	}

	/**
	 * @return the yMax
	 */
	public int getyMax() {
		return yMax;
	}

	/**
	 * @param yMax the yMax to set
	 */
	public void setyMax(int yMax) {
		this.yMax = yMax;
	}

	/**
	 * @return the xMin
	 */
	public int getxMin() {
		return xMin;
	}

	/**
	 * @param xMin the xMin to set
	 */
	public void setxMin(int xMin) {
		this.xMin = xMin;
	}

	/**
	 * @return the yMin
	 */
	public int getyMin() {
		return yMin;
	}

	/**
	 * @param yMin the yMin to set
	 */
	public void setyMin(int yMin) {
		this.yMin = yMin;
	}
	
}
