package cellsociety_team24;

public abstract class RuleEnforcer {
	private Cell[][] myGrid; 
	public RuleEnforcer(Cell[][] grid) {
		myGrid = grid; 
		
	}
	
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
