package cellsociety_team24;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WaTorRuleEnforcer extends RuleEnforcer{
	private WaTorCell[][] myGrid; 
//	private WaTorCell[][] copyGrid;
	private Random random; 
	public WaTorRuleEnforcer(Cell[][] grid) {
		super(grid);
		myGrid = new WaTorCell[grid.length][grid.length];
//		copyGrid = myGrid; 
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
			neighbors = getNeighborsOcean(x, y); 
			myGrid[x][y].updateTurn();
			if(!neighbors.isEmpty()){//Moving a fish
				int randomPick = random.nextInt((neighbors.size() - 1)) + 1;
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
				neighbors = getNeighborsFish(x, y);
				myGrid[x][y].updateTurn();
				if(!neighbors.isEmpty()){//If fish were found
					int randomPick = random.nextInt((neighbors.size() - 1)) + 1;
					int [] arrayNeighbors = neighbors.get(randomPick); //Something is going on here
					move(x, y, arrayNeighbors[0],arrayNeighbors[1], myGrid[x][y].isFish(), true);
				}
				else{//No fish were found
					neighbors = getNeighborsOcean(x, y);
					if(!neighbors.isEmpty()){
						int randomPick = random.nextInt((neighbors.size() - 1)) + 1;
						int [] arrayNeighbors = neighbors.get(randomPick); //Something is going on here
						move(x, y, arrayNeighbors[0],arrayNeighbors[1], myGrid[x][y].isFish(), false);
					}
				}
			}
		}
		checkIfCanSpawn(x, y, myGrid[x][y].getTurnAlive());
	}
	
	public void checkIfCanSpawn(int x, int y, int turnsAlive){
		if(turnsAlive >= 3){
			myGrid[x][y].resetTurn();
			List<int []> arrayEmptyNeighbors = new ArrayList<int[]>();
			arrayEmptyNeighbors = getNeighborsOcean(x,y); 
			if(!arrayEmptyNeighbors.isEmpty()){
				int randomPick = random.nextInt((arrayEmptyNeighbors.size() - 1)) + 1;
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
		if(fish){
			myGrid[x][y].makeFish(); 
		}
		else{
			myGrid[x][y].makeShark(); 
		}
		return; 
	}
	
	public List<int[]> getNeighborsFish(int r, int c){
		List<int[]> neighborList = new ArrayList<int[]>();
		for(int rChange = -1;rChange <= 1;rChange++){
			if(r + rChange >= 0 && r + rChange < myGrid.length){//If r is within the boundaries of the grid
				for(int cChange = -1;cChange < 2;cChange++){
					if(c + cChange >= 0 && c + cChange < myGrid.length){
						if(!(rChange == 0 && cChange == 0)){
							if(myGrid[r+rChange][c+cChange].isFish()){
								int[] coordinates = new int[2]; 
								coordinates[0] = r + rChange;
								coordinates[1] = c +cChange; 
								neighborList.add(coordinates);
							}
						}
					}
				}
			}	
		}
	return neighborList; 	
	}
	
	public List<int[]> getNeighborsOcean(int r, int c){
		List<int[]> neighborList = new ArrayList<int[]>();
		for(int rChange = -1;rChange <= 1;rChange++){
			if(r + rChange >= 0 && r + rChange < myGrid.length){//If r is within the boundaries of the grid
				for(int cChange = -1;cChange < 2;cChange++){
					if(c + cChange >= 0 && c + cChange < myGrid.length){
						if(!(rChange == 0 && cChange == 0)){
							if(myGrid[r+rChange][c+cChange].isOcean()){
								int[] coordinates = new int[2]; 
								coordinates[0] = r + rChange;
								coordinates[1] = c +cChange; 
								neighborList.add(coordinates);
							}
						}
					}
				}
			}	
		}
	return neighborList; 	
	}
	
	public List<int[]> getNeighbors(int r, int c, boolean fish){//Needs to account for torus behavior + refactor both sharks and fish
		List<int[]> neighborList = new ArrayList<int[]>();
		for(int rChange = -1; rChange <= 1; rChange++){
			if(r + rChange >= 0 && r + rChange < myGrid.length){//If r is within boundaries
				for(int cChange = -1; cChange < 2; cChange++){
					if(c+cChange >= 0 && c + cChange < myGrid.length){//If c is within boundaries
						if(!(rChange == 0 && cChange == 0)){
							if(fish){ //If fish
								if(myGrid[r+rChange][c+cChange].isOcean()){
									int[] coordinates = new int[2]; 
									coordinates[0] = r + rChange;
									coordinates[1] = c + cChange; 
									neighborList.add(coordinates);
								}
							}
							else{ //If shark
								if(myGrid[r+rChange][c+cChange].isFish()){
									int[] coordinates = new int[2]; 
									coordinates[0] = r + rChange;
									coordinates[1] = c +cChange; 
									neighborList.add(coordinates);
								}
							}
						}
					}
					else{//c is not within boundaries
						if(c+cChange < 0){
							c = myGrid.length - 1; 
						}
						else if(c+cChange > myGrid.length - 1){
							c = 0; 
						}
						if(fish){ //If fish
							if(myGrid[r][c].isOcean()){
								int[] coordinates = new int[2]; 
								coordinates[0] = r + rChange;
								coordinates[1] = c +cChange; 
								neighborList.add(coordinates);
							}
						}
						else{ //If shark
							if(myGrid[r][c].isFish()){
								int[] coordinates = new int[2]; 
								coordinates[0] = r + rChange;
								coordinates[1] = c +cChange; 
								neighborList.add(coordinates);
							}
						}
					}
				}
			}
			else{//r is not within boundaries NEED TO CHANGE THIS ASAP
				if(r + rChange < 0){
					r = myGrid.length - 1;
				}
				else if(r + rChange > myGrid.length - 1){
					r = 0; 
				}
			}
		}
	return neighborList; 
	}
	
	
}
