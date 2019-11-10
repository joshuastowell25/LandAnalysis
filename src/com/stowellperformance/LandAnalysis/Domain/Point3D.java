/**
 * 
 */
package com.stowellperformance.LandAnalysis.Domain;

/**
 * @author Josh Stowell
 * @since November 2019
 */
public class Point3D extends Point implements Comparable<Point3D> {
	private int xCoord;
	private int yCoord;
	private int zCoord;

	public Point3D(int xCoord, int yCoord, int zCoord){
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.zCoord = zCoord;
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

	/**
	 * @return the zCoord
	 */
	public int getzCoord() {
		return zCoord;
	}

	/**
	 * @param zCoord the zCoord to set
	 */
	public void setzCoord(int zCoord) {
		this.zCoord = zCoord;
	}

	@Override
	public int compareTo(Point3D o){
		int retVal = 1;
		
		if(o != null && o.getxCoord() == this.xCoord && o.getyCoord() == this.yCoord && o.getzCoord() == this.zCoord){
			retVal = 0;
		}
		
		return retVal;
	}

	@Override
	public boolean touches(Point p) throws Exception{
		boolean touches = false;
		if(p instanceof Point3D){
			Point3D point = (Point3D) p;
			int pz = point.getzCoord();
			int px = point.getxCoord();
			int py = point.getyCoord();
			if(pz == this.zCoord){//same plane
				if((px == (this.xCoord - 1) || px == (this.xCoord + 1)) && py == (this.yCoord)){
					//to the left or the right, same z, same y
					touches = true;
				}else if((py == (this.yCoord - 1) || py == (this.yCoord + 1)) && px == (this.xCoord)){
					//above or below, same z, same x
					touches = true;
				}
			}else if(pz == (this.zCoord - 1) || pz == (this.zCoord + 1)){
				if(px == this.xCoord && py == this.yCoord){
					touches = true;
				}
			}
			
		}else if(p instanceof Point2D){
			Point2D point = (Point2D) p;
			int px = point.getxCoord();
			int py = point.getyCoord();
			if(this.zCoord == 0){
				if((px == (this.xCoord - 1) || px == (this.xCoord + 1)) && py == (this.yCoord)){
					//to the left or the right, same z, same y
					touches = true;
				}else if((py == (this.yCoord - 1) || py == (this.yCoord + 1)) && px == (this.xCoord)){
					//above or below, same z, same x
					touches = true;
				}
			}else if(this.zCoord == -1 || this.zCoord == 1){
				if(px == this.xCoord && py == this.yCoord){
					touches = true;
				}
			}
		}
		return touches;
	}

	@Override
	public String getCoordString() {
		return this.xCoord+","+this.yCoord+","+this.zCoord;
	}
}

