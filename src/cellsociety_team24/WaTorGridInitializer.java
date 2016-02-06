package cellsociety_team24;

import javafx.scene.Group; 

public class WaTorGridInitializer extends GridInitializer{
	private static WaTorCell[][] grid;
	
	public WaTorGridInitializer(Group gr, FileReader fr){
		super(gr, fr);
	}
	
	public void makeGrid(){
		grid = new WaTorCell[DIMENSION][DIMENSION];
		for(int i = 0;i<grid.length;i++){
			for(int j = 0; j < grid.length;j++){
				WaTorCell cell = new WaTorCell(g, WORLD_SIZE/DIMENSION, i, j);
				grid[i][j] = cell; 
			}
		}
	}
}