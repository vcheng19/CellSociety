package gridinitializers;

import cellclasses.Cell;
import cellclasses.ForagingAntCell;
import cellsociety_team24.Grid;
import filereadcheck.FileReader;
import javafx.scene.Group;
import ruleEnforcers.ForagingAntRuleEnforcer;

public class ForagingAntGridInitializer extends GridInitializer {
	private final String nestXTag = "nestx";
	private final String nestYTag = "nesty"; 
	private final String foodXTag = "foodx"; 
	private final String foodYTag = "foody"; 
	private final int initAnts = Integer.parseInt(getReader().readProperty("initantsnest"));
	ForagingAntRuleEnforcer myRuleEnforcer;
	
	public ForagingAntGridInitializer(Grid thisGrid, Group gr, FileReader fr) {
		super(thisGrid, gr, fr);
		myRuleEnforcer = new ForagingAntRuleEnforcer(getGrid(), fr);
	}
	
	public void makeGrid() {
		Cell[][] grid = new ForagingAntCell[getDimension()][getDimension()];
		for (int i=0;i<grid.length;i++) { 
			for (int j=0;j<grid[0].length;j++) { 
				ForagingAntCell cell = new ForagingAntCell(i, j);
				grid[i][j] = cell;
				if (doConfigCell(nestXTag, nestYTag, i, j)) cell.makeNest(initAnts); 
				if (doConfigCell(foodXTag, foodYTag, i, j)) cell.makeFood();
			}
		}
	}
	
	public void addAttributes(Cell[][] grid) { 
		for (int i=0;i<grid.length;i++) { 
			for (int j=0;j<grid[0].length;j++) { 
				ForagingAntCell cell = new ForagingAntCell(i, j);
				grid[i][j] = cell;
				if (doConfigCell(nestXTag, nestYTag, i, j)) cell.makeNest(initAnts);
				if (doConfigCell(foodXTag, foodYTag, i, j)) cell.makeFood();
			}
		}
	}

}
