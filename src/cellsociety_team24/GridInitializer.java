package cellsociety_team24;
import javafx.scene.Group;

public class GridInitializer {

	FileReader reader;
	Group g; 
	int WORLD_SIZE = 800;
	int DIMENSION; 
	String CELL_TYPE;
	public Cell[][] grid;
	
	GridInitializer (Group gr, FileReader fr) { 
		g = gr; 
		reader = fr;
		//CELL_TYPE = reader.readProperty("sim_type");
		DIMENSION = Integer.parseInt(reader.readProperty("dimension"));
	}
	
	
	public void makeGrid() { 
		switch(CELL_TYPE) { 
			case "Game of life":
				grid = new GameOfLifeCell[DIMENSION][DIMENSION];
			default: 
				grid = new Cell[DIMENSION][DIMENSION];
		}
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
		//addAlive();
	}
	
	// this method is specific to the game of life cell
	public void addAlive() { 
		int[] xAlive = reader.populateCoorArray(reader.readProperty("alivex"));
		int[] yAlive = reader.populateCoorArray(reader.readProperty("alivey"));
		for (int i=0;i<xAlive.length;i++) { 
			GameOfLifeCell golcell= (GameOfLifeCell) grid[xAlive[i]][yAlive[i]];
			golcell.makeAlive();
		}	
	}
	
	public Cell[][] getGrid(){
		return grid;
	};
	
	// ask for properties from reader
//	public void test() { 
//		System.out.println(reader.readProperty("title"));
//		System.out.println(reader.readProperty("underpop"));
//		System.out.println(reader.readProperty("alivex"));
//	}
}
