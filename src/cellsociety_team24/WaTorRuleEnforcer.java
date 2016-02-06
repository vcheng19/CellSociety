package cellsociety_team24;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WaTorRuleEnforcer extends RuleEnforcer{
	private WaTorCell[][] myGrid; 
	private int sharkSpawn = 7;
	private int fishSpawn = 2;
	
	
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
		
	}

	public void iterateGrid(){
		for(int r = 0; r < myGrid.length; r++){
			for(int c = 0; c < myGrid.length; c++){
				WaTorCell actualCell = myGrid[r][c];
				int x = actualCell.getX();
				int y = actualCell.getY(); 
				if(myGrid[x][y].isFish() || myGrid[x][y].isShark()){
					doAction(x, y);
				}
			}
		}
	}

	public void doAction(int x, int y){
		List<int[]> neighbors = new ArrayList<int[]>();
		if(myGrid[x][y].isFish()){ //For fish
			neighbors = getNeighbors(x, y, true); 
			myGrid[x][y].updateTurn();
			if(!neighbors.isEmpty()){//Moving a fish
//				int randomPick = random.nextInt((neighbors.size() - 1)) + 1;
				int randomPick = (int) Math.floor(Math.random() * (neighbors.size() -1));
				int [] arrayNeighbors = neighbors.get(randomPick);
				move(x, y, arrayNeighbors[0],arrayNeighbors[1], myGrid[x][y].isFish(), true);
			}
		}
		else{ //For shark
			if(myGrid[x][y].getEnergy() == 0){//If shark has starved
				myGrid[x][y].makeOcean();
				myGrid[x][y].updateEnergy(myGrid[x][y].isShark(), true);
			}
			else{
				myGrid[x][y].updateEnergy(myGrid[x][y].isShark(), false);
				neighbors = getNeighbors(x, y, false);
				myGrid[x][y].updateTurn();
				if(!neighbors.isEmpty()){//If fish were found
					int randomPick = (int) Math.floor(Math.random() * (neighbors.size() -1));
					int [] arrayNeighbors = neighbors.get(randomPick); //Something is going on here
					move(x, y, arrayNeighbors[0],arrayNeighbors[1], myGrid[x][y].isFish(), true);
				}
				else{//No fish were found
					neighbors = getNeighbors(x, y, false);
					if(!neighbors.isEmpty()){
						int randomPick = (int) Math.floor(Math.random() * (neighbors.size() -1));
						int [] arrayNeighbors = neighbors.get(randomPick); //Something is going on here
						move(x, y, arrayNeighbors[0],arrayNeighbors[1], myGrid[x][y].isFish(), false);
					}
				}
			}
		}
		checkIfCanSpawn(x, y, myGrid[x][y].getTurnAlive(), myGrid[x][y].isFish());
	}
	
	public void checkIfCanSpawn(int x, int y, int turnsAlive, boolean fish){
		if((fish && turnsAlive >= fishSpawn) || (!fish && turnsAlive >= sharkSpawn)){//This is a magic value
			myGrid[x][y].resetTurn();
			List<int []> arrayEmptyNeighbors = new ArrayList<int[]>();
			arrayEmptyNeighbors = getNeighbors(x,y, true); 
			if(!arrayEmptyNeighbors.isEmpty()){
				int randomPick = (int) Math.floor(Math.random() * (arrayEmptyNeighbors.size() -1));
				int [] arrayNeighbors = arrayEmptyNeighbors.get(randomPick); 
				spawn(arrayNeighbors[0], arrayNeighbors[1], myGrid[x][y].isFish());
			}
		}
	}
	
	public void move(int xOld, int yOld, int xNew, int yNew, boolean fish, boolean eat){
		if(fish){	
			myGrid[xNew][yNew].makeFish(); 
			myGrid[xOld][yOld].makeOcean(); 
		}
		else{//Move shark
			myGrid[xNew][yNew].makeShark();
			myGrid[xOld][yOld].makeOcean();
			if(eat){//If the shark ate a fish
				myGrid[xNew][yNew].updateEnergy(myGrid[xNew][yNew].isShark(), true);
			}
		}
		return;
	}
	
	public void spawn(int x, int y, boolean fish){
		if (fish){
			myGrid[x][y].makeFish(); 
		}
		else{
			myGrid[x][y].makeShark(); 
		}
		return; 
	}
	
	public List<int[]> getNeighbors(int r, int c, boolean fish){//Needs to account for torus behavior + refactor both sharks and fish
		List<int[]> neighborList = new ArrayList<int[]>(); 
		for(int rChange = -1; rChange <= 1; rChange++){
			int rNew = r + rChange; 
			if(rNew != r){
				if(rNew < 0)	rNew = myGrid.length - 1;
				else if(rNew >= myGrid.length - 1)	rNew = 0; 
				for(int cChange = -1; cChange <= 1; cChange++){
					int cNew = c + cChange;
					if(cNew != c){
						if(cNew < 0)	cNew = myGrid.length - 1;
						else if(cNew >= myGrid.length - 1)	cNew = 0; 
						if(fish){
							if(myGrid[rNew][cNew].isOcean()){ //This portion must be refactored...
								int [] coordinates = new int[2];
								coordinates[0] = rNew; coordinates[1] = cNew; //Can i even do this 
								neighborList.add(coordinates);
							}
						}
						else{
							if(myGrid[rNew][cNew].isFish()){
								int[] coordinates = new int[2];
								coordinates[0] = rNew; coordinates[1] = cNew; 
								neighborList.add(coordinates);
							}
						}
					}
				}
			}
		}
		return neighborList; 
	}
	
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
