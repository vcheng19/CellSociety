package cellsociety_team24;

import java.util.ArrayList;

import cellclasses.Cell;
import javafx.scene.Group;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class TriangleGrid extends Grid{
	//private static final double HALFWAY = .5;
	

	public TriangleGrid(Cell[][] grid, double cellSize, Group root){
		super(grid, cellSize, root);
	}
	
	public void createCells(){   //make setShape method in Cell class
		Cell[][] myGrid = getGrid();
		for (int i = 0; i < myGrid.length;i++){
			for(int j = 0; j < myGrid.length;j++){
				System.out.println(i +  "    " + j);
				Cell x = myGrid[i][j];
				Polygon triangle = new Polygon();
//				double len1 = i*cellSize;
//				double wid1 = j*cellSize;
//				double len2 = i*cellSize;
//				double wid2 = (j+1)*cellSize;
//				double len3 = (i+1)*cellSize;
//				double wid3 = (j+HALFWAY)*cellSize;
	
				double len1 = i*cellSize;
				double wid1 = j*cellSize;
				double wid2 = (j+1)*cellSize;
				double len2 = (i+1)*cellSize;
				
				triangle.getPoints().addAll(new Double[]{
					    len1, wid1,
					    len2, wid2,
					    len2, wid1 });
				
				x.setToRoot(root, triangle);
				ArrayList<Cell> neighbors = getNeighbors(x,false);
				x.setNeighbors(neighbors); 
				
//				x = myGrid[i][j+1];
//				double wid4 = wid3+cellSize;
//				Polygon triangle2 = new Polygon();
//				triangle.getPoints().addAll(new Double[]{
//					    len2, wid2,
//					    len3, wid3,
//					    len3, wid4 });
//				
//				x.setToRoot(root, triangle2);
//				ArrayList<Cell> neighbors2 = getNeighbors(x,false);
//				x.setNeighbors(neighbors2); 

			}
		}
		
		//createNeighbors();
	}


	
}
