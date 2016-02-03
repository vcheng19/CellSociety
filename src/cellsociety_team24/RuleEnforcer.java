package cellsociety_team24;

import java.util.*;


public abstract class RuleEnforcer {
	int gridsize; //FILLER
	public RuleEnforcer() {
		// TODO Auto-generated constructor stub
		
	}
	
	public void updateState(Cell cell){
		int row = cell.getX();
		int col = cell.getY(); 
		
	
			
	}
	
	public void checkNeighbors(int xPos, int yPos){
		
	}
	
//	public boolean isCornerCell(int r, int c){
//		if(r == 0 || r == gridsize - 1){
//			if(c == 0 || c == gridsize - 1){
//				return true;
//			}
//			else{
//				return false; 
//			}
//		}
//		else{
//			return false; 
//		}
//	}
//	
//	public boolean isMiddleCell(int r, int c){
//		if(r != 0 || r != gridsize - 1){
//			if(c != 0 || c != gridsize - 1){
//				return true; 
//			}
//			else{
//				return false;
//			}
//		}
//		else{
//			return false; 
//		}
//	}
//	
//	public boolean isSideCell(int r, int c){
//		return true; 
//	}
}
