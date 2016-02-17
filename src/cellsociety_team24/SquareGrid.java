package cellsociety_team24;

import java.util.*;

import cellclasses.Cell;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

public class SquareGrid extends Grid {
	
	public SquareGrid(){}
	
	public void createCells(boolean wrap, int range){
		Cell[][] myGrid = getGrid();
		for (Cell[] cellArray: myGrid){
			for(Cell x: cellArray){
				double cellSize = getCellSize();
				Group root = getRoot();
				boolean adjacent = getAdj();
				Rectangle myRect = new Rectangle(x.getX()*cellSize, x.getY()*cellSize, cellSize, cellSize);
				x.setToRoot(root, myRect);
				List<Cell> neighbors = new ArrayList<Cell>();
				if(adjacent){
					neighbors = getCardinalNeighbors(x, wrap, range);
				}
				else{
					neighbors = getNeighbors(x, wrap, range);
				}
				x.setNeighbors(neighbors);
			}
		}
		
	}
}
