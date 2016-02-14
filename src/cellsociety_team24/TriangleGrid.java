package cellsociety_team24;

import java.util.*;

import cellclasses.Cell;
import javafx.scene.Group;
import javafx.scene.shape.Polygon;

public class TriangleGrid extends Grid{
	
	private final static int ROTATE_AMOUNT = 180;
	
	public TriangleGrid(){}
	
	public void createCells(boolean wrap, int range){   //make setShape method in Cell class
		Cell[][] myGrid = getGrid();
        for (int i = 0; i < myGrid.length;i++){
			for(int j = 0; j < myGrid.length;j++){
				Cell x = myGrid[i][j];
				Polygon triangle = new Polygon();
				
				double cellSize = getCellSize();
				Group root = getRoot();
				
				double len1 = i*cellSize;
				double len2 = (i+1)*cellSize;
				double len3 = (2 +i)*cellSize;
				double wid1 = j*cellSize;
				double wid2 = (j+1)*cellSize;
				triangle.getPoints().addAll(new Double[]{
					    len1, wid1,
					    len2, wid2,
					    len3, wid1 });
				
				if(j%2 == i%2){
					triangle.setRotate(ROTATE_AMOUNT);
				}
				
				x.setToRoot(root, triangle);
				List<Cell> neighbors = getNeighbors(x, wrap, range);
				x.setNeighbors(neighbors); 
			}
        }
	}


	
}
