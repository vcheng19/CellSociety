package gridinitializers;

import cellclasses.SugarScapeCell;
import filereadcheck.FileReader;
import javafx.scene.Group;

public class SugarScapeGridInitializer extends GridInitializer{
	private int vision;
	private int sugarMetabolism;
	private int sugarAgent; 
	private int sugarAmount;
	private int sugarGrowBackRate;
	private int sugarGrowBackInterval; 
	
	public SugarScapeGridInitializer(Group gr, FileReader fr){
		super(gr, fr); 
	}

	@Override
	public void makeGrid() {
		grid = new SugarScapeCell[DIMENSION][DIMENSION];
		initializeParameters(); 
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				SugarScapeCell cell = new SugarScapeCell(g, WORLD_SIZE/DIMENSION, i, j); 
				grid[i][j] = cell; 
			}
		}
	}
	
	public void initializeParameters(){
		vision = Integer.parseInt(reader.readProperty("vision"));
		sugarMetabolism = Integer.parseInt(reader.readProperty("sugarMetabolism"));
		sugarAgent = Integer.parseInt(reader.readProperty("sugarAgent"));
		sugarAmount = Integer.parseInt(reader.readProperty("sugarAmount"));
		sugarGrowBackRate = Integer.parseInt(reader.readProperty("sugarGrowBackRate"));
		sugarGrowBackInterval = Integer.parseInt(reader.readProperty("sugarGrowBackInterval"));
	}
	
	public void addSugar(){
		int [] xSugarStats = reader.populateCoorArray(reader.readProperty("sugarX"));
		int [] ySugarStats = reader.populateCoorArray(reader.readProperty("sugarY")); 
		for (int i = 0; i < xSugarStats.length; i++){
			SugarScapeCell sugarCell = (SugarScapeCell) grid[xSugarStats[i]][ySugarStats[i]];
			sugarCell.makeSugar(sugarAmount, sugarGrowBackRate, sugarGrowBackInterval);
		}
	}
	
	public void addAgent(){
		int [] xAgent = reader.populateCoorArray(reader.readProperty("agentX"));
		int [] yAgent = reader.populateCoorArray(reader.readProperty("agentY")); 
		for (int i = 0; i < xAgent.length; i++){
			SugarScapeCell sugarCell = (SugarScapeCell) grid[xAgent[i]][yAgent[i]];
			sugarCell.makeAgent(vision, sugarAgent, sugarMetabolism);
		}
	}
}