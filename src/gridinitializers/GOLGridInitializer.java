package gridinitializers;

import cellclasses.GameOfLifeCell;
import cellsociety_team24.Grid;
import cellsociety_team24.HexagonGrid;
import cellsociety_team24.SquareGrid;
import cellsociety_team24.TriangleGrid;
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
				//GameOfLifeCell cell = new GameOfLifeCell(g, WORLD_SIZE/DIMENSION, i, j);
				GameOfLifeCell cell = new GameOfLifeCell(i, j);
				grid[i][j] = cell;
			}
		}
		Grid thisGrid = new HexagonGrid(grid, WORLD_SIZE/DIMENSION, g);
		//System.out.println(WORLD_SIZE/DIMENSION);
		thisGrid.createCells();
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
