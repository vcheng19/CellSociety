package cellsociety_team24;

import java.util.*;


public abstract class RuleEnforcer {
	int gridsize; //FILLER
	public RuleEnforcer() {
		// TODO Auto-generated constructor stub
		
	}
	
	public void checkNeighbor(Cell cell){
		int[] cellCoord = cell.getCoordinates() //I think we should add this method in 
		
		if(isCornerCell(cellCoord[0], cellCoord[1])){
			
		}
		
		else if(isMiddleCell(cellCoord[0], cellCoord[1])){
		}
		else{ //Side cell 
			
		}
			
	}
	
	public boolean isCornerCell(int r, int c){
		if(r == 0 || r == gridsize - 1){
			if(c == 0 || c == gridsize - 1){
				return true;
			}
			else{
				return false; 
			}
		}
		else{
			return false; 
		}
	}
	
	public boolean isMiddleCell(int r, int c){
		if(r != 0 || r != gridsize - 1){
			if(c != 0 || c != gridsize - 1){
				return true; 
			}
			else{
				return false;
			}
		}
		else{
			return false; 
		}
	}
	
//	public boolean isSideCell(int r, int c){
//		return true; 
//	}
}
