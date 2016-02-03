package cellsociety_team24;
import javafx.scene.Group;

public class GridInitializer {

	private static FileReader reader;
	private static Group g; 
	private final int WORLD_SIZE = 800;
	private int DIMENSION; 
	private Cell[][] grid;
	
	GridInitializer (Group gr, FileReader fr) { 
		reader = fr;
		g = gr; 
		DIMENSION = Integer.parseInt(reader.readProperty("dimension"));
	}
	
	public void makeGrid() { 
		grid = new Cell[DIMENSION][DIMENSION];
		// populate grid with cells 
		for (int i=0;i<grid.length;i++) { 
			for (int j=0;j<grid[0].length;j++) { 
				grid[i][j] = new Cell();
			}
		}
	}
	
	public void addAlive() { 
		
	}
	
	public Cell[][] getGrid() {
		return grid;
	}
	
	// ask for properties from reader
	public void test() { 
		System.out.println(reader.readProperty("title"));
		System.out.println(reader.readProperty("underpop"));
		System.out.println(reader.readProperty("alivex"));
	}
}
