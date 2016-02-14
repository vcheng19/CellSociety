package gridinitializers;

import cellclasses.GameOfLifeCell;
import cellsociety_team24.Grid;
import cellsociety_team24.HexagonGrid;
import cellsociety_team24.SquareGrid;
import cellsociety_team24.TriangleGrid;
import filereadcheck.FileReader;
import javafx.scene.Group;

public class GOLGridInitializer extends GridInitializer{
	private final String aliveXTag = "alivex"; 
	private final String aliveYTag = "alivey"; 
	
	public GOLGridInitializer(Grid thisGrid, Group gr, FileReader fr, boolean w) {
		super(thisGrid, gr, fr, w);
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
		thisGrid.setValues(grid, WORLD_SIZE/DIMENSION, g);
		//System.out.println(WORLD_SIZE/DIMENSION);
		System.out.println(wrap);
		thisGrid.createCells(wrap);
		addAttributes();
	}
	
	public void addAttributes(){
		for (int i=0;i<grid.length;i++) { 
			for (int j=0;j<grid[0].length;j++) { 
				//GameOfLifeCell cell = new GameOfLifeCell(g, WORLD_SIZE/DIMENSION, i, j);
				GameOfLifeCell cell = (GameOfLifeCell) grid[i][j];
				if (doConfigCell(aliveXTag, aliveYTag, i, j)) cell.makeAlive();
			}
		}
	}
//
	public void addAlive() { 
		int[] xAlive = reader.populateCoorArray(reader.readProperty("alivex"));
		int[] yAlive = reader.populateCoorArray(reader.readProperty("alivey"));
		for (int i=0;i<xAlive.length;i++) { 
			GameOfLifeCell golcell= (GameOfLifeCell) grid[xAlive[i]][yAlive[i]];
			golcell.makeAlive();
		}	
	}
}
