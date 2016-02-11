package gridinitializers;

import cellclasses.FireCell;
import filereadcheck.FileReader;
import javafx.scene.Group;

public class FireGridInitializer extends GridInitializer {
	
	public FireGridInitializer(Group gr, FileReader fr) {
		super(gr, fr);
		makeBorder();
		addFire();
	}
	
	public void makeBorder(){
		Cell[][] grid = getGrid();
		for(Cell x: grid){
			row = x.getX();
			col = y.getY();
			if(row == 0 || col == 0 || row == grid.size() -1 || col == grid.size() -1){
				x.makeEmpty();
			}
		}
	}
	
	private void addFire(){
		Cell[][] grid = getGrid();
		int fireX = Integer.parseInt(reader.readProperty("fireX"));
		int fireY = Integer.parseInt(reader.readProperty("fireY"));
		FireCell fireCell = (FireCell) grid[fireX][fireY];
		fireCell.makeFire();
	}
	
}
