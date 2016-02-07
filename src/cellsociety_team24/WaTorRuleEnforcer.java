package cellsociety_team24;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WaTorRuleEnforcer extends RuleEnforcer{
	private WaTorCell[][] myGrid;
	private static int DIMENSION;
	private static int fishSpawn; 
	private static int sharkSpawn;
	private int sharkEnergy;
	private int fishEnergy;
	private int fishNumber = 0;
	private int sharkNumber = 0; 
	public WaTorRuleEnforcer(Cell[][] grid, FileReader reader) {
		super(grid, reader);
		myGrid = new WaTorCell[grid.length][grid.length];
		for (int i=0;i<grid.length;i++) { 
			for (int j=0;j<grid.length;j++) { 
				myGrid[i][j] = (WaTorCell) grid[i][j];
			}
		}
	}
	
	public void initializeParameters() { 
		DIMENSION = Integer.parseInt(reader.readProperty("dimension"));
		fishSpawn = Integer.parseInt(reader.readProperty("fishspawn"));
		sharkSpawn = Integer.parseInt(reader.readProperty("sharkspawn"));
		sharkEnergy = Integer.parseInt(reader.readProperty("sharkenergy"));
		fishEnergy = Integer.parseInt(reader.readProperty("fishenergy"));
	}

	public void iterateGrid(){
		fishNumber = 0;
		sharkNumber = 0;
		for(int r = 0; r < myGrid.length; r++){
			for(int c = 0; c < myGrid.length; c++){
				WaTorCell curCell = myGrid[r][c];
				if (!curCell.getMoved()) {
					if(curCell.isFish()) {
						fishNumber++;
						doFishAction(curCell);	
					} else if(curCell.isShark()){
						sharkNumber++;
						doSharkAction(curCell);
					}
				}
			}
		}
		resetMovedGrid();
	}
	
	public void resetMovedGrid() { 
		for (int r=0;r<myGrid.length;r++) { 
			for (int c=0;c<myGrid.length;c++) { 
				myGrid[r][c].setMoved(false);
			}
		}
	}
	
	public void doFishAction(WaTorCell fish) { 
		fish.updateTurn();
		List<WaTorCell> oceanNear = getOceanNeighbors(fish);
		checkIfCanSpawn(fish);
		if(!oceanNear.isEmpty()){//Moving a fish
			moveFish(fish, chooseNeighbor(oceanNear));
		}
	}
	
	public void doSharkAction(WaTorCell shark) { 
		if(shark.getEnergy() == 0){//If shark has starved
			shark.makeOcean(); // make it die
		}	
		else{ // if shark still has energy level
			checkIfCanSpawn(shark);
			shark.updateTurn();
			//shark.updateEnergy(false);
			List<WaTorCell> fishNear = getFishNeighbors(shark);
			List<WaTorCell> ocean = getOceanNeighbors(shark);
			if(!fishNear.isEmpty()){//If fish were found
				WaTorCell fish = chooseNeighbor(fishNear);
				eatFish(shark, fish);
			}
			else{//No fish were found
				if(!ocean.isEmpty()){
					moveShark(shark, chooseNeighbor(ocean));
				}
			}
		}
	}
	
	public WaTorCell chooseNeighbor(List<WaTorCell> neighbors) { 
		int randomPick = (int) (Math.floor(Math.random() * (neighbors.size() -1)));
		WaTorCell ne = neighbors.get(randomPick); //Something is going on here
		return ne;
	}

	public void spawn(WaTorCell ocean, boolean fish){
		if (fish){
			ocean.makeFish(fishEnergy, 0); 
		}
		else{
			ocean.makeShark(sharkEnergy, 0); 
		}
		return; 
	}
	
	public void checkIfCanSpawn(WaTorCell curCell){
		if ((curCell.isFish() && curCell.getTurnsAlive() >= fishSpawn) ||
			 (curCell.isShark() && curCell.getTurnsAlive() >= sharkSpawn)) { 
			List<WaTorCell> oceanSpots = getOceanNeighbors(curCell);
			
			if (!oceanSpots.isEmpty()) { 
				spawn(chooseNeighbor(oceanSpots), curCell.isFish());
				curCell.resetTurn();
			}
		}
	}

	public void moveFish(WaTorCell fish, WaTorCell ocean) {
		fish.makeOcean();
		ocean.makeFish(fish.getEnergy(), fish.getTurnsAlive());
		ocean.setMoved(true);
	}
	
	public void moveShark(WaTorCell shark, WaTorCell ocean) { 
		ocean.makeShark(shark.getEnergy(), shark.getTurnsAlive());
		ocean.setEnergy(shark.getEnergy()-1);
		shark.makeOcean();
		ocean.setMoved(true);
	}
	
	public void eatFish(WaTorCell shark, WaTorCell fish) { 
		fish.makeShark(shark.getEnergy(), shark.getTurnsAlive());
		shark.setEnergy(shark.getEnergy()+fish.getEnergy());
		shark.makeOcean();
		fish.setMoved(true);
	}
	
	
	public List<WaTorCell> getFishNeighbors (WaTorCell curCell){  
		List<WaTorCell> neighborList = new ArrayList<WaTorCell>(); 
		for(int rChange = -1; rChange <= 1; rChange++){
			if(rChange != 0){
				int rNew = curCell.getX() + rChange; 
				if(rNew < 0) rNew = DIMENSION - 1;
				else if(rNew > DIMENSION - 1) rNew = 0;
				if(myGrid[rNew][curCell.getY()].isFish()){
					neighborList.add(myGrid[rNew][curCell.getY()]);
				}
			}
		}
		for(int cChange = -1; cChange <= 1; cChange++){
			if(cChange != 0){
				int cNew = curCell.getY() + cChange; 
				if(cNew < 0) cNew = DIMENSION - 1;
				else if(cNew > DIMENSION - 1) cNew = 0;
				if(myGrid[curCell.getX()][cNew].isFish()){
					neighborList.add(myGrid[curCell.getX()][cNew]);
				}
			}
		}
		return neighborList; 
	}
//	public List<WaTorCell> getFishNeighbors (WaTorCell curCell){  
//		List<WaTorCell> neighborList = new ArrayList<WaTorCell>(); 
//		for(int rChange = -1; rChange <= 1; rChange++){
//			int rNew = curCell.getX() + rChange;
//			if (rNew < 0) rNew = DIMENSION - 1;
//			else if (rNew > DIMENSION - 1) rNew = 0;
//					// Math.abs((curCell.getX() + rChange) % (DIMENSION - 1)); 
//			if(rNew != curCell.getX()){
//				for(int cChange = -1; cChange <= 1; cChange++){
//					//int cNew = Math.abs((curCell.getY() + cChange) % (DIMENSION - 1)); 
//					int cNew = curCell.getX() + cChange;
//					if (cNew < 0) cNew = DIMENSION - 1;
//					else if (cNew > DIMENSION - 1) cNew = 0;
//					//if (!myGrid[rNew][cNew].getMoved()) {
//					if(cNew != curCell.getY()){
//						if(myGrid[rNew][cNew].isFish()){ 
//								neighborList.add(myGrid[rNew][cNew]);
//						}
//					}
//					//}
//				}
//			}
//		}
//		return neighborList; 
//	}
	
	
	public List<WaTorCell> getOceanNeighbors (WaTorCell curCell){  
		List<WaTorCell> neighborList = new ArrayList<WaTorCell>(); 
		for(int rChange = -1; rChange <= 1; rChange++){
			if(rChange != 0){
				int rNew = curCell.getX() + rChange; 
				if(rNew < 0) rNew = DIMENSION - 1;
				else if(rNew > DIMENSION - 1) rNew = 0;
				if(myGrid[rNew][curCell.getY()].isOcean()){
					neighborList.add(myGrid[rNew][curCell.getY()]);
				}
			}
		}
		for(int cChange = -1; cChange <= 1; cChange++){
			if(cChange != 0){
				int cNew = curCell.getY() + cChange; 
				if(cNew < 0) cNew = DIMENSION - 1;
				else if(cNew > DIMENSION - 1) cNew = 0;
				if(myGrid[curCell.getX()][cNew].isOcean()){
					neighborList.add(myGrid[curCell.getX()][cNew]);
				}
			}
		}
		return neighborList; 
	}
}
