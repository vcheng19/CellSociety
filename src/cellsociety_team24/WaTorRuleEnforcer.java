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

//	public void iterateGrid(){
//		for(int r = 0; r < myGrid.length; r++){
//			for(int c = 0; c < myGrid.length; c++){
//				WaTorCell actualCell = myGrid[r][c];
//				int x = actualCell.getX();
//				int y = actualCell.getY(); 
//				
//				if(myGrid[x][y].isFish()){
//					
//				}
//			}
//		}
//	}
	
	public void doFishAction(int x, int y){
		List<int[]> neighbors = new ArrayList<int[]>();
		neighbors = getNeighborsOcean(x, y); 
		myGrid[x][y].updateTurn();
		if(!neighbors.isEmpty()){
			int randomPick = random.nextInt((neighbors.size() - 1) + 1);
			int [] arrayNeighbors = neighbors.get(randomPick);
			moveFish(x, y, arrayNeighbors[0],arrayNeighbors[1]);
		}
		if(myGrid[x][y].getTurnAlive() >= 3){
			myGrid[x][y].resetTurn(); 
			List<int []> arrayEmptyNeighbors = new ArrayList<int[]>();
			arrayEmptyNeighbors = getNeighborsOcean(x,y); 
			if(!arrayEmptyNeighbors.isEmpty()){
				int randomPick = random.nextInt((neighbors.size() - 1) + 1);
				int [] arrayNeighbors = neighbors.get(randomPick); 
				spawnFish(arrayNeighbors[0], arrayNeighbors[1]);
			}
		}
	}
	
	public void moveFish(int xOld, int yOld, int xNew, int yNew){
		myGrid[xNew][yNew].makeFish(); 
		myGrid[xOld][yOld].makeOcean(); 
		return;
	}
	
	public void spawnFish(int x, int y){
		myGrid[x][y].makeFish(); 
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
}
