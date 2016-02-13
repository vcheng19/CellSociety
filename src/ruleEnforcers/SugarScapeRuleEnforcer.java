package ruleEnforcers;

import java.util.*;

import cellclasses.*;
import filereadcheck.FileReader;


public class SugarScapeRuleEnforcer extends RuleEnforcer{
	private SugarScapeCell[][] myGrid; 
	private int vision;
	private int sugarMetabolism;
	private int sugarAgent; 
	private int sugarAmount;
	private int sugarGrowBackRate;
	private int sugarGrowBackInterval; 
	
	public SugarScapeRuleEnforcer(Cell[][] grid, FileReader reader){
		super(grid, reader);
		myGrid = new SugarScapeCell[grid.length][grid.length];
		for (int i = 0;i < grid.length;i++){
			for(int j = 0; j < grid.length; j++){
				myGrid[i][j] = (SugarScapeCell) grid[i][j];
			}
		}
	}

	@Override
	void initializeParameters() {
		// TODO Auto-generated method stub
		vision = Integer.parseInt(reader.readProperty("vision"));
		sugarMetabolism = Integer.parseInt(reader.readProperty("sugarMetabolism"));
		sugarAgent = Integer.parseInt(reader.readProperty("sugarAgent"));
		sugarAmount = Integer.parseInt(reader.readProperty("sugarAmount"));
		sugarGrowBackRate = Integer.parseInt(reader.readProperty("sugarGrowBackRate"));
		sugarGrowBackInterval = Integer.parseInt(reader.readProperty("sugarGrowBackInterval"));

	}

	@Override
	public void iterateGrid() {
		// TODO Auto-generated method stub
		for(int r = 0; r < myGrid.length; r++){
			for(int c = 0; c < myGrid.length; c++){
				SugarScapeCell curCell = myGrid[r][c];
				if(!curCell.didMove()){
					if(curCell.isAgent()){
						doAgentAction(curCell);
					}
					else{
						doSugarAction(curCell);
					}
				}
			}
		}
		resetMovedGrid();
	}
	
	public void resetMovedGrid() { 
		for (int r=0;r<myGrid.length;r++) { 
			for (int c=0;c<myGrid.length;c++) { 
				myGrid[r][c].setMoved(false);
			}
		}
	}
	
	public void doSugarAction(SugarScapeCell sugar){
		sugar.updateSugarTick();
		if(sugar.getSugarTick() == sugarGrowBackInterval){
			sugar.updateSugarAmount(true); 
		}
	}
	
	public void doAgentAction(SugarScapeCell agent){
		if(!agent.didMove()){
			agent.updateAge();
			if(agent.getAge() == agent.getMaxAge() || agent.getSugarAgent() > agent.getSugarMetabolism()){
				agent.killAgent(agent); 
				return;
			}
			List<SugarScapeCell> sugarNeighbors = getNeighbors(agent, true, false);
			if(sugarNeighbors.size() != 0){
				SugarScapeCell swap = chooseNeighbor(sugarNeighbors);
				agent.moveAgent(agent, swap);
			}
			List<SugarScapeCell> agentNeighbors = getNeighbors(agent, true, true);
			if(agentNeighbors.size() != 0){
				reproduceAgent(agent, chooseNeighbor(agentNeighbors));
			}
		}
	}
	
	public List<SugarScapeCell> getNeighbors(Cell agent, boolean wrap, boolean repro){
		List<SugarScapeCell> neighborList = new ArrayList<SugarScapeCell>();
		SugarScapeCell curCell = (SugarScapeCell) agent; 
		for(Cell cell: getAdjNeighbors(agent, true, vision)){
			SugarScapeCell checkedCell = (SugarScapeCell) cell;
			if(!checkedCell.isOccupied() && !repro){
				neighborList.add(checkedCell); 
			}
			else if((checkedCell.getGender() == !curCell.getGender()) && checkedCell.getAge() <= checkedCell.getFertileLimit()){
				neighborList.add(checkedCell);
			}
			
		}
		return neighborList;
	}
	
	public void reproduceAgent(SugarScapeCell mom, SugarScapeCell dad){
		int babyVision = (mom.getVision() + dad.getVision()) / 2; 
		int babySugarAgent = (mom.getSugarAgent() / 2) + (dad.getSugarAgent() / 2); 
		mom.setSugar(mom.getSugarAgent() / 2);
		dad.setSugar(dad.getSugarAgent() / 2);
		int babySugarMetabolism = (mom.getSugarMetabolism() + dad.getSugarMetabolism()) / 2;
		int babyMaxAge = (mom.getMaxAge() + dad.getMaxAge()) / 2;
		int babyFertileLimit = (mom.getFertileLimit() + dad.getFertileLimit()) / 2;
		List<SugarScapeCell> momNeighbors = getNeighbors(mom, true, true);
		List<SugarScapeCell> dadNeighbors = getNeighbors(dad, true, true);
		List<SugarScapeCell> allNeighbors = new ArrayList<SugarScapeCell>(momNeighbors);
		allNeighbors.addAll(dadNeighbors); 
		List<SugarScapeCell> emptyNeighbors = new ArrayList<SugarScapeCell>(); 
		for(SugarScapeCell cell: allNeighbors){
			if(!cell.isOccupied()) emptyNeighbors.add(cell);
		}
		SugarScapeCell birth = chooseNeighbor(emptyNeighbors);
		birth.makeAgent(babyVision, babySugarAgent, babySugarMetabolism, babyMaxAge, babyFertileLimit);
		birth.setMoved(true);
	}
	
	public SugarScapeCell chooseNeighbor(List<SugarScapeCell> neighbors){
		int randomPick = (int) (Math.floor(Math.random() * (neighbors.size() - 1)));
		SugarScapeCell ne = neighbors.get(randomPick); 
		return ne;
	}
	
	
}
