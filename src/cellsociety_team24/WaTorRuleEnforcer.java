package cellsociety_team24;

public class WaTorRuleEnforcer extends RuleEnforcer{
	private WaTorCell[][] myGrid; 
	private WaTorCell[][] copyGrid;
	
	public WaTorRuleEnforcer(Cell[][] grid) {
		super(grid);
		myGrid = new WaTorCell[grid.length][grid.length];
		copyGrid = myGrid; 
	}

	public void iterateGrid(){
		
	}
}
