package cellsociety_team24;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WaTorRuleEnforcer extends RuleEnforcer{
	private WaTorCell[][] myGrid;
	private static int DIMENSION;
	private static int fishSpawn; 
	private static int sharkSpawn;
	
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
	}

	public void iterateGrid(){
		for(int r = 0; r < myGrid.length; r++){
			for(int c = 0; c < myGrid.length; c++){
				WaTorCell curCell = myGrid[r][c];
				if(curCell.isFish()) {
					doFishAction(curCell);	
				} else if(curCell.isShark()){
					doSharkAction(curCell);
				}
			}
		}
	}
	
	public void doFishAction(WaTorCell fish) { 
		fish.updateTurn();
		List<WaTorCell> oceanNear = getOceanNeighbors(fish);
		if(!oceanNear.isEmpty()){//Moving a fish
			moveFish(fish, chooseNeighbor(oceanNear));
		}
	}
	
	public void doSharkAction(WaTorCell shark) { 
		if(shark.getEnergy() == 0){//If shark has starved
			shark.makeOcean(); // make it die
		}	
		else{ // if shark still has energy level
			shark.updateTurn();
			shark.updateEnergy(false);
			List<WaTorCell> fishNear = getFishNeighbors(shark);
			List<WaTorCell> ocean = getOceanNeighbors(shark);
			if(!fishNear.isEmpty()){//If fish were found
				eatFish(shark, chooseNeighbor(fishNear));
//				shark.updateEnergy();
			}
			else{//No fish were found
				moveShark(shark, chooseNeighbor(ocean));
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
			ocean.makeFish(); 
		}
		else{
			ocean.makeShark(); 
		}
		return; 
	}
	
	public void checkIfCanSpawn(WaTorCell curCell){
		if ((curCell.isFish() && curCell.getTurnAlive() >= fishSpawn) ||
			 (curCell.isShark() && curCell.getTurnAlive() >= sharkSpawn)) { 
			List<WaTorCell> oceanSpots = getOceanNeighbors(curCell);
			if (!oceanSpots.isEmpty()) { 
				spawn(chooseNeighbor(oceanSpots), curCell.isFish());
				curCell.resetTurn();
			}
		}
	}

	public void moveFish(WaTorCell fish, WaTorCell ocean) {
		fish.makeOcean();
		ocean.makeFish();
	}
	
	public void moveShark(WaTorCell shark, WaTorCell ocean) { 
		ocean = shark;
		shark.makeOcean();
	}
	
	public void eatFish(WaTorCell shark, WaTorCell fish) { 
		fish = shark;
		shark.makeOcean();
	}
	
	public List<WaTorCell> getFishNeighbors (WaTorCell curCell){  
		List<WaTorCell> neighborList = new ArrayList<WaTorCell>(); 
		for(int rChange = -1; rChange <= 1; rChange++){
			int rNew = Math.abs((curCell.getX() + rChange) % (DIMENSION - 1)); 
			if(rNew != curCell.getX()){
				for(int cChange = -1; cChange <= 1; cChange++){
					int cNew = Math.abs((curCell.getY() + cChange) % (DIMENSION - 1)); 
					if(cNew != curCell.getY()){
						if(myGrid[rNew][cNew].isFish()){ 
								neighborList.add(myGrid[rNew][cNew]);
						}
					}
				}
			}
		}
		return neighborList; 
	}
	
	public List<WaTorCell> getOceanNeighbors (WaTorCell curCell){  
		List<WaTorCell> neighborList = new ArrayList<WaTorCell>(); 
		for(int rChange = -1; rChange <= 1; rChange++){
			int rNew = Math.abs((curCell.getX() + rChange) % (DIMENSION - 1)); 
			if(rNew != curCell.getX()){
				for(int cChange = -1; cChange <= 1; cChange++){
					int cNew = Math.abs((curCell.getY() + cChange) % (DIMENSION - 1)); 
					if(cNew != curCell.getY()){
						if(myGrid[rNew][cNew].isOcean()){ 
								neighborList.add(myGrid[rNew][cNew]);
						}
					}
				}
			}
		}
		return neighborList; 
	}
}
