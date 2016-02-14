package ruleEnforcers;

import java.util.ArrayList;

import cellclasses.Cell;
import cellclasses.SlimeCell;
import filereadcheck.FileReader;

public class SlimeRuleEnforcer extends RuleEnforcer {
	private SlimeCell[][] myGrid; 
	private ArrayList<SlimeCell> mySlimes;
	private int sniffThreshhold;
	private int sniffAngle;
	private int wiggleAngle;
	private int wiggleBias;
	
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
		//sniffAngle = 2*x/45 + 1 to find the # of neighbors to keep, this is assuming the angle will be less than 180
		// do same for wiggleAngle
		// TODO Auto-generated method stub
		//find Parameters from xml file
		sniffThreshhold = Integer.parseInt(reader.readProperty("sniffThreshhold"));
		sniffAngle= (2*Integer.parseInt(reader.readProperty("sniffAngle"))/45 +1);
		wiggleAngle = 2*Integer.parseInt(reader.readProperty("wiggleAngle"))/45 + 1;
		wiggleBias = Integer.parseInt(reader.readProperty("wiggleBias"));
		
	}

	@Override
	public void iterateGrid() {
		// TODO Auto-generated method stub
		ArrayList<SlimeCell> temp = new ArrayList<SlimeCell>();
		for(int i = mySlimes.size()-1;i > 0; i--){
			SlimeCell cell = mySlimes.get(i);
			int patchValue = patchNum(cell);
			if(patchValue >= sniffThreshhold){
				ArrayList<SlimeCell> myNeighbors = myGetNeighbors(cell,false, wiggleAngle);
				if(myNeighbors.size() != 0){
					moveCell(chooseDestination(myNeighbors,cell), cell);
				}
			}
			else{
				ArrayList<SlimeCell> myNeighbors = myGetNeighbors(cell,false, wiggleAngle);
				if(myNeighbors.size() > 0){
					moveCell(chooseDestination(myNeighbors,cell), cell);
				}
			}
		}
		
//		for(SlimeCell slime: mySlimes){
//			ArrayList<SlimeCell> neighbors = myGetNeighbors(slime, false, );
//			
//		}
		
		
	}
	
	private void moveCell(SlimeCell randomCell, SlimeCell cell){
		randomCell.makeSlime();
		cell.makeEmpty();
		mySlimes.add(randomCell);
		mySlimes.remove(cell);
	}
	
	private SlimeCell chooseDestination(ArrayList<SlimeCell> posCells, Cell cell){
		//look at wiggleBias
//		for(SlimeCell x: posCells){
//			if(x.getX() == cell.getX() && wiggleBias == 0){
//				return x;
//			}
//			if(wiggleBias < 0 && x.getX() == cell.getX() -1 ){
//				return x;
//			}
//			if(wiggleBias > 0 && x.getX() == cell.getX() +1 ){
//				return x;
//			}
//		}
		int random = (int)(Math.random()*posCells.size());
		return posCells.get(random);
	}
	
	private ArrayList<SlimeCell> myGetNeighbors(SlimeCell cell, boolean onlyCountSlimes, int threshhold){
		ArrayList<Cell> allNeighbors = cell.getNeighbors();
		ArrayList<SlimeCell> allVisibleNeighbors = new ArrayList<SlimeCell>();
		ArrayList<SlimeCell> results = new ArrayList<SlimeCell>();
//		allVisibleNeighbors.add((SlimeCell)allNeighbors.get(1));
		for(int i = 0; i < threshhold;i++){
//			if(i != 1 && i != 6){
				allVisibleNeighbors.add((SlimeCell)allNeighbors.get(i));
//			}
		}
			
		for(SlimeCell slime: allVisibleNeighbors){
			if(onlyCountSlimes && slime.isSlime() || !onlyCountSlimes && !slime.isSlime()){
				results.add(slime);
			}
		}
		
		return results;
	}
	
	private int patchNum(SlimeCell cell){
		int numSlimeInPatch = 0;
		ArrayList<SlimeCell> possibleNeighbors = myGetNeighbors(cell, true, sniffAngle);
		for(SlimeCell x: possibleNeighbors){
			numSlimeInPatch = numSlimeInPatch + patchNum(x);
		}
		return numSlimeInPatch;
	}
	
	

}
