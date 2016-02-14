package cellsociety_team24;

import java.util.ArrayList;

import cellclasses.Cell;
import javafx.scene.Group;
import javafx.scene.shape.Polygon;

public class TriangleGrid extends Grid{
	//private static final double HALFWAY = .5;
	
	public TriangleGrid(){
		
	}
	
	public TriangleGrid(double cellSize1, Group root1){
		super(cellSize1, root1);
	}
	
	public TriangleGrid(Cell[][] grid, double cellSize, Group root){
		super(grid, cellSize, root);
	}
	
	public TriangleGrid(Cell[][] grid, int cellSize, Group root, boolean adj) {
		super(grid, cellSize, root, adj);
		// TODO Auto-generated constructor stub
	}
	
	public void createCells(boolean wrap, int range){   //make setShape method in Cell class
		Cell[][] myGrid = getGrid();
        for (int i = 0; i < myGrid.length;i++){
			for(int j = 0; j < myGrid.length;j++){
				
				//System.out.println(i +  "    " + j);
				Cell x = myGrid[i][j];
				//System.out.println(x.getX() + "   " + x.getY());
				Polygon triangle = new Polygon();
				//double side = Math.sqrt(cellSize*cellSize*4/3);
				
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
					triangle.setRotate(180);
				}
				
				x.setToRoot(root, triangle);
				ArrayList<Cell> neighbors = getNeighbors(x, wrap, range);
				x.setNeighbors(neighbors); 
			}
        }
		//createNeighbors();
	}


	
}
