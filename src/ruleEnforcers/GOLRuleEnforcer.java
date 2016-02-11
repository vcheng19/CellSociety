package ruleEnforcers;

import java.util.ArrayList;
import java.util.List;

import cellclasses.Cell;
import cellclasses.GameOfLifeCell;
import filereadcheck.FileReader;

public class GOLRuleEnforcer extends RuleEnforcer {
	private boolean[][] copyGrid; 
	private static int underPop; 
	private static int overPop; 
	private static int liveAgain; 
	private boolean wrap = false;
	
	public GOLRuleEnforcer(Cell[][]grid, FileReader reader){
		super(grid, reader);
	}
	
	public void initializeParameters() { 
		underPop = Integer.parseInt(reader.readProperty("underpop"));
		overPop = Integer.parseInt(reader.readProperty("overpop"));
		liveAgain = Integer.parseInt(reader.readProperty("liveagain"));
	}

	public void iterateGrid(){
		makeCopy(); //To iterate over a reference grid (last gen)
		for(int r = 0; r < copyGrid.length; r++){
			for(int c = 0; c < copyGrid.length; c++){
				boolean copyCell = copyGrid[r][c];
				GameOfLifeCell actualCell = myGrid[r][c];
				int myNeighbors = numNeighbors(actualCell);
				if(copyCell && myNeighbors == liveAgain){
						actualCell.makeAlive();
				}
				else if (myNeighbors < underPop || myNeighbors > overPop) {
						actualCell.killCell();
				}
			}
		}
	}

	private void makeCopy() {
		copyGrid = new boolean[myGrid.length][myGrid.length];
		for (int row = 0; row < myGrid.length; row++){
			for(int col = 0; col < myGrid.length; col++){
				copyGrid[row][col] = myGrid[row][col].isDead();
			}
		}
	}
	
	
	
	private int numNeighbors(Cell cell){
		int numAliveCells = 0;
		List<Cell> myNeighbors = getNeighbors(cell, wrap);
		for (Cell x: myNeighbors){
			int row= x.getX();
			int col = x.getY();
			if(!copyGrid[row][col]){
				numAliveCells++;
			}	
		}
		return numAliveCells; 
	}
}
