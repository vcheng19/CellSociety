package information;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cellclasses.Cell;
import ruleEnforcers.RuleEnforcer;

public abstract class SimulationInformation {
	RuleEnforcer source; 
	Cell[][] myGrid;
	Map<String, Integer> data; 
	Map<String, List<int[]>> coors; 
			
	SimulationInformation (RuleEnforcer re) { 
		source = re; 
		myGrid = re.getGrid();
		data = new HashMap<String, Integer>();
	}
	
	public abstract void generateCountOfEach(); 
	
	public abstract void recordCoors(); 

}
