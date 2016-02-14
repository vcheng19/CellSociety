package gridinitializers;

import cellclasses.*;
import cellsociety_team24.Grid;
import filereadcheck.FileReader;
import javafx.scene.Group; 

public class WaTorGridInitializer extends GridInitializer{
	private int sharkEnergy; 
	private int fishEnergy;
	private final String sharkXTag = "sharkx"; 
	private final String sharkYTag = "sharky";
	private final String fishXTag = "fishx";
	private final String fishYTag = "fishy"; 
	
	public WaTorGridInitializer(Grid thisGrid, Group gr, FileReader fr, boolean isWrapped){
		super(thisGrid,gr, fr, isWrapped);
	}
	
	public void makeGrid(){
		Cell[][]grid = new WaTorCell[getDimension()][getDimension()];
		FileReader reader = getReader();
		sharkEnergy = Integer.parseInt(reader.readProperty("sharkenergy"));
		fishEnergy = Integer.parseInt(reader.readProperty("fishenergy"));
		for(int i = 0;i<grid.length;i++){
			for(int j = 0; j < grid[0].length;j++){
				WaTorCell cell = new WaTorCell(i, j);
				grid[i][j] = cell; 
			}
		}
		getThisGrid().setValues(grid, getWorldSize()/getDimension(), getGroup(), true);
		getThisGrid().createCells(getWrap(), 1);
		addAttributes(grid);
		setGrid(grid);	
	}
	
	public void addAttributes(Cell[][] grid){
		for(int i = 0;i<grid.length;i++){
			for(int j = 0; j < grid[0].length;j++){
				WaTorCell cell = (WaTorCell) grid[i][j];
				cell.makeOcean();
				if (doConfigCell(sharkXTag, sharkYTag, i, j)) cell.makeShark(sharkEnergy, 0);
				if (doConfigCell(fishXTag, fishYTag, i, j)) cell.makeFish(fishEnergy, 0);
			}
		}
	}
}