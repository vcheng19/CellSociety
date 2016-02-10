package cellsociety_team24;

import filereadcheck.FileReader;
import javafx.scene.Group; 

public class WaTorGridInitializer extends GridInitializer{
	private int sharkEnergy; 
	private int fishEnergy;
	
	public WaTorGridInitializer(Group gr, FileReader fr){
		super(gr, fr);
	}
	
	public void makeGrid(){
		grid = new WaTorCell[DIMENSION][DIMENSION];
		sharkEnergy = Integer.parseInt(reader.readProperty("sharkenergy"));
		fishEnergy = Integer.parseInt(reader.readProperty("fishenergy"));
		for(int i = 0;i<grid.length;i++){
			for(int j = 0; j < grid[0].length;j++){
				WaTorCell cell = new WaTorCell(g, WORLD_SIZE/DIMENSION, i, j);
				grid[i][j] = cell; 
			}
		}
		addSharks();
		addFish();
	}
	
	public void addSharks(){
		int[] xShark = reader.populateCoorArray(reader.readProperty("sharkx"));
		int[] yShark = reader.populateCoorArray(reader.readProperty("sharky"));
		for(int i = 0; i< xShark.length;i++){
			WaTorCell watorcell = (WaTorCell) grid[xShark[i]][yShark[i]];
			watorcell.makeShark(sharkEnergy, 0);
		}
	}
	
	public void addFish(){
		int[] xFish = reader.populateCoorArray(reader.readProperty("fishx"));
		int[] yFish = reader.populateCoorArray(reader.readProperty("fishy"));
		for(int i = 0; i< xFish.length;i++){
			WaTorCell watorcell = (WaTorCell) grid[xFish[i]][yFish[i]];
			watorcell.makeFish(fishEnergy, 0);
		}
	}
	
}