package cellsociety_team24;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Cell {
	Color color;
	private int myRow;
	private int myCol;
	Rectangle myRect;
	
	public Cell(Group root, double size, int x, int y) {
		myRow = x;
		myCol = y;
		myRect = new Rectangle(x*size, y*size, size, size);
		myRect.setFill(Color.BLACK);
		root.getChildren().add(myRect);
	}
	
	/**public Cell(Color c){
		color = c;
	}
	
	public void setColor(Color c){
		color = c;
	}	
	
	public Color getColor(){
		return color;
	} */
	
	public int getX(){
		return myRow;
	}
	
	public int getY(){
		return myCol;
	}

}
