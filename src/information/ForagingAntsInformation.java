package information;

import java.util.ArrayList;

import cellclasses.Cell;
import cellclasses.ForagingAntCell;

public class ForagingAntsInformation extends SimulationInformation {

	public ForagingAntsInformation(Cell[][] grid) {
		super(grid);
		sim_type = "Foraging ants";
	}

	public void record(Cell cell, int[] point) {
		ForagingAntCell cur = (ForagingAntCell) cell;
		ArrayList<int[]> curList;
		if (cur.isType("ant")) { 
			curList = coors.get("ants");
			curList.add(point);
			coors.put("ants", curList);
		} else if (cur.isType("nest") && (cur.getAnts() > 0)) { 
			data.put("ants", data.get("ants") + cur.getAnts());
		} 
	}

	public void initializeMaps() {
		data.put("ants", 0);
		coors.put("ants", new ArrayList<int[]>());
	}
}
