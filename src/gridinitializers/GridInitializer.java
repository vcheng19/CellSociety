package gridinitializers;
import cellclasses.Cell;
import filereadcheck.FileReader;
import javafx.scene.Group;

public abstract class GridInitializer {

	private FileReader reader;
	private Group g; 
	private final int WORLD_SIZE = 600;
	private int DIMENSION; 
	private String cellType;
	private Cell[][] grid;
	
	GridInitializer (Group gr, FileReader fr) { 
		g = gr; 
		reader = fr;
		DIMENSION = Integer.parseInt(reader.readProperty("dimension"));
		makeGrid();
	}
	
	public int getDimension(){
		return DIMENSION;
	}
	
	public String getCellType(){
		return cellType;	
	}
	
	public Group getGroup(){
		return g;
	}
	
	public Cell[][] getGrid(){
		return grid;
	}

	public abstract void makeGrid();
	
}
