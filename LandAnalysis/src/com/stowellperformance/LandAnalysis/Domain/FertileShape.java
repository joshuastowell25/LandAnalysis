package com.stowellperformance.LandAnalysis.Domain;

import java.util.ArrayList;
import java.util.HashMap;

public class FertileShape implements Comparable<FertileShape> {
	//Points list
	private HashMap<String,Point> pointHash;
	private ArrayList<Point> pointList;
	private Point headPoint; //The head point is the point that was first added to the FertileShape
	
	public Point getHeadPoint(){
		return this.headPoint;
	}
	
	public void setHeadPoint(Point p){
		this.headPoint = p;
	}
	
	public FertileShape(){
		pointList = new ArrayList<Point>();
		pointHash = new HashMap<String, Point>();
	}
	
	/**
	 * Adds the points from another fertileShape to this shape
	 * @param otherShape
	 */
	public void absorb(FertileShape otherShape) throws Exception{
		//change to log4J
		//System.out.println("Head "+this.headPoint.getCoordString()+ " absorbing: ");
		Point otherHead = otherShape.getHeadPoint();
		if(otherHead != null){
			otherHead.unmarkHead();
		}
		
		this.getHeadPoint().markHead();
		otherShape.setHeadPoint(this.getHeadPoint()); //change the absorbee's head point to this shapes head point
		
		ArrayList<Point> pl = otherShape.getPointList();
		
		for(int i = 0; i<pl.size(); i++){
			addPoint(pl.get(i));
		}
	}
	
	public void addPoint(Point p) throws Exception{
		if(!pointHash.containsKey(p.getCoordString())){
			pointList.add(p);	
		}
		pointHash.put(p.getCoordString(), p);
		
		changeHead(p);
	}
	
	private void changeHead(Point p){
		this.headPoint.unmarkHead();
		p.markHead();
		this.setHeadPoint(p);
	}
	
	public ArrayList<Point> getPointList(){
		return pointList;
	}
	
	public void printPoints(){
		System.out.println("Printing Points: ");
		for( int i = 0; i<pointList.size(); i++){
			System.out.println(pointList.get(i).getCoordString());
		}
	}
	
	public int size(){
		return pointList.size();
	}

	@Override
	public int compareTo(FertileShape o) {
		int retVal = 0;
		
		if(this.size() > o.size()){
			retVal = 1;
		}
		else if(this.size() < o.size()){
			retVal = -1;
		}
		else{
			retVal = 0;
		}
		
		return retVal;
	}
}
