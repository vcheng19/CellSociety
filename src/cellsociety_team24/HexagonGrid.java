package cellsociety_team24;

import java.util.*;

import cellclasses.Cell;
import javafx.scene.Group;
import javafx.scene.shape.Polygon;

public class HexagonGrid extends Grid {
	
	public HexagonGrid(){}
	
	public void createCells(boolean wrap, int range){
		Cell[][] myGrid = getGrid();
        for (int i = 0; i < myGrid.length;i++){
			for(int j = 0; j < myGrid.length;j++){
				Cell x = myGrid[i][j];
				double cellSize = getCellSize();
				double height1 = cellSize;
				double side = height1/Math.sqrt(3);								//these numbers are with the Hexagon Side Relation Formula. DO NOT CHANGE
				double iIncrement = Math.sqrt(side*side - height1*height1/4);   //this too
				Polygon hexagon = new Polygon();

				double len1 = i*cellSize;
				double len2 = len1 + iIncrement;
				double len3 = (iIncrement+side)+ i*cellSize;
				double len4 = (iIncrement*2 + side) + i*cellSize;
				double wid1 = j*cellSize;
				double wid2 = (j+.5)*cellSize;
				double wid3 = (j+1)*cellSize;
				
				if(i%2 == 0){
					wid1 = wid1 + .5*cellSize;
					wid2 = wid2 + .5*cellSize;
					wid3 = wid3 + .5*cellSize;
				}
				
				hexagon.getPoints().addAll(new Double[]{
					    len1, wid2,
					    len2, wid1,
					    len3, wid1,
					    len4, wid2,
					    len3, wid3,
					    len2, wid3 });
				
				Group root = getRoot();
				x.setToRoot(root, hexagon);				//could possibly refactor this
				List<Cell> neighbors = myGetNeighbors(x,wrap, range);
				x.setNeighbors(neighbors); 
				
			}
        }
	}
	
	
	public List<Cell> myGetNeighbors(Cell check, boolean wrap, int range){
		List<Cell> result = new ArrayList<Cell>();
		List<Cell> allNeighbors = new ArrayList<Cell>();
		int r = check.getX();
		int c = check.getY();
		
		allNeighbors = getNeighbors(check, wrap, range);
		for(Cell x: allNeighbors){
			if((!(x.getY() == c+1 && x.getX() != r) && r%2 !=0) || (!(x.getY() == c-1 && x.getX() != r ) && r%2==0)){
				result.add(x);
			}
		}
		return result;
	}

	
	
}
