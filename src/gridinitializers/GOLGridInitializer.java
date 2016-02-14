package gridinitializers;

import cellclasses.*;
import cellsociety_team24.Grid;
import filereadcheck.FileReader;
import javafx.scene.Group;

public class GOLGridInitializer extends GridInitializer{
	private final String aliveXTag = "alivex"; 
	private final String aliveYTag = "alivey"; 
	
	public GOLGridInitializer(Grid thisGrid, Group gr, FileReader fr, boolean w) {
		super(thisGrid, gr, fr, w);
	}
	
	public void makeGrid() { 
		GameOfLifeCell[][] grid = new GameOfLifeCell[getDimension()][getDimension()];
		for (int i=0;i<grid.length;i++) { 
			for (int j=0;j<grid[0].length;j++) { 
				GameOfLifeCell cell = new GameOfLifeCell(i, j);
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
			for (int j=0;j<grid[0].length;j++) { 
				GameOfLifeCell cell = (GameOfLifeCell) grid[i][j];
				if (doConfigCell(aliveXTag, aliveYTag, i, j)) cell.makeAlive();
			}
		}
	}
}
