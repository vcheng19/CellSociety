package gridinitializers;


import cellclasses.SegregationCell;
import filereadcheck.FileReader;
import javafx.scene.Group;

public class SegregationGridInitializer extends GridInitializer {
	private final String n1TagX = "redx"; 
	private final String n1TagY = "redy"; 
	private final String n2TagX = "bluex"; 
	private final String n2TagY = "bluey"; 
	
	public SegregationGridInitializer(Group gr, FileReader fr) {
		super(gr, fr);
	}
	
	public void makeGrid() { 
		grid = new SegregationCell[DIMENSION][DIMENSION];
		for (int i=0;i<grid.length;i++) { 
			for (int j=0;j<grid[0].length;j++) { 
				SegregationCell cell = new SegregationCell(g, WORLD_SIZE/DIMENSION, i, j);
				grid[i][j] = cell;
				if (doConfigCell(n2TagX, n2TagY, i, j)) cell.makeBlue();
				if (doConfigCell(n1TagX, n1TagY, i, j)) cell.makeRed();
			}
		}
	}
}
