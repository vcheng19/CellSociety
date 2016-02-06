package cellsociety_team24;

import java.util.ArrayList;

public abstract class RuleEnforcer {
	public Cell[][] myGrid;
	public FileReader reader;
	
	public RuleEnforcer(Cell[][] grid, FileReader fr) {
		myGrid = grid; 
		reader = fr;
		initializeParameters();
	}
	
	abstract void initializeParameters();
	
	public void iterateGrid(){
		for(int x = 0; x < myGrid.length; x++){
			for(int y = 0; y < myGrid.length; y++){
				updateState(myGrid[x][y]); 
			}
		}
		return; 
	}
	
	public void updateState(Cell cell){
		getNeighbors(cell); 
		return; 
	}
	
	public ArrayList<Cell> getNeighbors(Cell check){
		ArrayList<Cell> result = new ArrayList<Cell>();
		int r = check.getX();
		int c = check.getY();
		for(int rChange = -1;rChange < 2;rChange++){
			if(r + rChange >= 0 && r + rChange < myGrid.length){//If r is within the boundaries of the grid
				for(int cChange = -1;cChange < 2;cChange++){
					if(c + cChange >= 0 && c + cChange < myGrid.length && !(rChange == 0 && cChange == 0)){
						result.add(myGrid[r+rChange][c+cChange]);
					}
				}
			}
		}
		return result;
	}
}
