package cellsociety_team24;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WaTorRuleEnforcer extends RuleEnforcer{
	private WaTorCell[][] myGrid;
	private Random random; 
	private int DIMENSION;
	private int fishSpawn; 
	private int sharkSpawn;
	
	public WaTorRuleEnforcer(Cell[][] grid, FileReader fr) {
		super(grid, fr);
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
		sharkSpawn = Integer.parseInt(reader.readProperty("sharkSpawn"));
	}

	public void iterateGrid(){
		for(int r = 0; r < myGrid.length; r++){
			for(int c = 0; c < myGrid.length; c++){
				WaTorCell curCell = myGrid[r][c];
				//int x = actualCell.getX();
				//int y = actualCell.getY(); 
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
//				int randomPick = random.nextInt((neighbors.size() - 1)) + 1;
//				int [] arrayNeighbors = neighbors.get(randomPick); //Something is going on here
				eatFish(shark, chooseNeighbor(fishNear));
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

//	public void doAction(int x, int y){ 
//		List<int[]> neighbors = new ArrayList<int[]>();
//		WaTorCell curCell = myGrid[x][y];
//		if(curCell.isFish()){ //For fish
//			neighbors = getNeighbors(x, y, true); 
//			curCell.updateTurn();
//			if(!neighbors.isEmpty()){//Moving a fish
//				int randomPick = (int) Math.random()*(neighbors.size() - 1);
//				int [] arrayNeighbors = neighbors.get(randomPick);
//				move(x, y, arrayNeighbors[0],arrayNeighbors[1], curCell.isFish(), true);
//			}
//		}
//		else{ //For shark
//			if(curCell.getEnergy() == 0){//If shark has starved
//				curCell.makeOcean();
//				curCell.updateEnergy(curCell.isShark(), true);
//			}
//			else{
//				curCell.updateEnergy(curCell.isShark(), false);
//				neighbors = getNeighbors(x, y, false);
//				curCell.updateTurn();
//				if(!neighbors.isEmpty()){//If fish were found
//					int randomPick = random.nextInt((neighbors.size() - 1)) + 1;
//					int [] arrayNeighbors = neighbors.get(randomPick); //Something is going on here
//					move(x, y, arrayNeighbors[0],arrayNeighbors[1], curCell.isFish(), true);
//				}
//				else{//No fish were found
//					neighbors = getNeighbors(x, y, false);
//					if(!neighbors.isEmpty()){
//						int randomPick = random.nextInt((neighbors.size() - 1)) + 1;
//						int [] arrayNeighbors = neighbors.get(randomPick); //Something is going on here
//						move(x, y, arrayNeighbors[0],arrayNeighbors[1], curCell.isFish(), false);
//					}
//				}
//			}
//		}
//		checkIfCanSpawn(x, y, curCell.getTurnAlive());
//	}
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
//		if(turnsAlive >= 3){//This is a magic value
//			curCell.resetTurn();
//			List<int []> arrayEmptyNeighbors = new ArrayList<int[]>();
//			arrayEmptyNeighbors = getNeighbors(x,y, true); 
//			if(!arrayEmptyNeighbors.isEmpty()){
//				int randomPick = (int) Math.random()*(arrayEmptyNeighbors.size() - 1);
//				int [] arrayNeighbors = arrayEmptyNeighbors.get(randomPick); 
//				spawn(arrayNeighbors[0], arrayNeighbors[1], myGrid[x][y].isFish());
//			}
//		}
	}
	
	// move fish 
//	public void move(int xOld, int yOld, int xNew, int yNew, boolean fish, boolean eat){
//		if(fish){	
//			myGrid[xNew][yNew].makeFish(); 
//			myGrid[xOld][yOld].makeOcean(); 
//		}
//		else{//Move shark
//			myGrid[xNew][yNew].makeShark();
//			myGrid[xOld][yOld].makeOcean();
//			if(eat){//If the shark ate a fish
//				myGrid[xNew][yNew].updateEnergy(myGrid[xNew][yNew].isShark(), true);
//			}
//		}
//		return;
//	}
	
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
			int rNew = Math.abs((curCell.getX() + rChange) % DIMENSION); 
			if(rNew != curCell.getX()){
				for(int cChange = -1; cChange <= 1; cChange++){
					int cNew = Math.abs((curCell.getY() + cChange) % DIMENSION); 
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
			int rNew = Math.abs((curCell.getX() + rChange) % DIMENSION); 
			if(rNew != curCell.getX()){
				for(int cChange = -1; cChange <= 1; cChange++){
					int cNew = Math.abs((curCell.getY() + cChange) % DIMENSION); 
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
	
//	public List<WaTorCell> getNeighbors(int r, int c, boolean fish){//Needs to account for torus behavior + refactor both sharks and fish
//		List<WaTorCell> neighborList = new ArrayList<WaTorCell>(); 
//		for(int rChange = -1; rChange <= 1; rChange++){
//			int rNew = Math.abs((r + rChange) % DIMENSION); 
//			if(rNew != r){
////				if(rNew < 0) rNew = myGrid.length - 1;
////				else if(rNew >= myGrid.length - 1)	rNew = 0; 
//				for(int cChange = -1; cChange <= 1; cChange++){
//					int cNew = Math.abs((c + cChange) % DIMENSION); 
//					//int cNew = c + cChange;
//					if(cNew != c){
////						if(cNew < 0)	cNew = myGrid.length - 1;
////						else if(cNew >= myGrid.length - 1)	cNew = 0; 
//						if(fish){
//							if(myGrid[rNew][cNew].isOcean()){ //This portion must be refactored...
//								//int [] coordinates = new int[2];
//								//coordinates[0] = rNew; coordinates[1] = cNew; //Can i even do this 
//								neighborList.add(myGrid[rNew][cNew]);
//							}
//						}
//						else{
//							// generate fish neighbors
//							if(myGrid[rNew][cNew].isFish()){
////								int[] coordinates = new int[2];
////								coordinates[0] = rNew; coordinates[1] = cNew; 
//								neighborList.add(myGrid[rNew][cNew]);
//							}
//							if (neigh)
//							// if neighbor list empty 
//							// find ocean neighbors
//						}
//					}
//				}
//			}
//		}
//		return neighborList; 
//	}
	
//	public void returnNeighbor(int rNew, int cNew, boolean fish){
//		if(fish){
//			if(myGrid[rNew][cNew].isOcean()){
//				int [] coordinates = new int[2];
//				coordinates[0] = rNew;
//				coordinates[1] = cNew;
//				
//			}
//		}
//	}
	
}
