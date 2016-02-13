package cellsociety_team24;

import java.util.ArrayList;

import cellclasses.Cell;
import javafx.scene.Group;
import javafx.scene.shape.Polygon;

public class TriangleGrid extends Grid{
	//private static final double HALFWAY = .5;
	

	public TriangleGrid(Cell[][] grid, double cellSize, Group root){
		super(grid, cellSize, root);
	}
	
	public void createCells(boolean wrap){   //make setShape method in Cell class
		Cell[][] myGrid = getGrid();
        for (int i = 0; i < myGrid.length;i++){
			for(int j = 0; j < myGrid.length;j++){
				
				//System.out.println(i +  "    " + j);
				Cell x = myGrid[i][j];
				//System.out.println(x.getX() + "   " + x.getY());
				Polygon triangle = new Polygon();

				double len1 = i*cellSize;
				double len2 = (i+1)*cellSize;
				double len3 = (i+2)*cellSize;
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
				ArrayList<Cell> neighbors = getNeighbors(x,wrap);
				x.setNeighbors(neighbors); 
			}
        }
//		for (int i = 0; i < myGrid.length;i++){
//			for(int j = 0; j < myGrid.length;j++){
//				//System.out.println(i +  "    " + j);
//				
//				Cell x = myGrid[i][j];
//				Polygon triangle = new Polygon();
//				double len1 = i*cellSize;
//				double wid1 = j*cellSize;
//				double len2 = i*cellSize;
//				double wid2 = (j+1)*cellSize;
//				double len3 = (i+1)*cellSize;
//				double wid3 = (j+.5)*cellSize;
//				triangle.getPoints().addAll(new Double[]{
//					    len1, wid1,
//					    len2, wid1,
//					    len3, wid3 });
//				
////				double len1 = i*cellSize;
////				double wid1 = j*cellSize;
////				double len2 = (i+1)*cellSize;
////				double wid2 = (j+1)*cellSize;
////				
////				//if(j%2 == 0){
////					triangle.getPoints().addAll(new Double[]{
////					    len1, wid1,
////					    len2, wid1,
////					    len1, wid2 });
////					System.out.println(len1 + " " + wid1);
////					System.out.println(len2 + " " + wid1);
////					System.out.println(len1 + " " + wid2);
//				//}
////				else{
////					double len3 = (j-1)*cellSize;
////					double wid3 = ()
////					triangle.getPoints().addAll(new Double[]{
////						    len1, wid1,
////						    len2, wid2,
////						    len1, wid2 });
////					
////				}
//				
////				x.setToRoot(root, triangle);
////				ArrayList<Cell> neighbors = getNeighbors(x,false);
////				x.setNeighbors(neighbors); 
//////				
////				Cell y = myGrid[i][j+1];
////				Polygon triangle2 = new Polygon();
////				triangle2.getPoints().addAll(new Double[]{
////					    len1, wid2,
////					    len2, wid1,
////					    len2, wid2 });
////				
////				y.setToRoot(root, triangle2);
////				ArrayList<Cell> neighbors2 = getNeighbors(y,false);
////				y.setNeighbors(neighbors2);
//				
//			}
//		}
		
		//createNeighbors();
	}


	
}
