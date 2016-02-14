package gridinitializers;

import cellclasses.SlimeCell;
import cellsociety_team24.Grid;
import cellsociety_team24.HexagonGrid;
import filereadcheck.FileReader;
import javafx.scene.Group;

public class SlimeGridInitializer extends GridInitializer {
	
	public SlimeGridInitializer(Grid thisGrid,Group gr, FileReader fr, boolean isWrapped) {
		super(thisGrid, gr, fr, isWrapped);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void makeGrid() {
		grid = new SlimeCell[DIMENSION][DIMENSION];
		for (int i=0;i<grid.length;i++) { 
			for (int j=0;j<grid[0].length;j++) { 
				//GameOfLifeCell cell = new GameOfLifeCell(g, WORLD_SIZE/DIMENSION, i, j);
				SlimeCell cell = new SlimeCell(i, j);
				grid[i][j] = cell;
			}
		}
		thisGrid.setValues(grid, WORLD_SIZE/DIMENSION, g);
		//System.out.println(WORLD_SIZE/DIMENSION);
		thisGrid.createCells(wrap);
		addAttributes();
	}

	@Override
	public void addAttributes() {
		// TODO Auto-generated method stub
		int numberSlime = Integer.parseInt(reader.readProperty("numSlimeCells"));
		int count = 0;
		while(count < numberSlime){
			int x = (int) Math.floor(Math.random() * grid.length);
			int y = (int) Math.floor(Math.random() * grid.length);
			SlimeCell cell = (SlimeCell) grid[x][y];
			if(!cell.isSlime()){
				count++;
				cell.makeSlime();
			}
		}
	}

}
