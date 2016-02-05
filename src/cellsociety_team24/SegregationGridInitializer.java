package cellsociety_team24;

import javafx.scene.Group;

public class SegregationGridInitializer extends GridInitializer {
	private static SegregationCell[][] grid;
	
	SegregationGridInitializer(Group gr, FileReader fr) {
		super(gr, fr);
		// TODO Auto-generated constructor stub
	}
	
	public void makeGrid() { 
		grid = new SegregationCell[DIMENSION][DIMENSION];
		for (int i=0;i<grid.length;i++) { 
			for (int j=0;j<grid[0].length;j++) { 
				SegregationCell cell = new SegregationCell(g, WORLD_SIZE/DIMENSION, i, j);
				grid[i][j] = cell;
			}
		}
		makeRed();
		makeBlue();
	}
	//************REFACTOR THIS PART OF THE CODE: VVVVVVV
	private void makeRed(){
		int[] xAlive = reader.populateCoorArray(reader.readProperty("redx"));
		int[] yAlive = reader.populateCoorArray(reader.readProperty("redy"));
		for (int i=0;i<xAlive.length;i++) { 
			SegregationCell segcell= grid[xAlive[i]][yAlive[i]];
			segcell.makeRed();
		}	
	}
	
	private void makeBlue(){
		int[] xAlive = reader.populateCoorArray(reader.readProperty("bluex"));
		int[] yAlive = reader.populateCoorArray(reader.readProperty("bluey"));
		for (int i=0;i<xAlive.length;i++) { 
			SegregationCell segcell= grid[xAlive[i]][yAlive[i]];
			segcell.makeBlue();
		}	
	}
	
	public SegregationCell[][] getGrid() {
		return grid;
	}
	

}
