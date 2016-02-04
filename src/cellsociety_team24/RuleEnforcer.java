package cellsociety_team24;

import java.util.*;


public abstract class RuleEnforcer {
	private Cell[][] myGrid; 
	private Cell cell; 
	public RuleEnforcer(Cell[][] grid) {
		myGrid = grid; 
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
	
	public void checkNeighbors(int xPos, int yPos){//Will need to update this 
		return; 
	}
}
