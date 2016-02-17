// This entire file is part of my masterpiece.
// Cody Li (cl305) 
//Purpose and design: 	
//			The purpose of this code was to provide a data structure to contain the actual grid of the simulation and to incorporate the implementation of different shapes of grids
// 			such as Hexagonal and Triangular. Additionally, another main responsibility of this class was that it provided the methods that cells needed to obtain their neighbors. 
//			The getCardinal and getNeighbor methods provided the tools for each simulation to pick out how they wanted to choose neighbors (either standard or toroidal, and either in the 
//			4 cardinal directions or in all adjacent cells) as well as how many neighbors to retrieve (i.e. the vision variable in SugarScape). The flexibility in the code is that it takes
//			in just the Cell object and then returns a List of Cells such that the simulation can then specifically parse through. This condensed a lot of redundant code into two very flexible
//			and modular methods. 


package cellsociety_team24;

import java.util.*;

import cellclasses.Cell;
import javafx.scene.Group;

public abstract class Grid {
	private Cell[][] myGrid;
	private double cellSize;
	private Group root;
	private boolean adjacent;
	
	public Grid(){}
	
	public abstract void createCells(boolean wrap, int range);
	
	public void setValues(Cell[][] grid, double cellSize1, Group root1, boolean adj1){
		myGrid = grid;
		cellSize = cellSize1;
		root = root1;
		adjacent = adj1;
	}
	
	public void setValues(Cell[][] grid, double cellSize1, Group root1){
		myGrid = grid;
		cellSize = cellSize1;
		root = root1;
	}
	
	
	public Cell[][] getGrid(){
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
					if(rNew >= 0 && rNew < myGrid.length && cNew >= 0 && cNew < myGrid.length){
						result.add(myGrid[rNew][cNew]);	
					}
					else if(wrap){
						rNew = wrapCor(rNew);
						cNew = wrapCor(cNew);
						result.add(myGrid[rNew][cNew]);	
					}
				}
			}
		}
		return result;
	}
	
	public int wrapCor(int cor) { 
		if(cor < 0) cor = myGrid.length - 1;
		else if(cor > myGrid.length - 1) cor = 0;
		return cor; 
	}
}
