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
	private boolean[][] sugarGrid; 
	private int lowerBoundAge = 60;
	private int fertileLimitCutoff = 20; 
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
				cell.makeSugar(sugarAmount, sugarGrowBackRate, sugarGrowBackInterval); 
			}
		}
		addSugar();
		addAgent();
	}
	
	public void initializeParameters(){
		vision = Integer.parseInt(reader.readProperty("vision"));
		System.out.println(vision);
		sugarMetabolism = Integer.parseInt(reader.readProperty("sugarMetabolism"));
		sugarAgent = Integer.parseInt(reader.readProperty("sugarAgent"));
		sugarAmount = Integer.parseInt(reader.readProperty("sugarAmount"));
		sugarGrowBackRate = Integer.parseInt(reader.readProperty("sugarGrowBackRate"));
		sugarGrowBackInterval = Integer.parseInt(reader.readProperty("sugarGrowBackInterval"));
	}
	
	public void addSugar(){ 
//		System.out.println("add sugar");
		for (int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid.length; j++){
				SugarScapeCell sugarCell = (SugarScapeCell) grid[i][j];
				sugarCell.makeSugar(sugarAmount, sugarGrowBackRate, sugarGrowBackInterval);
			}
		}
//		System.out.println("hey again");
	}
	
	public void addAgent(){
		int [] xAgent = reader.populateCoorArray(reader.readProperty("agentX"));
		int [] yAgent = reader.populateCoorArray(reader.readProperty("agentY")); 
		for (int i = 0; i < xAgent.length; i++){
			SugarScapeCell sugarCell = (SugarScapeCell) grid[xAgent[i]][yAgent[i]];
			int maxAge = (int) (Math.random() * 59) + 1 + lowerBoundAge; 
			sugarCell.makeAgent(vision, sugarAgent, sugarMetabolism, maxAge, maxAge = fertileLimitCutoff);
//			sugarGrid[xAgent[i]][yAgent[i]] = false;
		}
	}
}