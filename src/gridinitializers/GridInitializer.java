package gridinitializers;
import cellclasses.Cell;
import filereadcheck.FileReader;
import javafx.scene.Group;

public abstract class GridInitializer {

	private FileReader reader;
	private Group g; 
	private final int WORLD_SIZE = 600;
	private int DIMENSION; 
//	private String CELL_TYPE;
	private Cell[][] grid;
	
	GridInitializer (Group gr, FileReader fr) { 
		g = gr; 
		reader = fr;
		DIMENSION = Integer.parseInt(reader.readProperty("dimension"));
		grid = new Cell[DIMENSION][DIMENSION]; 
		makeGrid();
	}
	
	public Cell[][] getGrid(){
		return grid;
	}
	
	public FileReader getReader(){
		return reader;
	}
	
	public Group getGroup(){
		return g;
	}
	
	public int getWorldSize(){
		return WORLD_SIZE;
	}
	
	public int getDimension(){
		return DIMENSION;
	}

	public abstract void makeGrid();
	
}
