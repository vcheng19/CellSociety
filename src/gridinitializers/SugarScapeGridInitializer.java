package gridinitializers;

import cellclasses.*;
import cellsociety_team24.Grid;
import filereadcheck.FileReader;
import javafx.scene.Group;

public class SugarScapeGridInitializer extends GridInitializer{
	private int vision;
	private int sugarMetabolism;
	private int sugarAgent; 
	private int sugarAmount;
	private int sugarGrowBackRate;
	private int sugarGrowBackInterval; 
	private int lowerBoundAge = 60;
	private int fertileLimitCutoff = 20; 
	private final String agentXTag = "agentX";
	private final String agentYTag = "agentY";
	
	public SugarScapeGridInitializer(Grid thisGrid, Group gr, FileReader fr, boolean wrap){
		super(thisGrid, gr, fr, wrap); 
	}

	@Override
	public void makeGrid() {
		Cell[][]grid = new SugarScapeCell[getDimension()][getDimension()];
		initializeParameters(); 
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				SugarScapeCell cell = new SugarScapeCell(i, j); 
				grid[i][j] = cell; 
			}
		}
		getThisGrid().setValues(grid, getWorldSize()/getDimension(), getGroup(), true);
		getThisGrid().createCells(getWrap(), vision);
		addAttributes(grid);
		setGrid(grid);
	}
	
	public void initializeParameters(){
		FileReader reader = getReader();
		vision = Integer.parseInt(reader.readProperty("vision"));
		sugarMetabolism = Integer.parseInt(reader.readProperty("sugarMetabolism"));
		sugarAgent = Integer.parseInt(reader.readProperty("sugarAgent"));
		sugarAmount = Integer.parseInt(reader.readProperty("sugarAmount"));
		sugarGrowBackRate = Integer.parseInt(reader.readProperty("sugarGrowBackRate"));
		sugarGrowBackInterval = Integer.parseInt(reader.readProperty("sugarGrowBackInterval"));
	}
	
	public void addAttributes(Cell[][]grid){
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid.length; j++){
				SugarScapeCell cell = (SugarScapeCell) grid[i][j];
				cell.makeSugar(sugarAmount, sugarGrowBackRate, sugarGrowBackInterval); 
				cell.makeCircle(getGroup(), getWorldSize()/getDimension(), i, j);
				if(doConfigCell(agentXTag, agentYTag, i, j)){
					int maxAge = (int) (Math.random() * 59) + 1 + lowerBoundAge; 
					cell.makeAgent(vision, sugarAgent, sugarMetabolism, maxAge, maxAge = fertileLimitCutoff);
				}
			}
		}
	}
}