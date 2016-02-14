package cellsociety_team24;

import cellclasses.Cell;
import cellclasses.SegregationCell;
import ruleEnforcers.FireRuleEnforcer;
import ruleEnforcers.ForagingAntRuleEnforcer;
import ruleEnforcers.GOLRuleEnforcer;
import ruleEnforcers.RuleEnforcer;
import ruleEnforcers.SegregationRuleEnforcer;
import ruleEnforcers.WaTorRuleEnforcer;

public class GridToXML {
	RuleEnforcer infoSource;
	SegregationRuleEnforcer seg;
	FireRuleEnforcer fire;
	GOLRuleEnforcer gol; 
	WaTorRuleEnforcer wator; 
	ForagingAntRuleEnforcer ants;
	
	GridToXML(RuleEnforcer re) { 
		infoSource = re; 
	}

	public void matchSimType() { 
		
	}
	
}
