package cellsociety_team24;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
public abstract class Grid {
	ArrayList<Cell> myGrid;
	int mySize; 
	double cellSize;
	Group root;
	
	public Grid(int size, double cellSize, Group root){
		myGrid = new ArrayList<Cell>();
		mySize = size;
		cellSize = this.cellSize;
		root = this.root;
	}
	
	public void createCells(int yBound){
		for(int i = 0; i< mySize; i++){
			for(int j = 0; i < yBound; j++){
				Shape shape = new Rectangle(i*mySize, i*mySize, mySize, mySize);
				Cell c = new Cell(root, shape, i, j);
				myGrid.add(c);
			}
		}
		
		createNeighbors();
	}
	
	//public abstract void createNeighbors();
	
	public int getYBound(){  //override this for the hexagon
		return mySize;
	}
	
	public void createNeighbors(){
		for (Cell x: myGrid){
			ArrayList<Cell> neighbors = getNeighbors(x);
			x.setNeighbors(neighbors); 		//not too good to set stuff but couldnt figure out better way
		}
	}
	
	//this is the finite rectangular neighbors
	//also works for triangular fun fact
	//
	public ArrayList<Cell> getAdjNeighbors(Cell check){
		return getNeighbors(check);
	}
	
	public ArrayList<Cell> getNeighbors(Cell check){
		ArrayList<Cell> result = new ArrayList<Cell>();
		int r = check.getX();
		int c = check.getY();
		for(int rChange = -1;rChange < 2;rChange++){
			if(r + rChange >= 0 && r + rChange < mySize){//If r is within the boundaries of the grid
				for(int cChange = -1;cChange < 2;cChange++){
					if(c + cChange >= 0 && c + cChange < mySize && !(rChange == 0 && cChange == 0)){
						result.add(myGrid.get((r+rChange)*mySize+c+cChange));
					}
				}
			}
		}
		return result;
	}
}
