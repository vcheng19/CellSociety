package ruleEnforcers;

import java.util.ArrayList;

import cellclasses.Cell;
import filereadcheck.FileReader;

public abstract class RuleEnforcer {
	public Cell[][] myGrid;
	public FileReader reader;
	public Cell[][] copyGrid;
	private int range;
	public RuleEnforcer(Cell[][] grid, FileReader fr) {
		myGrid = grid; 
		reader = fr;
		initializeParameters();
	}

	abstract void initializeParameters();

	public abstract void iterateGrid();

	//public abstract void updateState(Cell cell);

	public ArrayList<Cell> getNeighbors(Cell check, boolean wrap, int range){
		ArrayList<Cell> result = new ArrayList<Cell>();
		int r = check.getX();
		int c = check.getY();
		for(int rChange = -range;rChange <= range;rChange++){
			int rNew = r + rChange;
			for (int cChange = -range;cChange <= range;cChange++){
				int cNew = c + cChange;
				if( !(rChange == 0 && cChange == 0)){
					if(rNew >= 0 && rNew < myGrid.length && cNew >= 0 && cNew < myGrid.length){
						result.add(myGrid[rNew][cNew]);	
					}
					else if(wrap){
						rNew = wrapCor(rNew);
						cNew = wrapCor(cNew);
						result.add(myGrid[rNew][cNew]);	
					}
				}
			}
		}
		return result;
	}

	public int wrapCor(int cor) { 
		System.out.println(cor);
		System.out.println(myGrid.length);
		if(cor < 0) cor = Math.floorMod(cor, myGrid.length);
		else if(cor > myGrid.length - 1) cor = Math.floorMod(cor, myGrid.length);
		System.out.println("Wrapped: " + cor);
		return cor;
	}

	public ArrayList<Cell> getAdjNeighbors(Cell check, boolean wrap, int range){
		ArrayList<Cell> result = new ArrayList<Cell>();
		ArrayList<Cell> allNeighbors= getNeighbors(check, wrap, range);
		int myRow = check.getX();
		int myCol = check.getY();
		for(Cell x: allNeighbors){
			int row = x.getX();
			int col = x.getY();
			if( row == myRow || col == myCol){
				result.add(x);
			}
		}
		return result;
	}
	public void createCopyGrid(){
		for (int row = 0; row < myGrid.length; row++){
			for(int col = 0; col < myGrid.length; col++){
				copyGrid[row][col] = myGrid[row][col];
			}
		}
	}

}
