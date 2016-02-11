package gridinitializers;

import cellclasses.SegregationCell;
import filereadcheck.FileReader;
import javafx.scene.Group;

public class SegregationGridInitializer extends GridInitializer {
	
	public SegregationGridInitializer(Group gr, FileReader fr) {
		super(gr, fr);
		addRed(getGrid());
		addBlue(getGrid());
	}

	private void addRed(Cell[][] grid){
		int[] xRed = reader.populateCoorArray(reader.readProperty("redx"));
		int[] yRed = reader.populateCoorArray(reader.readProperty("redy"));
		for (int i=0;i<xRed.length;i++) { 
			SegregationCell segcell= (SegregationCell) grid[xRed[i]][yRed[i]];
			segcell.makeRed();
		}	
	}
	
	private void addBlue(Cell[][] grid){
		int[] xBlue = reader.populateCoorArray(reader.readProperty("bluex"));
		int[] yBlue = reader.populateCoorArray(reader.readProperty("bluey"));
		for (int i=0;i<xBlue.length;i++) { 
			SegregationCell segcell= (SegregationCell) grid[xBlue[i]][yBlue[i]];
			segcell.makeBlue();
		}	
	}
}
