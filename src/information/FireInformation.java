package information;

import java.util.ArrayList;
import cellclasses.Cell;
import cellclasses.FireCell;
import filereadcheck.FileWriter;

public class FireInformation extends SimulationInformation {

	public FireInformation(Cell[][] grid) {
		super(grid);
	}

	public void initializeMaps() { 
		data.put("onfire", 0);
		data.put("burned", 0);
		data.put("nofire", 0);
		System.out.println(coors);
		coors.put("onfire", new ArrayList<int[]>());
		coors.put("burned", new ArrayList<int[]>());
		coors.put("nofire", new ArrayList<int[]>());
	}
	
	public void record(Cell cell, int[] point) { 
		FireCell cur = (FireCell) cell; 
		ArrayList<int[]> curList;
		if (cur.isFire()) { 
			curList = coors.get("onfire");
			curList.add(point);
			coors.put("onfire", curList);
		} else if (cur.isTree()) { 
			curList = coors.get("nofire");
			curList.add(point);
			coors.put("nofire", curList);
		} else { 
			curList = coors.get("burned"); 
			curList.add(point);
			coors.put("burned", curList);
		}
	}

	@Override
	public void writeToXML(FileWriter writer) {
		
	}

}
