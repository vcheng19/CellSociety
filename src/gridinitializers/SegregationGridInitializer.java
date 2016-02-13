package gridinitializers;


import cellclasses.SegregationCell;
import cellsociety_team24.Grid;
import cellsociety_team24.HexagonGrid;
import cellsociety_team24.SquareGrid;
import cellsociety_team24.TriangleGrid;
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
				SegregationCell cell = new SegregationCell(i, j);
				grid[i][j] = cell;
			}
		}
		Grid thisGrid = new HexagonGrid(grid, WORLD_SIZE/DIMENSION, g);
		//System.out.println(WORLD_SIZE/DIMENSION);
		boolean wrap = false;
		thisGrid.createCells(wrap);
		addAttributes();
	}
	
	private void addAttributes(){
		for (int i=0;i<grid.length;i++) { 
			for (int j=0;j<grid[0].length;j++) { 
				SegregationCell cell = new SegregationCell(i, j);
				grid[i][j] = cell;
				if (doConfigCell(n2TagX, n2TagY, i, j)) cell.makeBlue();
				if (doConfigCell(n1TagX, n1TagY, i, j)) cell.makeRed();
			}
		}
	}

//	private void addRed(){
//		int[] xRed = reader.populateCoorArray(reader.readProperty("redx"));
//		int[] yRed = reader.populateCoorArray(reader.readProperty("redy"));
//		for (int i=0;i<xRed.length;i++) { 
//			SegregationCell segcell= (SegregationCell) grid[xRed[i]][yRed[i]];
//			segcell.makeRed();
//		}	
//	}
//	
//	private void addBlue(){
//		int[] xBlue = reader.populateCoorArray(reader.readProperty("bluex"));
//		int[] yBlue = reader.populateCoorArray(reader.readProperty("bluey"));
//		for (int i=0;i<xBlue.length;i++) { 
//			SegregationCell segcell= (SegregationCell) grid[xBlue[i]][yBlue[i]];
//			segcell.makeBlue();
//		}	

//	}
}
