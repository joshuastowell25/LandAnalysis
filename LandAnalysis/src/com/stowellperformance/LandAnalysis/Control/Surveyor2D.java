package com.stowellperformance.LandAnalysis.Control;

import java.util.ArrayList;
import java.util.Collections;

import com.stowellperformance.LandAnalysis.Domain.FertileShape;
import com.stowellperformance.LandAnalysis.Domain.Field2D;
import com.stowellperformance.LandAnalysis.Domain.IField;
import com.stowellperformance.LandAnalysis.Domain.Point2D;

public class Surveyor2D implements ISurveyor{
	private final String ROWSTRAT = "ROW";
	private final String COLSTRAT = "COL";
	private String surveyStrat = COLSTRAT;
	
	/**
	 * A smarter implementation of surveyField may split the input field into two or more fields
	 * and use concurrency to survey them concurrently, then merge the results
	 */
	public void surveyField(IField field) throws Exception{
		if(!(field instanceof Field2D)){
			throw new Exception("Surveyor2D cannot survey objects that are not of type Field2D");
		}
		Field2D f = (Field2D)field;
		
		switch(surveyStrat){
			case COLSTRAT:
				for(int i = f.getxMin(); i<=f.getxMax(); i++){
					for(int j = f.getyMin(); j<= f.getyMax(); j++){
						Point2D a = f.getPoint(i, j);
						surveyPoint(a, f);
					}
				}
				break;
			case ROWSTRAT:
				for(int j = f.getyMin(); j<= f.getyMax(); j++){
					for(int i = f.getxMin(); i<=f.getxMax(); i++){
						Point2D a = f.getPoint(i, j);
						surveyPoint(a, f);
					}
				}
				break;
			default:
				throw new Exception("Unknown survey strategy");
		}
	}
	
	public void surveyPoint(Point2D a, IField field) throws Exception{
		
		if(a.isFertile() && !a.isChecked()){
			int x = a.getxCoord();
			int y = a.getyCoord();
			
			Field2D f = (Field2D)field;
			Point2D leftNeighbor = f.getPoint(x-1, y);
			Point2D bottomNeighbor = f.getPoint(x, y-1);
			
			//only need to look at the left and bottom neighbors due to direction of travel
			
			//if the left Neighbor and the bottomNeighbor have different FertileShape parents, have the left one absorb the other
			if(leftNeighbor!= null && bottomNeighbor!= null && leftNeighbor.isFertile() && bottomNeighbor.isFertile() && leftNeighbor.hasParent() && bottomNeighbor.hasParent() && leftNeighbor.getParent() != bottomNeighbor.getParent()){
				leftNeighbor.getParent().absorb(bottomNeighbor.getParent());
				a.setParent(leftNeighbor.getParent());
				bottomNeighbor.setParent(a.getParent());
				leftNeighbor.getParent().addPoint(a);
			}else if(leftNeighbor != null && leftNeighbor.isChecked() && leftNeighbor.isFertile() && leftNeighbor.hasParent()){
				a.setParent(leftNeighbor.getParent());
				leftNeighbor.getParent().addPoint(a);
			}else if(bottomNeighbor != null && bottomNeighbor.isChecked() && bottomNeighbor.isFertile() && bottomNeighbor.hasParent()){
				a.setParent(bottomNeighbor.getParent());
				bottomNeighbor.getParent().addPoint(a);
			}else if(a.isFertile() && a.getParent() == null){//if a is fertile and doesn't have a parent yet, create one and mark a as the head
				a.createParent();
			}						
		}
		a.markChecked();
	}
	
	public String getResults(IField field) throws Exception{
		String results = "";
		try{
			ArrayList<FertileShape> fertilePlots = new ArrayList<FertileShape>();
	
			Field2D f = (Field2D)field;
			int xMin = f.getxMin();
			int xMax = f.getxMax();
			int yMin = f.getyMin();
			int yMax = f.getyMax();
			
			for(int i = xMin; i<=xMax; i++){
				for(int j = yMin; j<= yMax; j++){
					Point2D a = f.getPoint(i, j);
					if(a.isHead()){
						fertilePlots.add(a.getParent());
					}
				}
			}
			
			Collections.sort(fertilePlots); //sorts into ascending order
			
			for(int i = 0; i<fertilePlots.size(); i++){
				results += fertilePlots.get(i).size() + " ";
			}
		}catch(Exception e){
			throw new Exception("Unable to get field results: "+e.getMessage());
		}
		
		return results;
	}
}
