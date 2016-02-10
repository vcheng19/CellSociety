package cellsociety_team24;

import filereadcheck.FileReader;
import javafx.scene.Group;

public class GOLGridInitializer extends GridInitializer{
	
	public GOLGridInitializer(Group gr, FileReader fr) {
		super(gr, fr);
	}
	
	public void makeGrid() { 
		grid = new GameOfLifeCell[DIMENSION][DIMENSION];
		for (int i=0;i<grid.length;i++) { 
			for (int j=0;j<grid[0].length;j++) { 
				GameOfLifeCell cell = new GameOfLifeCell(g, WORLD_SIZE/DIMENSION, i, j);
				grid[i][j] = cell;
			}
		}
		addAlive();
	}

	public void addAlive() { 
		int[] xAlive = reader.populateCoorArray(reader.readProperty("alivex"));
		int[] yAlive = reader.populateCoorArray(reader.readProperty("alivey"));
		for (int i=0;i<xAlive.length;i++) { 
			GameOfLifeCell golcell= (GameOfLifeCell) grid[xAlive[i]][yAlive[i]];
			golcell.makeAlive();
		}	
	}
}
