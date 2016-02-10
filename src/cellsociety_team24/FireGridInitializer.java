package cellsociety_team24;

import filereadcheck.FileReader;
import javafx.scene.Group;

public class FireGridInitializer extends GridInitializer {
	
	public FireGridInitializer(Group gr, FileReader fr) {
		super(gr, fr);
	}
	
	public void makeGrid(){
		grid = new FireCell[DIMENSION][DIMENSION];
		for (int i=0;i<grid.length;i++) { 
			for (int j=0;j<grid[0].length;j++) { 
				FireCell cell = new FireCell(g, WORLD_SIZE/DIMENSION, i, j);
				grid[i][j] = cell;
				if(i == 0 || i == grid.length -1 || j ==0 || j == grid[0].length-1){
					cell.makeEmpty();
				}
			}
		}
		addFire();
	}
	
	private void addFire(){
		int fireX = Integer.parseInt(reader.readProperty("fireX"));
		int fireY = Integer.parseInt(reader.readProperty("fireY"));
		FireCell fireCell = (FireCell) grid[fireX][fireY];
		fireCell.makeFire();
	}
	
}
