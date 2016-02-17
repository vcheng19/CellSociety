package gridinitializers;

import cellclasses.Cell;
import cellclasses.ForagingAntCell;
import cellsociety_team24.Grid;
import filereadcheck.FileReader;
import javafx.scene.Group;

public class ForagingAntGridInitializer extends GridInitializer {
	private final String nestXTag = "nestx";
	private final String nestYTag = "nesty"; 
	private final String foodXTag = "foodx"; 
	private final String foodYTag = "foody"; 
	private final int initAnts = Integer.parseInt(getReader().readProperty("initantsnest"));
	
	public ForagingAntGridInitializer(Grid thisGrid, Group gr, FileReader fr, boolean w) {
		super(thisGrid, gr, fr, w);
		
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

	@Override
	public void addAttributes(Cell[][] grid) {
		// TODO Auto-generated method stub
		
	}

}
