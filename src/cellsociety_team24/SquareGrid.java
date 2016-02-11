package cellsociety_team24;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class SquareGrid extends Grid {

	public SquareGrid(int size, int cellSize, Group root) {
		super(size, cellSize, root);
		// TODO Auto-generated constructor stub
	}
	
	public void createCells(int yBound){
		for(int i = 0; i< mySize; i++){
			for(int j = 0; i < yBound; j++){
				Shape shape = new Rectangle(i*mySize, i*mySize, mySize, mySize);
				Cell c = new Cell(root, shape, i, j);
				myGrid.add(c);
			}
		}
		
	}
	
	public void createNeighbors(){
		for (Cell x: myGrid){
			ArrayList<Cell> neighbors = getAdjNeighbors(x); 
			x.setNeighbors(neighbors); 		//not too good to set stuff but couldnt figure out better way
		}
	}
	
	//how do I diferrentiate b/w whether I want adjNeighbors or real neighbors tho
	
	//move the adjacent neighbors here bc only squares have adj
	private ArrayList<Cell> getAdjNeighbors(Cell check){
		ArrayList<Cell> result = new ArrayList<Cell>();
		ArrayList<Cell> allNeighbors= getNeighbors(check);
		int myRow = check.getX();
		int myCol = check.getY();
		for(Cell x: allNeighbors){
			int row = x.getX();
			int col = x.getY();
			if( row == myRow || col == myCol){
				FireCell adjCell = (FireCell)x;
				result.add(adjCell);
			}
		}
		return result;
	}

}
