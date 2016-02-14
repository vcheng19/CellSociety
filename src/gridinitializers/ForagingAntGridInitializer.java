package gridinitializers;

import cellclasses.ForagingAntCell;
<<<<<<< HEAD
import cellclasses.SegregationCell;
=======
import cellsociety_team24.Grid;
>>>>>>> 15e7675d6c31567242b975ebc3603c803177d388
import filereadcheck.FileReader;
import javafx.scene.Group;

public class ForagingAntGridInitializer extends GridInitializer {
	private final String nestXTag = "nestx";
	private final String nestYTag = "nesty"; 
	private final String foodXTag = "foodx"; 
	private final String foodYTag = "foody"; 
	private final int initAnts = Integer.parseInt(reader.readProperty("initantsnest"));
	
	public ForagingAntGridInitializer(Grid thisGrid, Group gr, FileReader fr, boolean w) {
		super(thisGrid, gr, fr, w);
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

	@Override
	public void addAttributes() {
		// TODO Auto-generated method stub
		
	}

}
