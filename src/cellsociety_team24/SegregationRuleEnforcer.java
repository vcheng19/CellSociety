package cellsociety_team24;

import java.util.ArrayList;

public class SegregationRuleEnforcer extends RuleEnforcer {
	SegregationCell[][] myGrid;
	private double[][] copyGrid; 
	private double myPercent;
	ArrayList<SegregationCell> emptyCells;
	
	public SegregationRuleEnforcer(Cell[][] grid, FileReader fr) {
		super(grid, fr);
		emptyCells = new ArrayList<SegregationCell>();
		myGrid = new SegregationCell[grid.length][grid.length];
		for (int i=0;i<grid.length;i++) { 
			for (int j=0;j<grid.length;j++) { 
				myGrid[i][j] = (SegregationCell) grid[i][j];
			}
		}
		
		for(int i = 0; i < myGrid.length; i++){
			for(int j = 0; j< myGrid[i].length; j++){
				if(myGrid[i][j].isEmpty()){
					emptyCells.add(myGrid[i][j]);
				}
			}
		}
	}
	
	public void initializeParameters() { 
		myPercent = Integer.parseInt(reader.readProperty("percentage"))/100.0;
	}
	
	public void iterateGrid(){
		createCopyGrid();
		for(int r = 0; r < myGrid.length; r++){
			for(int c = 0; c < myGrid.length; c++){
				double cellPercent = copyGrid[r][c];
				if(cellPercent < myPercent && !myGrid[r][c].isEmpty()){
					int newLocation = (int) (Math.random()*emptyCells.size());
					SegregationCell newCell = emptyCells.get(newLocation);
					if(myGrid[r][c].isRed()){
						newCell.makeRed();
					}
					else{
						newCell.makeBlue();
					}
					emptyCells.remove(newLocation);
					myGrid[r][c].makeEmpty();
					emptyCells.add(myGrid[r][c]);
				}
			}
		}
		
	}
	
	private void createCopyGrid(){
		copyGrid = new double[myGrid.length][myGrid.length];
		for (int row = 0; row < myGrid.length; row++){
			for(int col = 0; col < myGrid.length; col++){
				ArrayList<SegregationCell> myNeighbors = getNeighbors(myGrid[row][col]);
				int total = 0;
				int numColor = 0;
				if(!myGrid[row][col].isEmpty()){
					boolean myColor = myGrid[row][col].isRed();
					for (SegregationCell x: myNeighbors){
						if(!x.isEmpty()){
							boolean otherColor = x.isRed();
							if(myColor == otherColor){
								numColor++;
							}
							total++;
						}		
					}
					copyGrid[row][col] = (double) numColor/total;
				}
			}
		}
	}
	
	private ArrayList<SegregationCell> getNeighbors(Cell check){
		ArrayList<SegregationCell> result = new ArrayList<SegregationCell>();
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
