package gridinitializers;

import cellclasses.WaTorCell;
import filereadcheck.FileReader;
import javafx.scene.Group; 

public class WaTorGridInitializer extends GridInitializer{
	private int sharkEnergy; 
	private int fishEnergy;
	
	public WaTorGridInitializer(Group gr, FileReader fr){
		super(gr, fr);
		getEnergies();
		addSharks(getGrid());
		addFish(getGrid());
	}
	
	public void addSharks(Cell[][] grid){
		int[] xShark = reader.populateCoorArray(reader.readProperty("sharkx"));
		int[] yShark = reader.populateCoorArray(reader.readProperty("sharky"));
		for(int i = 0; i< xShark.length;i++){
			WaTorCell watorcell = (WaTorCell) grid[xShark[i]][yShark[i]];
			watorcell.makeShark(sharkEnergy, 0);
		}
	}
	
	public void addFish(Cell[][] grid){
		int[] xFish = reader.populateCoorArray(reader.readProperty("fishx"));
		int[] yFish = reader.populateCoorArray(reader.readProperty("fishy"));
		for(int i = 0; i< xFish.length;i++){
			WaTorCell watorcell = (WaTorCell) grid[xFish[i]][yFish[i]];
			watorcell.makeFish(fishEnergy, 0);
		}
	}
	
}
