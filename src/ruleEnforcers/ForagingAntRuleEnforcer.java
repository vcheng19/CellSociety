package ruleEnforcers;
import java.util.List;

import cellclasses.Cell;
import cellclasses.ForagingAntCell;
import filereadcheck.FileReader;

public class ForagingAntRuleEnforcer extends RuleEnforcer{
	private static ForagingAntCell[][] myGrid;
	ForagingAntCell home; 
	ForagingAntCell food; 
	private double SELECT_K; 
	private double SELECT_N;
	private int BIRTHRATE;
	private int MAX_PH;
	private final int ANT_LIMIT = 2; 
	private boolean wrap = false;
	
	public ForagingAntRuleEnforcer(Cell[][] grid, FileReader fr) {
		super(grid, fr);
		myGrid = new ForagingAntCell[grid.length][grid.length];
		for (int i=0;i<grid.length;i++) { 
			for (int j=0;j<grid.length;j++) { 
				myGrid[i][j] = (ForagingAntCell) grid[i][j];
				if (myGrid[i][j].isType("nest")) { 
					home = myGrid[i][j];
				} 
				if (myGrid[i][j].isType("food")) { 
					food = myGrid[i][j];
				}
			}
		}
		copyGrid = new ForagingAntCell[myGrid.length][myGrid.length];
	}

	void initializeParameters() {
		SELECT_K = Integer.parseInt(reader.readProperty("selectk")) / 1000; 
		SELECT_N = Integer.parseInt(reader.readProperty("selectn"));
		BIRTHRATE = Integer.parseInt(reader.readProperty("birthrate"));
		MAX_PH = Integer.parseInt(reader.readProperty("birthrate"));
	}
	
	public void iterateGrid(){
		for(int r = 0; r < myGrid.length; r++){
			for(int c = 0; c < myGrid.length; c++){
				ForagingAntCell curCell = myGrid[r][c];
				if (curCell.isType("ant")) { 
					determineMove(curCell);
				}
				else if (curCell.isType("nest") && curCell.getAnts() >= ANT_LIMIT) { 
					determineMove(curCell);
				}
			}
		}
		resetMovedGrid();
	}
	
	public void determineMove(ForagingAntCell curCell) {
		ForagingAntCell moveToCell = chooseAntsNeighbor(curCell);
		if ((!moveToCell.equals(home)) && (!moveToCell.equals(home))) {
			moveAnt(curCell, moveToCell);
		}
		//else if() { 
		//	
		//}
		else {
			moveToCell.addAnt();
			curCell.makeFloor();
			curCell.setMoved(true);
			curCell.setHasEaten(true);
		}
	}
	
	public void moveAnt(ForagingAntCell curCell, ForagingAntCell moveToCell) { 
		moveToCell.makeAnt(); 
		curCell.makeFloor();
		curCell.setMoved(true);
	}
	
	public ForagingAntCell chooseAntsNeighbor (ForagingAntCell antCell) { 
		List<Cell> allNeighbors = getAdjNeighbors(antCell, wrap); 
		ForagingAntCell nextPos = antCell;
		double max_probability = 0; 
		for (Cell cell: allNeighbors) { 
			ForagingAntCell neighbor = (ForagingAntCell) cell;
			double foodProbability = Math.pow((neighbor.getFoodPh() + SELECT_K), SELECT_N);
			double homeProbability = Math.pow((neighbor.getHomePh() + SELECT_K), SELECT_N);
			if (antCell.hasFood() && foodProbability > max_probability) { 
				nextPos = neighbor; 
				max_probability = foodProbability; 
			} 
			else if (!antCell.hasFood() && homeProbability > max_probability) { 
				nextPos = neighbor; 
				max_probability = homeProbability;
			}
		}
		return nextPos;
	}
	// implement probability thing 
	
	// make the ants be born at every step - where tho? 
}
