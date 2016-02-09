package cellsociety_team24;
import javafx.scene.Group;

public abstract class GridInitializer {

	FileReader reader;
	Group g; 
	final int WORLD_SIZE = 600;
	int DIMENSION; 
	String CELL_TYPE;
	public Cell[][] grid;
	
	GridInitializer (Group gr, FileReader fr) { 
		g = gr; 
		reader = fr;
		DIMENSION = Integer.parseInt(reader.readProperty("dimension"));
		makeGrid();
	}
	
	public Cell[][] getGrid(){
		return grid;
	}

	public abstract void makeGrid();
	
}
