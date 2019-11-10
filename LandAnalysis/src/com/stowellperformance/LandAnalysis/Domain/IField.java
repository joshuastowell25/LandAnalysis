package com.stowellperformance.LandAnalysis.Domain;


public abstract interface IField {
	public void markBarren(String barrenMap) throws Exception;
	public void fertilizeField();
}
