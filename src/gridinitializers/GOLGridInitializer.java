package gridinitializers;

import cellclasses.GameOfLifeCell;
import filereadcheck.FileReader;
import javafx.scene.Group;

public class GOLGridInitializer extends GridInitializer{
	
	public GOLGridInitializer(Group gr, FileReader fr) {
		super(gr, fr);
		addAlive();
	}

	public void addAlive() { 
		Cell[][] grid = getGrid();
		int[] xAlive = reader.populateCoorArray(reader.readProperty("alivex"));
		int[] yAlive = reader.populateCoorArray(reader.readProperty("alivey"));
		for (int i=0;i<xAlive.length;i++) { 
			GameOfLifeCell golcell= (GameOfLifeCell) grid[xAlive[i]][yAlive[i]];
			golcell.makeAlive();
		}	
	}
}
