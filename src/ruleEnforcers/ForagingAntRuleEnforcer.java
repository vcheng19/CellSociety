package ruleEnforcers;

import java.util.List;

import cellclasses.Cell;
import cellclasses.ForagingAntCell;
import filereadcheck.FileReader;

public class ForagingAntRuleEnforcer extends RuleEnforcer{
	private static ForagingAntCell[][] myGrid;
	ForagingAntCell home; 
	private final double SELECT_K = Integer.parseInt(reader.readProperty("selectk")) / 1000; 
	private final double SELECT_N = Integer.parseInt(reader.readProperty("selectn"));
	private final int BIRTHRATE = Integer.parseInt(reader.readProperty("birthrate"));
	private final int MAX_PH = Integer.parseInt(reader.readProperty("birthrate"));
	
	private boolean wrap = false;
	
	
	public ForagingAntRuleEnforcer(Cell[][] grid, FileReader fr) {
		super(grid, fr);
		myGrid = new ForagingAntCell[grid.length][grid.length];
		for (int i=0;i<grid.length;i++) { 
			for (int j=0;j<grid.length;j++) { 
				myGrid[i][j] = (ForagingAntCell) grid[i][j];
				if (myGrid[i][j].isType("nest")) { 
					home = myGrid[i][j];
				}
			}
		}
		copyGrid = new ForagingAntCell[myGrid.length][myGrid.length];
	}

	void initializeParameters() {
		
		
		
	}
	
	public void iterateGrid(){
		for(int r = 0; r < myGrid.length; r++){
			for(int c = 0; c < myGrid.length; c++){
				ForagingAntCell curCell = myGrid[r][c];

			}
		}
		//resetMovedGrid();
	}
	

}
