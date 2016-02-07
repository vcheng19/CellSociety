package cellsociety_team24;

import javafx.scene.Group; 

public class WaTorGridInitializer extends GridInitializer{
	private static WaTorCell[][] grid;
	private static boolean[][] oceanGrid;
	private static int sharkEnergy; 
	private static int fishEnergy;
	
	public WaTorGridInitializer(Group gr, FileReader fr){
		super(gr, fr);
	}
	
	public void makeGrid(){
		grid = new WaTorCell[DIMENSION][DIMENSION];
		oceanGrid = new boolean[DIMENSION][DIMENSION];
		fishEnergy = Integer.parseInt(reader.readProperty("fishenergy"));
		for(int i = 0;i<grid.length;i++){
			for(int j = 0; j < grid[0].length;j++){
				WaTorCell cell = new WaTorCell(g, WORLD_SIZE/DIMENSION, i, j, sharkEnergy, fishEnergy);
				grid[i][j] = cell; 
				oceanGrid[i][j] = true;
			}
		}
		addSharks();
		addFish();
		addOcean();
	}
	
	public void addSharks(){
		int[] xShark = reader.populateCoorArray(reader.readProperty("sharkx"));
		int[] yShark = reader.populateCoorArray(reader.readProperty("sharky"));
		sharkEnergy = Integer.parseInt(reader.readProperty("sharkenergy"));
		for(int i = 0; i< xShark.length;i++){
			WaTorCell watorcell = grid[xShark[i]][yShark[i]];
			oceanGrid[xShark[i]][yShark[i]] = false;
			watorcell.makeShark();
		}
	}
	
	public void addFish(){
		int[] xFish = reader.populateCoorArray(reader.readProperty("fishx"));
		int[] yFish = reader.populateCoorArray(reader.readProperty("fishy"));
		for(int i = 0; i< xFish.length;i++){
			WaTorCell watorcell = grid[xFish[i]][yFish[i]];
			oceanGrid[xFish[i]][yFish[i]] = false;
			watorcell.makeFish();
		}
	}
	
	public void addOcean(){
		for(int i = 0; i< grid.length; i++){
			for(int j = 0; j < grid.length; j++){
				if(oceanGrid[i][j]){
					grid[i][j].makeOcean();
				}
			}
		}
	}
	
	public WaTorCell[][] getGrid(){
		return grid;
	}
	
}