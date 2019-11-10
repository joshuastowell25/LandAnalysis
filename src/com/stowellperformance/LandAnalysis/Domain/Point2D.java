/**
 * 
 */
package com.stowellperformance.LandAnalysis.Domain;

/**
 * @author Josh Stowell
 * @since November 2019
 */
public class Point2D extends Point implements Comparable<Point2D> {
	private int xCoord;
	private int yCoord;
	
	public Point2D(int xCoord, int yCoord){
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}
	
	/**
	 * @return the xCoord
	 */
	public int getxCoord() {
		return xCoord;
	}

	/**
	 * @param xCoord the xCoord to set
	 */
	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	/**
	 * @return the yCoord
	 */
	public int getyCoord() {
		return yCoord;
	}

	/**
	 * @param yCoord the yCoord to set
	 */
	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	@Override
	public int compareTo(Point2D o){
		int retVal = 1;
		
		if(o != null && o.getxCoord() == this.xCoord && o.getyCoord() == this.yCoord){
			retVal=0;
		}
		
		return retVal;
	}

	@Override
	public boolean touches(Point p) throws Exception {
		boolean touches = false;
		//2d points are always assumed to be at z=0 in 3d space
		if(p != null){
			if(p instanceof Point2D){
				Point2D point = (Point2D) p;
				int px = point.getxCoord();
				int py = point.getyCoord();
				if((px == (this.xCoord - 1) || px == (this.xCoord + 1)) && py == (this.yCoord)){
					//to the left or the right, same z, same y
					touches = true;
				}else if((py == (this.yCoord - 1) || py == (this.yCoord + 1)) && px == (this.xCoord)){
					//above or below, same z, same x
					touches = true;
				}
			}else if(p instanceof Point3D){
				throw new Exception("Point2D and Point3D touches() not supported.");
			}
		}
		return touches;
	}

	@Override
	public String getCoordString() {
		return this.xCoord+","+this.yCoord;
	}

}
