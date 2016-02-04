package cellsociety_team24;

public class GOLRuleEnforcer extends RuleEnforcer {
	private GameOfLifeCell[][] myGrid; 
	
	public GOLRuleEnforcer(Cell[][]grid){
		super(grid);
		myGrid = new GameOfLifeCell[grid.length][grid.length];
		for (int i=0;i<grid.length;i++) { 
			for (int j=0;j<grid.length;j++) { 
				myGrid[i][j] = (GameOfLifeCell) grid[i][j];
			}
		}
	}
	public void iterateGrid(){
		for(int r = 0; r < myGrid.length; r++){
			for(int c = 0; c < myGrid.length; c++){
				GameOfLifeCell curCell = myGrid[r][c];
				int x = curCell.getX(); 
				int y = curCell.getY(); 
				//If not (alive and have two or three living neighbors) 
				if(!(!curCell.isDead() && (numNeighbors(x, y) == 2 || numNeighbors(x, y) == 3))){
					//System.out.println(x + " " + y);
					curCell.killCell(); 
				}
				else if(curCell.isDead() && numNeighbors(x, y) == 3){
					curCell.makeAlive(); 
				}
			}
		}
	}
	
	public int numNeighbors(int r, int c){
		int numAliveCells = 0;
		for(int rChange = -1;rChange < 2;rChange++){
			if(r + rChange >= 0 && r + rChange < myGrid.length){//If r is within the boundaries of the grid
				for(int cChange = -1;cChange < 2;cChange++){
					if(c + cChange >= 0 && r + cChange < myGrid.length){
						if(!myGrid[r+rChange][c+cChange].isDead()) numAliveCells++;
					}
				}
			}	
		}
		return numAliveCells; 
	}
	

}
