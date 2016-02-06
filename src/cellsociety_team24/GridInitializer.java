package cellsociety_team24;
import javafx.scene.Group;

public abstract class GridInitializer {

	FileReader reader;
	Group g; 
	int WORLD_SIZE = 600;
	int DIMENSION; 
	String CELL_TYPE;
	public Cell[][] grid;
	
	GridInitializer (Group gr, FileReader fr) { 
		g = gr; 
		reader = fr;
		DIMENSION = Integer.parseInt(reader.readProperty("dimension"));
	}
	
//	public void makeGrid() { 
//		switch(CELL_TYPE) { 
//			case "Game of life":
//				grid = new GameOfLifeCell[DIMENSION][DIMENSION];
//			default: 
//				grid = new Cell[DIMENSION][DIMENSION];
//		}
//		for (int i=0;i<grid.length;i++) { 
//			for (int j=0;j<grid[0].length;j++) { 
//				Cell cell = new GameOfLifeCell(g, WORLD_SIZE/DIMENSION, i, j);
//				switch(CELL_TYPE) { 
//					case "Game of life":
//						cell = new GameOfLifeCell(g, WORLD_SIZE/DIMENSION, i, j);
//					default: 
//						cell = new GameOfLifeCell(g, WORLD_SIZE/DIMENSION, i, j);
//				}
//				grid[i][j] = cell;
//			}
//		}
//	}
	public abstract void makeGrid();
	
	public Cell[][] getGrid(){
		return grid;
	};
	
}
