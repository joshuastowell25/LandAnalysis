package com.stowellperformance.LandAnalysis.Domain;

abstract class Point {
	private FertileShape parent;
	private boolean isHead = false;
	private boolean fertile = true;
	private boolean checked = false;

	public abstract boolean touches(Point p) throws Exception;
	public abstract String getCoordString();
	
	public boolean hasParent(){
		return this.parent != null;
	}
	
	public void markHead(){
		isHead = true;
	}
	
	public void unmarkHead(){
		isHead = false;
	}
	
	public boolean isHead(){
		return isHead;
	}
	
	public boolean isChecked(){
		return checked;
	}
	
	public void markChecked(){
		checked = true;
	}
	
	public boolean isFertile() {
		return fertile;
	}

	public void setFertile(boolean fertile) {
		this.fertile = fertile;
	}
	
	public void createParent() throws Exception{
		this.markHead();
		this.parent = new FertileShape();
		this.parent.setHeadPoint(this);
		this.parent.addPoint(this);
	}
	
	public FertileShape getParent(){
		return this.parent;
	}
	
	public void setParent(FertileShape s) throws Exception{
		this.parent = s;
		this.parent.addPoint(this);
	}
}
