package cellsociety_team24;

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
		int row = cell.getX();
		int col = cell.getY(); 
		checkNeighbors(row, col); 
		return; 
	}
	
	public void checkNeighbors(int xPos, int yPos){//Will need to update this 
		return; 
	}
}
