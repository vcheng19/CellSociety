package gridinitializers;

import cellclasses.FireCell;
import cellclasses.GameOfLifeCell;
import cellsociety_team24.Grid;
import cellsociety_team24.HexagonGrid;
import cellsociety_team24.SquareGrid;
import cellsociety_team24.TriangleGrid;
import filereadcheck.FileReader;
import javafx.scene.Group;

public class FireGridInitializer extends GridInitializer {
	
	public FireGridInitializer(Group gr, FileReader fr) {
		super(gr, fr);
	}
	
	public void makeGrid(){
		grid = new FireCell[DIMENSION][DIMENSION];
		for (int i=0;i<grid.length;i++) { 
			for (int j=0;j<grid.length;j++) { 
				FireCell cell = new FireCell(i, j);
				grid[i][j] = cell;
			}
		}

		Grid thisGrid = new SquareGrid(grid, WORLD_SIZE/DIMENSION, g, true);
		boolean wrap = false;
		thisGrid.createCells(wrap);
		makeBorder();
		addFire();

	}
	
	private void addFire(){
		int fireX = Integer.parseInt(reader.readProperty("fireX"));
		int fireY = Integer.parseInt(reader.readProperty("fireY"));
		FireCell fireCell = (FireCell) grid[fireX][fireY];
		fireCell.makeFire();
	}
	
	private void makeBorder(){
		for (int i=0;i<grid.length;i++) { 
			for (int j=0;j<grid[0].length;j++) { 
				FireCell cell = (FireCell)grid[i][j];
				cell.makeTree();
				if(i == 0 || i == grid.length -1 || j ==0 || j == grid[0].length-1){
					cell.makeEmpty();
				}
			}
		}

	}
	
}
