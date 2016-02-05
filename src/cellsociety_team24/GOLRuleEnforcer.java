package cellsociety_team24;

public class GOLRuleEnforcer extends RuleEnforcer {
	private GameOfLifeCell[][] myGrid; 
	private boolean[][] copyGrid; 
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
		copyGrid = new boolean[myGrid.length][myGrid.length];
		for (int row = 0; row < myGrid.length; row++){
			for(int col = 0; col < myGrid.length; col++){
				copyGrid[row][col] = myGrid[row][col].isDead();
			}
		}
		
		for(int r = 0; r < copyGrid.length; r++){
			for(int c = 0; c < copyGrid.length; c++){
				boolean copyCell = copyGrid[r][c];
				GameOfLifeCell actualCell = myGrid[r][c];
				int x = actualCell.getX(); 
				int y = actualCell.getY(); 
				//If not (alive and have two or three living neighbors) 
				int myNeighbors = numNeighbors(x,y);
				if(copyCell){
					if(myNeighbors == 3){//Rule 4
						actualCell.makeAlive();
					}
				}
				else{
					if(!(myNeighbors == 2 || myNeighbors == 3)){//1,2,3
						actualCell.killCell();
					}
				}
			}
		}
	}
	
	public int numNeighbors(int r, int c){
		int numAliveCells = 0;
		for(int rChange = -1;rChange < 2;rChange++){
			if(r + rChange >= 0 && r + rChange < copyGrid.length){//If r is within the boundaries of the grid
				for(int cChange = -1;cChange < 2;cChange++){
					if(c + cChange >= 0 && c + cChange < copyGrid.length){
						if(!copyGrid[r+rChange][c+cChange] && !(rChange == 0 && cChange == 0)){
							numAliveCells++;
						}
					}
				}
			}	
		}
		return numAliveCells; 
	}
	

}
