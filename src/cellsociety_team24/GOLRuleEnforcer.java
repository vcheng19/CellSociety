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
				int x = myGrid[r][c].getX(); 
				int y = myGrid[r][c].getY(); 
				//If alive and have 2 or 3 living neighbors
				if(!myGrid[r][c].isDead() && (checkNeighbor(x, y) == 2 || checkNeighbor(x, y) == 3)){
					myGrid[r][c].makeAlive(); 
				}
				else{
					myGrid[r][c].killCell(); 
				}
				if(myGrid[r][c].isDead() && checkNeighbor(x, y) == 3){
					myGrid[r][c].makeAlive(); 
				}
			}
		}
		return myGrid; 
	}
	
	public int checkNeighbor(int r, int c){
		int numAliveCells = 0;
		for(int rChange = -1;rChange < 2;rChange++){
			if(r + rChange >= 0 && r + rChange < myGrid.length){//If r is within the boundaries of the grid
				for(int cChange = -1;cChange < 2;cChange++){
					if(c + cChange >= 0 && r + cChange < myGrid.length){
						if(!myGrid[r+rChange][c+cChange].isDead()) numAliveCells++;
					}
				}
			}	
		}
		return numAliveCells; 
	}
	

}
