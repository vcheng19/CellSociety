package cellsociety_team24;

import java.util.ArrayList;
import java.util.List;

public class WaTorRuleEnforcer extends RuleEnforcer{
	private WaTorCell[][] myGrid;
	private static int fishSpawn; 
	private static int sharkSpawn;
	private int sharkEnergy;
	private int fishEnergy;


	public WaTorRuleEnforcer(Cell[][] grid, FileReader reader) {
		super(grid, reader);
		myGrid = new WaTorCell[grid.length][grid.length];
		for (int i=0;i<grid.length;i++) { 
			for (int j=0;j<grid.length;j++) { 
				myGrid[i][j] = (WaTorCell) grid[i][j];
			}
		}
	}

	public void initializeParameters() { 
		fishSpawn = Integer.parseInt(reader.readProperty("fishspawn"));
		sharkSpawn = Integer.parseInt(reader.readProperty("sharkspawn"));
		sharkEnergy = Integer.parseInt(reader.readProperty("sharkenergy"));
		fishEnergy = Integer.parseInt(reader.readProperty("fishenergy"));
	}

	public void iterateGrid(){
		for(int r = 0; r < myGrid.length; r++){
			for(int c = 0; c < myGrid.length; c++){
				WaTorCell curCell = myGrid[r][c];
				if (!curCell.getMoved()) {
					if(curCell.isFish()) {
						doFishAction(curCell);	
					} else if(curCell.isShark()){
						doSharkAction(curCell);
					}
				}
			}
		}
		resetMovedGrid();
	}

	public void resetMovedGrid() { 
		for (int r=0;r<myGrid.length;r++) { 
			for (int c=0;c<myGrid.length;c++) { 
				myGrid[r][c].setMoved(false);
			}
		}
	}

	public void doFishAction(WaTorCell fish) { 
		fish.updateTurn();
		List<WaTorCell> oceanNear = getNeighbors(fish, false);
		checkIfCanSpawn(fish);
		if(!oceanNear.isEmpty()){
			moveFish(fish, chooseNeighbor(oceanNear));
		}
	}

	public void doSharkAction(WaTorCell shark) { 
		if(shark.getEnergy() == 0){
			shark.makeOcean();
		}	
		else{ 
			checkIfCanSpawn(shark);
			shark.updateTurn();
			List<WaTorCell> fishNear = getNeighbors(shark, true);
			List<WaTorCell> ocean = getNeighbors(shark, false);
			if(!fishNear.isEmpty()){
				WaTorCell fish = chooseNeighbor(fishNear);
				moveShark(shark, fish, true);
			}
			else if(!ocean.isEmpty()){
				moveShark(shark, chooseNeighbor(ocean), false);
			}
			else{
				shark.setEnergy(shark.getEnergy() - 1);
			}
		}

	}

	public WaTorCell chooseNeighbor(List<WaTorCell> neighbors) { 
		int randomPick = (int) (Math.floor(Math.random() * (neighbors.size() -1)));
		WaTorCell ne = neighbors.get(randomPick); 
		return ne;
	}

	public void spawn(WaTorCell ocean, boolean fish){
		if (fish){
			ocean.makeFish(fishEnergy, 0); 
		}
		else{
			ocean.makeShark(sharkEnergy, 0); 
		}
		return; 
	}

	public void checkIfCanSpawn(WaTorCell curCell){
		if ((curCell.isFish() && curCell.getTurnsAlive() >= fishSpawn) ||
				(curCell.isShark() && curCell.getTurnsAlive() >= sharkSpawn)) { 
			List<WaTorCell> oceanSpots = getNeighbors(curCell, false);
			if (!oceanSpots.isEmpty()) { 
				spawn(chooseNeighbor(oceanSpots), curCell.isFish());
				curCell.resetTurns();
			}
		}
	}

	public void moveFish(WaTorCell fish, WaTorCell ocean) {
		fish.makeOcean();
		ocean.makeFish(fish.getEnergy(), fish.getTurnsAlive());
		ocean.setMoved(true);
	}

	public void moveShark(WaTorCell shark, WaTorCell other, boolean ate) { 
		other.setMoved(true);
		if(ate){
			other.makeShark(shark.getEnergy() + other.getEnergy(), shark.getTurnsAlive());
		}
		else{
			other.makeShark(shark.getEnergy() - 1, shark.getTurnsAlive());
		}
		shark.makeOcean();
	}

	public List<WaTorCell> getNeighbors (WaTorCell curCell, boolean fishNeighbor){  
		List<WaTorCell> neighborList = new ArrayList<WaTorCell>(); 
		List<Cell> allNeighbors = getAdjNeighbors(curCell, true);
		for(Cell cell: allNeighbors){
			WaTorCell checkedCell = (WaTorCell) cell;
			if(checkedCell.isFish() && fishNeighbor){
				neighborList.add(checkedCell);
			}
			else if(checkedCell.isOcean() && !fishNeighbor){
				neighborList.add(checkedCell);
			}
		}
		return neighborList;
	}
	
}
