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

	private void makeRed(){
		int[] xRed = reader.populateCoorArray(reader.readProperty("redx"));
		int[] yRed = reader.populateCoorArray(reader.readProperty("redy"));
		for (int i=0;i<xRed.length;i++) { 
			SegregationCell segcell= grid[xRed[i]][yRed[i]];
			segcell.makeRed();
		}	
	}
	
	private void makeBlue(){
		int[] xBlue = reader.populateCoorArray(reader.readProperty("bluex"));
		int[] yBlue = reader.populateCoorArray(reader.readProperty("bluey"));
		for (int i=0;i<xBlue.length;i++) { 
			SegregationCell segcell= grid[xBlue[i]][yBlue[i]];
			segcell.makeBlue();
		}	
	}
	
	public SegregationCell[][] getGrid() {
		return grid;
	}
	

}
