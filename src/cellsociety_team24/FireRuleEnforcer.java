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
	
	public FireRuleEnforcer(Cell[][] grid, int percent) {
		super(grid);
		myPercent = percent;
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
			ArrayList<FireCell> myNeighbors = getNeighbors(fire);
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
	
	private ArrayList<FireCell> getNeighbors(Cell check){
		ArrayList<FireCell> result = new ArrayList<FireCell>();
		int r = check.getX();
		int c = check.getY();
		int[] rowChanges = {-1, 0, 0, 1};
		int[] colChanges = {0, -1, 1, 0};
		for(int i = 0; i< rowChanges.length;i++){
			int rChange = rowChanges[i];
			int cChange = colChanges[i];
			if(r + rChange >= 0 && r + rChange < copyGrid.length){
					if(c + cChange >= 0 && c + cChange < copyGrid.length && !(rChange == 0 && cChange == 0)){
						result.add(copyGrid[r+rChange][c+cChange]);
					}
			}
		}
		return result;
	}
	

}
