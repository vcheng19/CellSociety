//This code is part of my masterpiece
//Virginia Cheng
//The purpose of this class is that it allows for multiple grid types to be created and I think it shows that I finally understood what inheritance was.
//This class is convenient to use because it allows the user to not have to cast the Grid or specify what type of Grid is being used in each GridInitializer since each subclass of Grid contains the createCells() method, which is the only method any outside class will need to use.
//I refactored it a lot while I was making it to include the comprehensive getNeighbors() and getCardinalNeighbors() methods that each Grid class can use to find the neighbors.
//And it also allowed the cells to become more independent by allowing them to hold their own neighbors and freeing the RuleEnforcers of some responsibilities.
//It also saved time and prevented the RuleEnforcer class from having to find the neighbors of each cell for every iteration of the grid, which is not efficent at all.
//
//I refactored it a little bit more with the assumption that our grid would be List for flexibility purposes.
// And also changed the constructor to take in the cellSize, so the Grid can create an initial grid and limit the dependencies.
//Pretty much I moved the basic aspects of the grid that can be made at the beginning and put that into the constructor and made the inputs that are simulation specific a parameter of the createCells method.
//And now the List<Cell> would be created in the Grid class and then returned in the Grid class which would lessen the dependencies and the work the GridInitializer would have to do,
//as well as pave the way for RuleEnforcers to use List<Cell> instead of Cell[][]


package cellsociety_team24;

import java.util.*;

import cellclasses.Cell;
import javafx.scene.Group;

public abstract class Grid {
	private List<Cell> myGrid;
	private double cellSize;
	private Group root;
	private boolean adjacent;
	private int gridSize;
	
	public Grid(){}
	
	public Grid(int gridSize1, Group root1, double cellSize1){
		cellSize = cellSize1;
		root = root1;
		gridSize = gridSize1;
		makeGrid();
	}
	
	public abstract List<Cell> createCells(boolean wrap, int range, boolean adj1);
	
	private void makeGrid(){
		myGrid = new ArrayList<Cell>();
		for(int i  = 0; i < gridSize; i++){
			for(int j= 0; j <gridSize; j++){
				Cell cell = new Cell(i,j);
				myGrid.add(cell);
			}
		}
	}
	
	
	public List<Cell> getGrid(){
		return myGrid;
	}
	
	public Group getRoot(){
		return root;
	}
	
	public boolean getAdj(){
		return adjacent;
	}
	
	public double getCellSize(){
		return cellSize;
	}
	
	public List<Cell> getCardinalNeighbors(Cell check, boolean wrap, int range){
		ArrayList<Cell> result = new ArrayList<Cell>();
		List<Cell> allNeighbors = getNeighbors(check, wrap, range);

		int myRow = check.getX();
		int myCol = check.getY();
		for(Cell x: allNeighbors){
			int row = x.getX();
			int col = x.getY();
			if( row == myRow || col == myCol){
				Cell adjCell = x;
				result.add(adjCell);
			}
		}
		return result;
	}
	
	public List<Cell> getNeighbors(Cell check, boolean wrap, int range){
		ArrayList<Cell> result = new ArrayList<Cell>();
		int r = check.getX();
		int c = check.getY();
		for(int rChange = -range;rChange <= range;rChange++){
			int rNew = r + rChange;
			for (int cChange = -range;cChange <=range;cChange++){
				int cNew = c + cChange;
				if( !(rChange == 0 && cChange == 0)){
					if(rNew >= 0 && rNew < gridSize && cNew >= 0 && cNew < gridSize){
						result.add(myGrid.get(rNew*gridSize + cNew));	
					}
					else if(wrap){
						rNew = wrapCor(rNew);
						cNew = wrapCor(cNew);
						result.add(myGrid.get(rNew*gridSize + cNew));	
					}
				}
			}
		}
		return result;
	}
	
	public int wrapCor(int cor) { 
		if(cor < 0) cor = gridSize - 1;
		else if(cor > gridSize - 1) cor = 0;
		return cor; 
	}
}
