package cellclasses;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public abstract class Cell {
	Color color;
	private int myRow;
	private int myCol;
	Rectangle myRect;
	Circle myCirc;
	private Group myRoot; 
	private double mySize; 
	final static Color DEFAULT_COLOR = Color.BLACK;
	final static Color TRANS = new Color(0, 0, 0, 0);
	public Cell(Group root, double size, int x, int y) {
		myRow = x;
		myCol = y;
		mySize = size; 
		myRoot = root; 
		myRect = new Rectangle(x*size, y*size, size, size);
		myCirc = new Circle(x*size, y*size, size/5);
		myRect.setFill(DEFAULT_COLOR);
		myCirc.setFill(TRANS);
		myRoot.getChildren().add(myRect);
		myRoot.getChildren().add(myCirc);
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
