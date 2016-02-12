package gridinitializers;

import cellclasses.WaTorCell;
import filereadcheck.FileReader;
import javafx.scene.Group; 

public class WaTorGridInitializer extends GridInitializer{
	private int sharkEnergy; 
	private int fishEnergy;
	private final String sharkXTag = "sharkx"; 
	private final String sharkYTag = "sharky";
	private final String fishXTag = "fishx";
	private final String fishYTag = "fishy"; 
	
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
				if (doConfigCell(sharkXTag, sharkYTag, i, j)) cell.makeShark(sharkEnergy, 0);
				if (doConfigCell(fishXTag, fishYTag, i, j)) cell.makeFish(fishEnergy, 0);
			}
		}
//		addSharks();
//		addFish();
	}
	
//	public void addSharks(){
//		int[] xShark = reader.populateCoorArray(reader.readProperty("sharkx"));
//		int[] yShark = reader.populateCoorArray(reader.readProperty("sharky"));
//		for(int i = 0; i< xShark.length;i++){
//			WaTorCell watorcell = (WaTorCell) grid[xShark[i]][yShark[i]];
//			watorcell.makeShark(sharkEnergy, 0);
//		}
//	}
//	
//	public void addFish(){
//		int[] xFish = reader.populateCoorArray(reader.readProperty("fishx"));
//		int[] yFish = reader.populateCoorArray(reader.readProperty("fishy"));
//		for(int i = 0; i< xFish.length;i++){
//			WaTorCell watorcell = (WaTorCell) grid[xFish[i]][yFish[i]];
//			watorcell.makeFish(fishEnergy, 0);
//		}
//	}
	
}