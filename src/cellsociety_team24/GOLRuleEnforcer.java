package cellsociety_team24;

public class GOLRuleEnforcer extends RuleEnforcer {
	private GameOfLifeCell[][] myGrid; 
	private boolean[][] copyGrid; 
	private static int underPop; 
	private static int overPop; 
	private static int liveAgain; 
	
	public GOLRuleEnforcer(Cell[][]grid, FileReader reader){
		super(grid, reader);
		myGrid = new GameOfLifeCell[grid.length][grid.length];
		for (int i=0;i<grid.length;i++) { 
			for (int j=0;j<grid.length;j++) { 
				myGrid[i][j] = (GameOfLifeCell) grid[i][j];
			}
		}
		initializeParameters();
	}
	
	public void initializeParameters() { 
		underPop = Integer.parseInt(reader.readProperty("underpop"));
		overPop = Integer.parseInt(reader.readProperty("overpop"));
		liveAgain = Integer.parseInt(reader.readProperty("liveagain"));
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
				int myNeighbors = numNeighbors(x,y);
				if(copyCell && myNeighbors == liveAgain){
						actualCell.makeAlive();
				}
				else if (myNeighbors < underPop || myNeighbors > overPop) {
						actualCell.killCell();
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
