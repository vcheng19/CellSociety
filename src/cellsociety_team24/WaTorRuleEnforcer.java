package cellsociety_team24;

import java.util.ArrayList;
import java.util.List;

public class WaTorRuleEnforcer extends RuleEnforcer{
	private WaTorCell[][] myGrid; 
	private WaTorCell[][] copyGrid;
	
	public WaTorRuleEnforcer(Cell[][] grid) {
		super(grid);
		myGrid = new WaTorCell[grid.length][grid.length];
		copyGrid = myGrid; 
	}

	public void iterateGrid(){
		for(int r = 0; r < myGrid.length; r++){
			for(int c = 0; c < myGrid.length; c++){
				WaTorCell actualCell = myGrid[r][c];
				int x = actualCell.getX();
				int y = actualCell.getY(); 
				
			}
		}
	}
	
	public List<Integer[]> getNeighbors(int r, int c){
		List<int[]> neighborList = new ArrayList<int[]>();
		
		for(int rChange = -1;rChange <= 1;rChange++){
			if(r + rChange >= 0 && r + rChange < copyGrid.length){//If r is within the boundaries of the grid
				for(int cChange = -1;cChange < 2;cChange++){
					if(c + cChange >= 0 && c + cChange < copyGrid.length){
						if(!(rChange == 0 && cChange == 0)){
							
						}
					}
				}
			}	
		}
	}
	
	
}
