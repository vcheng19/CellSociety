package cellsociety_team24;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FireRuleEnforcer extends RuleEnforcer {
	private static FireCell[][] myGrid;
	private FireCell[][] copyGrid;
	private int myPercent;
	ArrayList<FireCell> fireCells;
	private final static int HUNDRED = 100;
	
	public FireRuleEnforcer(Cell[][] grid, FileReader fr) {
		super(grid, fr);
		myGrid = new FireCell[grid.length][grid.length];
		fireCells = new ArrayList<FireCell>();
	
		for (int i=0;i<grid.length;i++) { 
			for (int j=0;j<grid.length;j++) { 
				myGrid[i][j] = (FireCell) grid[i][j];
				if(myGrid[i][j].isFire()){
					fireCells.add(myGrid[i][j]);
				}
			}
		}
		
		copyGrid = new FireCell[myGrid.length][myGrid.length];
	}
	
	public void initializeParameters() { 
		myPercent = Integer.parseInt(reader.readProperty("percentage"));
	}
	
	public void iterateGrid(){
		createCopyGrid();
		Set<FireCell> cellsToFire = new HashSet<FireCell>();
		cellsToFire = catchOnFire(cellsToFire);
		
		for(FireCell s: cellsToFire){
			int rand = (int) (Math.random()*HUNDRED);
			if(rand < myPercent){
				s.makeFire();
				fireCells.add(s);
			}
		}
	}
	
	private Set<FireCell> catchOnFire(Set<FireCell> cellsToFire){
		for(FireCell fire: fireCells){
			ArrayList<FireCell> myNeighbors = getAdjNeighbors(fire);
			for(FireCell neighbor: myNeighbors){
				if(neighbor.isTree()){
					cellsToFire.add(neighbor);
				}
			}
			fire.makeEmpty();
		}
		fireCells.clear();
		return cellsToFire;
	}
	
	private void createCopyGrid(){
		for (int row = 0; row < myGrid.length; row++){
			for(int col = 0; col < myGrid.length; col++){
				copyGrid[row][col] = myGrid[row][col];
			}
		}
	}
	
	private ArrayList<FireCell> getAdjNeighbors(Cell check){
		ArrayList<FireCell> result = new ArrayList<FireCell>();
		ArrayList<Cell> allNeighbors= getNeighbors(check);
		int myRow = check.getX();
		int myCol = check.getY();
		for(Cell x: allNeighbors){
			int row = x.getX();
			int col = x.getY();
			if( row == myRow || col == myCol){
				FireCell adjCell = (FireCell)x;
				result.add(adjCell);
			}
		}
		return result;
	}
	

}
