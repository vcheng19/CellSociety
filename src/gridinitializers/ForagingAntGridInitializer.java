package gridinitializers;

import cellclasses.ForagingAntCell;
import cellclasses.SegregationCell;
import filereadcheck.FileReader;
import javafx.scene.Group;

public class ForagingAntGridInitializer extends GridInitializer {
	private final String nestXTag = "nestx";
	private final String nestYTag = "nesty"; 
	private final String foodXTag = "foodx"; 
	private final String foodYTag = "foody"; 
	private final int initAnts = Integer.parseInt(reader.readProperty("initantsnest"));
	
	public ForagingAntGridInitializer(Group gr, FileReader fr) {
		super(gr, fr);
	}
	
	public void makeGrid() {
		grid = new ForagingAntCell[DIMENSION][DIMENSION];
		for (int i=0;i<grid.length;i++) { 
			for (int j=0;j<grid[0].length;j++) { 
				ForagingAntCell cell = new ForagingAntCell(g, WORLD_SIZE/DIMENSION, i, j);
				grid[i][j] = cell;
				if (doConfigCell(nestXTag, nestYTag, i, j)) cell.makeNest(initAnts); 
				if (doConfigCell(foodXTag, foodYTag, i, j)) cell.makeFood();
			}
		}
	}
	
	public void addAttributes() { 
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
