package cellsociety_team24;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public abstract class Cell {
	Color color;
	private int myRow;
	private int myCol;
	Rectangle myRect;
	final static Color DEFAULT_COLOR = Color.BLACK;
	
	public Cell(Group root, Shape s, int x, int y) {
		myRow = x;
		myCol = y;
		//myRect = new Rectangle(x*size, y*size, size, size);
		s.setFill(DEFAULT_COLOR);
		root.getChildren().add(myRect);
	}
	
	public Color getColor(){
		return color;
	} 
	
	public void setColor(Color c){
		myRect.setFill(c);
	}
	
	public int getX(){
		return myRow;
	}
	
	public int getY(){
		return myCol;
	}

}
