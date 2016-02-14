package cellsociety_team24;

import java.util.ArrayList;

import cellclasses.Cell;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class SquareGrid extends Grid {
	
	public SquareGrid(){
		
	}
	
	public SquareGrid(double cellSize1, Group root1){
		super(cellSize1, root1);
	}

	public SquareGrid(Cell[][] grid, int cellSize, Group root, boolean adj) {
		super(grid, cellSize, root, adj);
		// TODO Auto-generated constructor stub
		adjacent = adj;
	}
	public SquareGrid(Cell[][] grid, int cellSize, Group root) {
		super(grid, cellSize, root);
		// TODO Auto-generated constructor stub
		adjacent = false;
	}
	
//	public void createCells(int yBound){
//		for(int i = 0; i< mySize; i++){
//			for(int j = 0; i < yBound; j++){
//				Shape shape = new Rectangle(i*mySize, i*mySize, mySize, mySize);
//				Cell c = new Cell(root, shape, i, j);
//				myGrid.add(c);
//			}
//		}
//		
//	}
	
//	public void setAdjacency(boolean adj){
//		adjacent = adj;
//	}
	
	public void createCells(boolean wrap, int range){
		Cell[][] myGrid = getGrid();
		for (Cell[] cellArray: myGrid){
			for(Cell x: cellArray){
				//System.out.println(x.getX() + "   " + x.getY());
				Rectangle myRect = new Rectangle(x.getX()*cellSize, x.getY()*cellSize, cellSize, cellSize);
				
				
				x.setToRoot(root, myRect);
				ArrayList<Cell> neighbors = new ArrayList<Cell>();
				if(adjacent){
					neighbors = getCardinalNeighbors(x, wrap, range);
				}
				else{
					neighbors = getNeighbors(x, wrap, range);
				}
				x.setNeighbors(neighbors); 		//not too good to set stuff but couldnt figure out better way
			}
		}
		
	}
	
	
	
//	public void createNeighbors(Cell cell){
//		ArrayList<Cell> neighbors = getAdjNeighbors(cell); 
//		cell.setNeighbors(neighbors); 		//not too good to set stuff but couldnt figure out better way
//	}
	
	//how do I diferrentiate b/w whether I want adjNeighbors or real neighbors tho
	
	//move the adjacent neighbors here bc only squares have adj

}
