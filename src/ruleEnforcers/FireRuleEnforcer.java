package ruleEnforcers;

import java.util.*;
import java.util.HashSet;
import java.util.Set;

import cellclasses.Cell;
import cellclasses.FireCell;
import filereadcheck.FileReader;

public class FireRuleEnforcer extends RuleEnforcer {
	private static FireCell[][] myGrid;
	private int firePercent;
	private ArrayList<FireCell> fireCells;
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
	}
	
	public void initializeParameters() { 
		firePercent = Integer.parseInt(getReader().readProperty("percentage"));
	}
	
	public void iterateGrid(){
		//Assumed that every spot has an equal chance of catching on fire regardless of the number of burning adjacent cells 
		Set<FireCell> cellsToFire = new HashSet<FireCell>(); //Cares about unique neighbors
		cellsToFire = catchOnFire(cellsToFire);
		for(FireCell s: cellsToFire){
			int rand = (int) (Math.random()*HUNDRED);
			if(rand < firePercent){
				s.makeFire();
				fireCells.add(s);
			}
		}
	}
	
	private Set<FireCell> catchOnFire(Set<FireCell> cellsToFire){
		for(FireCell fire: fireCells){
			List<Cell> myNeighbors = fire.getNeighbors();
			for(Cell x: myNeighbors){
				FireCell neighbor = (FireCell) x;
				if(neighbor.isTree()){
					cellsToFire.add(neighbor);
				}
			}
			fire.makeEmpty();
		}
		fireCells.clear();
		return cellsToFire;
	}
}
