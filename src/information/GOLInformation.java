package information;

import java.util.ArrayList;

import cellclasses.Cell;
import cellclasses.GameOfLifeCell;

public class GOLInformation extends SimulationInformation {

	public GOLInformation(Cell[][] grid) {
		super(grid);
		sim_type = "Game of life";
	}

	public void initializeMaps() { 
		data.put("alive", 0);
		data.put("dead", 0);
		coors.put("alive", new ArrayList<int[]>());
		coors.put("dead", new ArrayList<int[]>());
	}

	public void record(Cell cell, int[] point) {
		GameOfLifeCell cur = (GameOfLifeCell) cell; 
		if (!cur.isDead()) { 
			ArrayList<int[]> curList = coors.get("alive");
			curList.add(point);
			coors.put("alive", curList);
		} else { 
			ArrayList<int[]> curList = coors.get("dead");
			curList.add(point);
			coors.put("alive", curList);
		}
	}
}
