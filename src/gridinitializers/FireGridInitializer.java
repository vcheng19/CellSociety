package gridinitializers;

import cellclasses.FireCell;
import cellclasses.Cell;
import cellsociety_team24.Grid;
import filereadcheck.FileReader;
import javafx.scene.Group;

public class FireGridInitializer extends GridInitializer {
	private final String fireXTag = "fireX";
	private final String fireYTag = "fireY"; 
	
	public FireGridInitializer(Grid thisGrid, Group gr, FileReader fr, boolean w) {
		super(thisGrid, gr, fr, w);
	}
	
	public void makeGrid(){
		FireCell[][]grid = new FireCell[getDimension()][getDimension()];
		for (int i=0;i<grid.length;i++) { 
			for (int j=0;j<grid.length;j++) { 
				FireCell cell = new FireCell(i, j);
				grid[i][j] = cell;
			}
		}
		getThisGrid().setValues(grid, getWorldSize()/getDimension(), getGroup(), true);
		getThisGrid().createCells(getWrap(), 1);
		addAttributes(grid);
		setGrid(grid);
	}
	
	public void addAttributes(Cell[][] grid){
		makeBorder((FireCell[][]) grid);
		addFire((FireCell[][])grid);
	}
	
	private void addFire(FireCell[][] grid){
		int fireX = Integer.parseInt(getReader().readProperty(fireXTag));
		int fireY = Integer.parseInt(getReader().readProperty(fireYTag));
		FireCell fireCell = (FireCell) grid[fireX][fireY];
		fireCell.makeFire();
	}
	
	private void makeBorder(FireCell[][] grid){
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
