package cellsociety_team24;
import javafx.scene.Group;

public class GridInitializer {

	private static FileReader reader;
	private static Group g; 
	private final int WORLD_SIZE = 800;
	private int DIMENSION; 
	private String CELL_TYPE;
	private Cell[][] grid;
	
	GridInitializer (Group gr, FileReader fr) { 
		reader = fr;
		g = gr; 
		DIMENSION = Integer.parseInt(reader.readProperty("dimension"));
		CELL_TYPE = reader.readProperty("sim_type");
	}
	
	public void makeGrid() { 
		grid = new Cell[DIMENSION][DIMENSION];
		for (int i=0;i<grid.length;i++) { 
			for (int j=0;j<grid[0].length;j++) { 
				Cell cell = new GameOfLifeCell(g, WORLD_SIZE/DIMENSION, i, j);
				switch(CELL_TYPE) { 
					case "Game of life":
						cell = new GameOfLifeCell(g, WORLD_SIZE/DIMENSION, i, j);
					default: 
						cell = new GameOfLifeCell(g, WORLD_SIZE/DIMENSION, i, j);
				}
				grid[i][j] = cell;
			}
		}
		addAlive();
	}
	
	// this method is specific to the game of life cell
	public void addAlive() { 
		int[] xAlive = FileReader.populateCoorArray(reader.readProperty("alivex"));
		int[] yAlive = FileReader.populateCoorArray(reader.readProperty("alivey"));
		for (int i=0;i<xAlive.length;i++) { 
			GameOfLifeCell golcell= (GameOfLifeCell) grid[xAlive[i]][yAlive[i]];
			golcell.makeAlive();
		}	
	}
	
	public Cell[][] getGrid() {
		return grid;
	}
	
	// ask for properties from reader
//	public void test() { 
//		System.out.println(reader.readProperty("title"));
//		System.out.println(reader.readProperty("underpop"));
//		System.out.println(reader.readProperty("alivex"));
//	}
}
