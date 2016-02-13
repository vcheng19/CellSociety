package cellsociety_team24;

import java.util.ArrayList;

import cellclasses.Cell;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class HexagonGrid extends Grid {
	

	public HexagonGrid(Cell[][] grid, double cellSize, Group root){
		super(grid, cellSize, root);
	}
	
	public void createCells(){   //make setShape method in Cell class
		Cell[][] myGrid = getGrid();
        for (int i = 0; i < myGrid.length;i++){
			for(int j = 0; j < myGrid.length;j++){
				Cell x = myGrid[i][j];
				double height1 = cellSize;
				//System.out.println(cellSize);
				double side = height1/Math.sqrt(3);
				//System.out.println(side);
				double iIncrement = Math.sqrt(side*side - height1*height1/4);
				System.out.println(iIncrement);
				
				//System.out.println(i +  "    " + j);
				Polygon hexagon = new Polygon();
				
				if(i == 0){
					hexagon.setFill(Color.PURPLE);
				}

				double len1 = i*cellSize;
				double len2 = len1 + iIncrement;
				double len3 = (iIncrement+side)+ i*cellSize;
				double len4 = (iIncrement*2 + side) + i*cellSize;
				double wid1 = j*cellSize;
				double wid2 = (j+.5)*cellSize;
				double wid3 = (j+1)*cellSize;
				
				if(i%2 == 0){
					//change wid1, wid2, wid3
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
				
				x.setToRoot(root, hexagon);				//could possibly refactor this
				ArrayList<Cell> neighbors = getNeighbors(x,false);
				x.setNeighbors(neighbors); 
			}
        }
	}
	
	public ArrayList<Cell> getNeighbors(Cell check, boolean wrap){
		ArrayList<Cell> result = new ArrayList<Cell>();
		int r = check.getX();
		int c = check.getY();
		for(int rChange = -1;rChange <= 1;rChange++){
			int rNew = r + rChange;
			for (int cChange = -1;cChange < 2;cChange++){
				int cNew = c + cChange;
				if( !(rChange == 0 && cChange == 0)){
					if(rNew >= 0 && rNew < myGrid.length && cNew >= 0 && cNew < myGrid.length){
						result.add(myGrid[rNew][cNew]);	
					}
					else if(wrap){
						rNew = wrapCor(rNew);
						cNew = wrapCor(cNew);
						result.add(myGrid[rNew][cNew]);	
					}
				}
			}
		}
		return result;
	}
	
}
