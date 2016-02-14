package ruleEnforcers;

import cellclasses.Cell;
import filereadcheck.FileReader;

public abstract class RuleEnforcer {
	private Cell[][] myGrid;
	private FileReader reader;
	public RuleEnforcer(Cell[][] grid, FileReader fr) {
		myGrid = grid; 
		reader = fr;
		initializeParameters();
	}

	abstract void initializeParameters();

	public abstract void iterateGrid();
	
	public FileReader getReader(){
		return reader;
	}
	
	public void resetMovedGrid() { 
		for (int r=0;r<myGrid.length;r++) { 
			for (int c=0;c<myGrid.length;c++) { 
				myGrid[r][c].setMoved(false);
			}
		}
	}
	
	public Cell[][] getGrid() { 
		return myGrid;
	}
	
	public Cell[][] giveGrid() { 
		return myGrid;
	}
}
