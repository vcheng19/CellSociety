package gridinitializers;
import cellclasses.Cell;
import cellsociety_team24.Grid;
import filereadcheck.FileReader;
import javafx.scene.Group;

public abstract class GridInitializer {

	FileReader reader;
	Group g; 
	final int WORLD_SIZE = 600;
	int DIMENSION; 
	String CELL_TYPE;
	public Cell[][] grid;
	public Grid thisGrid;
	boolean wrap;
	
	GridInitializer (Grid thisGrid1, Group gr, FileReader fr, boolean isWrapped) { 
		g = gr; 
		reader = fr;
		DIMENSION = Integer.parseInt(reader.readProperty("dimension"));
		thisGrid = thisGrid1;
		wrap = isWrapped;
		makeGrid();
	}
	
	public Cell[][] getGrid(){
		return grid;
	}
	
	public boolean doConfigCell(String typeX, String typeY, int x, int y) { 
		int[] xs = reader.populateCoorArray(reader.readProperty(typeX));
		int[] ys = reader.populateCoorArray(reader.readProperty(typeY));
		for (int i=0;i<xs.length;i++) { 
			if (x == xs[i] && y == ys[i]) { 
				return true;
			}
		}	
		return false;
	}
	
	public abstract void addAttributes();
	
	public abstract void makeGrid();
	
}
