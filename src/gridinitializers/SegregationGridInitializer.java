package gridinitializers;

import cellclasses.SegregationCell;
import filereadcheck.FileReader;
import javafx.scene.Group;

public class SegregationGridInitializer extends GridInitializer {
	
	public SegregationGridInitializer(Group gr, FileReader fr) {
		super(gr, fr);
	}
	
	public void makeGrid() { 
		grid = new SegregationCell[DIMENSION][DIMENSION];
		for (int i=0;i<grid.length;i++) { 
			for (int j=0;j<grid[0].length;j++) { 
				SegregationCell cell = new SegregationCell(g, WORLD_SIZE/DIMENSION, i, j);
				grid[i][j] = cell;
			}
		}
		addRed();
		addBlue();
	}

	private void addRed(){
		int[] xRed = reader.populateCoorArray(reader.readProperty("redx"));
		int[] yRed = reader.populateCoorArray(reader.readProperty("redy"));
		for (int i=0;i<xRed.length;i++) { 
			SegregationCell segcell= (SegregationCell) grid[xRed[i]][yRed[i]];
			segcell.makeRed();
		}	
	}
	
	private void addBlue(){
		int[] xBlue = reader.populateCoorArray(reader.readProperty("bluex"));
		int[] yBlue = reader.populateCoorArray(reader.readProperty("bluey"));
		for (int i=0;i<xBlue.length;i++) { 
			SegregationCell segcell= (SegregationCell) grid[xBlue[i]][yBlue[i]];
			segcell.makeBlue();
		}	
	}
}
