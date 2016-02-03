package cellsociety_team24;

import java.util.*;


public abstract class RuleEnforcer {
	Cell[][] myGrid; 
	public RuleEnforcer() {
		// TODO Auto-generated constructor stub
		
	}
	
	public void iterateGrid(Cell[][] grid){
		myGrid = grid; 
		for(int x = 0; x < myGrid.length; x++){
			for(int y = 0; y < myGrid.length; y++){
				updateState(grid[x][y]); 
			}
		}
		return; 
	}
	
	public void updateState(Cell cell){
		int row = cell.getX();
		int col = cell.getY(); 
		checkNeighbors(row, col); 
		return; 
	}
	
//	public void checkNeighbors(int xPos, int yPos){
//		int numDeadCells = 0; 
//		if(xPos + 1 > myGrid.length){
//			if(myGrid[xPos+1][yPos]) 
//		}
//	}
	
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
