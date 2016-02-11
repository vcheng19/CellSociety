package gridinitializers;

import cellclasses.Cell;
import cellclasses.GameOfLifeCell;
import filereadcheck.FileReader;
import javafx.scene.Group;

public class GOLGridInitializer extends GridInitializer{
	private Cell [][]golGrid = getGrid();
	
	public GOLGridInitializer(Group gr, FileReader fr) {
		super(gr, fr);
	}
	
	public void makeGrid() { 
//		grid = new GameOfLifeCell[DIMENSION][DIMENSION];
		for (int i=0;i<golGrid.length;i++) { 
			for (int j=0;j<golGrid[0].length;j++) { 
				GameOfLifeCell cell = new GameOfLifeCell(getGroup(), getWorldSize()/getDimension(), i, j);
				golGrid[i][j] = cell;
			}
		}
		addAlive();
	}

	public void addAlive() { 
		int[] xAlive = getReader().populateCoorArray(getReader().readProperty("alivex"));
		int[] yAlive = getReader().populateCoorArray(getReader().readProperty("alivey"));
		for (int i=0;i<xAlive.length;i++) { 
			GameOfLifeCell golcell= (GameOfLifeCell) golGrid[xAlive[i]][yAlive[i]];
			golcell.makeAlive();
		}	
	}
}
