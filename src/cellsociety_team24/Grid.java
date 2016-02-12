package cellsociety_team24;

import java.util.ArrayList;

import cellclasses.Cell;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public abstract class Grid {
	//ArrayList<Cell> myGrid;
	Cell[][] myGrid;
	int mySize; 
	double cellSize;
	Group root;
	
	public Grid(Cell[][] grid, double cellSize1, Group root1){
		//myGrid = new ArrayList<Cell>();
		//mySize = size;
		myGrid = grid;
		cellSize = cellSize1;
		root = root1;
	}
	
	public abstract void createCells();
	
//	public void createCells(int yBound){
//		for(int i = 0; i< mySize; i++){
//			for(int j = 0; i < yBound; j++){
//				Shape shape = new Rectangle(i*mySize, i*mySize, mySize, mySize);
//				Cell c = new Cell(root, shape, i, j);
//				myGrid.add(c);
//			}
//		}
//		
//		createNeighbors();
//	}
//	
	//public abstract void createNeighbors();
	
	public int getYBound(){  //override this for the hexagon
		return mySize;
	}
	
	public Cell[][] getGrid(){
		return myGrid;
	}
	
	public Group getRoot(){
		return root;
	}
	
	public void createNeighbors(){
		for (Cell[] cellArray: myGrid){
			for(Cell x: cellArray){
				ArrayList<Cell> neighbors = getNeighbors(x, false);
				x.setNeighbors(neighbors); 		//not too good to set stuff but couldnt figure out better way
			}
		}
	}
	
	//this is the finite rectangular neighbors
	//also works for triangular fun fact
	//
	public ArrayList<Cell> getAdjNeighbors(Cell check, boolean wrap){
		return getNeighbors(check, wrap);
	}
	
	public ArrayList<Cell> getNeighbors(Cell check, boolean wrap){
		ArrayList<Cell> result = new ArrayList<Cell>();
		int r = check.getX();
		int c = check.getY();
		for(int rChange = -1;rChange < 2;rChange++){
			int rNew = r + rChange;
			for (int cChange = -1;cChange < 2;cChange++){
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
