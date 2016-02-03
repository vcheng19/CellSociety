package cellsociety_team24;

import java.util.*;

public class GOLRuleEnforcer extends RuleEnforcer {
	private GameOfLifeCell[][] myGrid; 
	
	public GOLRuleEnforcer(GameOfLifeCell[][]grid){
		super(grid);
		myGrid = grid; 
	}
	public GameOfLifeCell [][] iterateGrid(){
		for(int r = 0; r < myGrid.length; r++){
			for(int c = 0; c < myGrid.length; c++){
				
			}
		}
		return myGrid; 
	}
	
	public int checkNeighbor(int r, int c){
		int numDeadCells = 0;
		for(int rChange = -1;rChange < 2;rChange++){
			if(r + rChange >= 0 && r + rChange < myGrid.length){//If r is within the boundaries of the grid
				for(int cChange = -1;cChange < 2;cChange++){
					if(c + cChange >= 0 && r + cChange < myGrid.length){
						if(myGrid[r+rChange][c+cChange].isDead()) numDeadCells++;
					}
				}
			}	
		}
		return numDeadCells; 
	}
	

}
