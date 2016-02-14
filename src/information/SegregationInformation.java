package information;

import java.util.ArrayList;

import cellclasses.Cell;
import cellclasses.SegregationCell;

public class SegregationInformation extends SimulationInformation {

	public SegregationInformation(Cell[][] grid) {
		super(grid);
		sim_type = "Segregation";
	}

	public void record(Cell cell, int[] point) {
		SegregationCell cur = (SegregationCell) cell;
		ArrayList<int[]> curList;
		if (cur.isRed()) { 
			curList = coors.get("red");
			curList.add(point);
			coors.put("red", curList);
		} else if (cur.isBlue()) { 
			curList = coors.get("blue");
			curList.add(point);
			coors.put("blue", curList);
		} else { 
			curList = coors.get("free space"); 
			curList.add(point);
			coors.put("free space", curList);
		}

	}

	public void initializeMaps() {
		data.put("type 1", 0);
		data.put("type 2", 0);
		data.put("free space", 0);
		coors.put("red", new ArrayList<int[]>());
		coors.put("blue", new ArrayList<int[]>());
		coors.put("empty", new ArrayList<int[]>());
	}

}
