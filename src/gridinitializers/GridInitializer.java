package gridinitializers;
import cellclasses.Cell;
import cellsociety_team24.Grid;
import filereadcheck.FileReader;
import javafx.scene.Group;

public abstract class GridInitializer {

	private FileReader reader;
	private Group g; 
	private final int WORLD_SIZE = 600;
	private int DIMENSION; 
	private Cell[][] grid;
	private Grid thisGrid;
	private boolean wrap;
	
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
	
	public Grid getThisGrid(){
		return thisGrid; 
	}
	
	public int getDimension(){
		return DIMENSION;
	}
	
	public int getWorldSize(){
		return WORLD_SIZE;
	}
	
	public void setGrid(Cell[][] gridOther){
		grid = gridOther; 
	}
	
	public boolean getWrap(){
		return wrap;
	}
	
	public Group getGroup(){
		return g;
	}
	
	public FileReader getReader(){
		return reader;
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
	
	public abstract void addAttributes(Cell[][] grid);
	
	public abstract void makeGrid();
	
}
