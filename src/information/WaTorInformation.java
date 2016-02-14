package information;

import java.util.ArrayList;
import cellclasses.Cell;
import cellclasses.WaTorCell;

public class WaTorInformation extends SimulationInformation {

	public WaTorInformation(Cell[][] grid) {
		super(grid);
		sim_type = "WaTor";
	}

	public void record(Cell cell, int[] point) {
		WaTorCell cur = (WaTorCell) cell; 
		ArrayList<int[]> curList;
		if (cur.isFish()) { 
			curList = coors.get("fish");
			curList.add(point);
			coors.put("fish", curList);
		} else if (cur.isShark()) { 
			curList = coors.get("shark");
			curList.add(point);
			coors.put("shark", curList);
		} 
	}

	@Override
	public void initializeMaps() {
		data.put("fish", 0);
		data.put("shark", 0);
		coors.put("fish", new ArrayList<int[]>());
		coors.put("shark", new ArrayList<int[]>());
	}
}
