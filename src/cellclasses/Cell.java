package cellclasses;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Cell {
	Color color;
	private int myRow;
	private int myCol;
	Rectangle myRect;
	final static Color DEFAULT_COLOR = Color.BLACK;
	public boolean didMove; 
	
	public Cell(Group root, double size, int x, int y) {
		myRow = x;
		myCol = y;
		myRect = new Rectangle(x*size, y*size, size, size);
		myRect.setFill(DEFAULT_COLOR);
		root.getChildren().add(myRect);
	}
	
	public Color getColor(){
		return color;
	} 
	
	public void setMoved(boolean moved) { 
		didMove = moved;
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
