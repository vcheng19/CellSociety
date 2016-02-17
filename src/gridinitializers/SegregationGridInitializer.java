package gridinitializers;


import cellclasses.*;
import cellsociety_team24.Grid;
import filereadcheck.FileReader;
import javafx.scene.Group;
import ruleEnforcers.SegregationRuleEnforcer;

public class SegregationGridInitializer extends GridInitializer {
	private final String n1TagX = "redx"; 
	private final String n1TagY = "redy"; 
	private final String n2TagX = "bluex"; 
	private final String n2TagY = "bluey"; 
	SegregationRuleEnforcer myRuleEnforcer;
	
	public SegregationGridInitializer(Grid thisGrid,Group gr, FileReader fr) {
		super(thisGrid,gr, fr);
		myRuleEnforcer = new SegregationRuleEnforcer(getGrid(), fr);
	}
	
	public void makeGrid() { 
		Cell[][] grid = new SegregationCell[getDimension()][getDimension()];
		for (int i=0;i<grid.length;i++) { 
			for (int j=0;j<grid.length;j++) { 
				SegregationCell cell = new SegregationCell(i, j);
				grid[i][j] = cell;
			}
		}
		getThisGrid().setValues(grid, getWorldSize()/getDimension(), getGroup());
		getThisGrid().createCells(getWrap(), 1);
		addAttributes(grid);
		setGrid(grid);
	}
	
	public void addAttributes(Cell[][] grid){
		for (int i=0;i<grid.length;i++) { 
			for (int j=0;j<grid.length;j++) { 
				SegregationCell cell = (SegregationCell) grid[i][j];
				if (doConfigCell(n1TagX, n1TagY, i, j)) cell.makeRed();
				if (doConfigCell(n2TagX, n2TagY, i, j)) cell.makeBlue();

			}
		}
	}
}
