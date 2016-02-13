package ruleEnforcers;

import java.util.ArrayList;

import cellclasses.Cell;
import cellclasses.SlimeCell;
import filereadcheck.FileReader;

public class SlimeRuleEnforcer extends RuleEnforcer {
	private SlimeCell[][] myGrid; 
	private ArrayList<SlimeCell> mySlimes;
	
	public SlimeRuleEnforcer(Cell[][] grid, FileReader fr) {
		super(grid, fr);
		mySlimes = new ArrayList<SlimeCell>();
		myGrid = new SlimeCell[grid.length][grid.length];
		for (int i=0;i<grid.length;i++) { 
			for (int j=0;j<grid.length;j++) { 
				myGrid[i][j] = (SlimeCell) grid[i][j];
				if(myGrid[i][j].isSlime()){
					mySlimes.add(myGrid[i][j]);
				}
			}
		}
	}

	@Override
	void initializeParameters() {
		// TODO Auto-generated method stub
		//find Parameters from xml file
		
	}

	@Override
	public void iterateGrid() {
		// TODO Auto-generated method stub
		
		
		
	}
	
	

}
