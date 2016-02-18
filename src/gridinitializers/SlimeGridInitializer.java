package gridinitializers;

import cellclasses.*;
import cellsociety_team24.Grid;
import cellsociety_team24.HexagonGrid;
import filereadcheck.FileReader;
import javafx.scene.Group;
import ruleEnforcers.SlimeRuleEnforcer;

public class SlimeGridInitializer extends GridInitializer {
	SlimeRuleEnforcer myRuleEnforcer;
	
	public SlimeGridInitializer(Grid thisGrid,Group gr, FileReader fr) {
		super(thisGrid, gr, fr);
		myRuleEnforcer = new SlimeRuleEnforcer(getGrid(), fr);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void makeGrid() {
		Cell[][] grid = new SlimeCell[getDimension()][getDimension()];
		for (int i=0;i<grid.length;i++) { 
			for (int j=0;j<grid[0].length;j++) { 
				//GameOfLifeCell cell = new GameOfLifeCell(g, WORLD_SIZE/DIMENSION, i, j);
				SlimeCell cell = new SlimeCell(i, j);
				grid[i][j] = cell;
			}
		}
		getThisGrid().setValues(grid, getWorldSize()/getDimension(), getGroup());
		//System.out.println(WORLD_SIZE/DIMENSION);
		getThisGrid().createCells(getWrap(), 1);
		addAttributes(grid);
		setGrid(grid);
	}

	@Override
	public void addAttributes(Cell[][] grid) {
		// TODO Auto-generated method stub
		int numberSlime = Integer.parseInt(getReader().readProperty("numSlimeCells"));
		int count = 0;
		while(count < numberSlime){
			int x = (int) Math.floor(Math.random() * grid.length);
			int y = (int) Math.floor(Math.random() * grid.length);
			SlimeCell cell = (SlimeCell) grid[x][y];
			if(!cell.isSlime()){
				count++;
				cell.makeSlime();
			}
		}
	}

}
