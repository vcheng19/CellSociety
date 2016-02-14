package information;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cellclasses.Cell;
import filereadcheck.FileWriter;

public abstract class SimulationInformation {
	Cell[][] myGrid;
	Map<String, Integer> data; 
	Map<String, ArrayList<int[]>> coors; 
	String sim_type;
			
	SimulationInformation (Cell[][] grid) { 
		myGrid = grid;
		data = new HashMap<String, Integer>();
		coors = new HashMap<String, ArrayList<int[]>>();
	}
	
	public void populateData() {
		initializeMaps();
		recordCoors();
		generateCountOfEach(); 
	}
	
	public void recordCoors() {
		for (int i=0;i<myGrid.length;i++) { 
			for (int j=0;j<myGrid[0].length;j++) { 
				Cell cell = myGrid[i][j];
				int[] point = new int[2];
				point[0] = i; point[1] = j;
				record(cell, point);
			}
		}
	}
	
	public void generateCountOfEach() {
		for (String key: coors.keySet()) { 
			data.put(key, coors.get(key).size());
		}
	}
	
	public Map<String, Integer> getCountMap() {
		return data;
	}
	
	public Map<String, ArrayList<int[]>> getCoorsMap() {
		return coors;
	}
	
	public abstract void record(Cell cell, int[] point);
	
	public abstract void initializeMaps(); 
	
	public void writeToXML(FileWriter writer) {
		for (String key: coors.keySet()) { 
			StringBuffer genx = new StringBuffer(); 
			StringBuffer geny = new StringBuffer();
			ArrayList<int[]> goods = coors.get(key); 
			int count = 0;
			for (int i=0; i< goods.size(); i++) { 
				int[] point = goods.get(i);
				genx.append(point[0] + " ");
				geny.append(point[1] + " ");
			}
			System.out.println(key + " " + count);
			writer.writeElementToFile(key+"x", genx.toString());
			writer.writeElementToFile(key+"y", geny.toString());
		}
		writer.writeDocToXML(sim_type);
	}
}
