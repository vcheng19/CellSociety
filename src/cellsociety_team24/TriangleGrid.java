package cellsociety_team24;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class TriangleGrid extends Grid{
	private static final double HALFWAY = .5;
	

	public TriangleGrid(int size, double cellSize, Group root){
		super(size, cellSize, root);
	}
	
	public void createCells(int yBound){
		for(int i = 0; i< mySize/2; i++){
			for(int j = 0; i < yBound/2; j++){
				Polygon triangle = new Polygon();
				double len1 = i*cellSize;
				double wid1 = j*cellSize;
				double len2 = i*cellSize;
				double wid2 = (j+1)*cellSize;
				double len3 = (i+1)*cellSize;
				double wid3 = (j+HALFWAY)*cellSize;
	
				triangle.getPoints().addAll(new Double[]{
					     len1, wid1,
					    len2, wid2,
					    len3, wid3 });
				Cell c = new Cell(root, triangle, i, j);
				myGrid.add(c);
				
				double wid4 = wid3+cellSize;
				Polygon triangle2 = new Polygon();
				triangle.getPoints().addAll(new Double[]{
					    len2, wid2,
					    len3, wid3,
					    len3, wid4 });
				
				Cell c2 = new Cell(root, triangle, i, j);
				myGrid.add(c2);
			}
		}
		
		createNeighbors();
	}
	
}
