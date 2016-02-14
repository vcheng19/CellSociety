package cellclasses;

import java.util.*;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public abstract class Cell {
	private Color color;
	private Circle myCirc; 
	private int myRow;
	private int myCol;
	private final static Color TRANS = new Color(0, 0, 0, 0);
	private Shape myShape;
	private List<Cell> myNeighbors;
	private final static Color DEFAULT_COLOR = Color.BLACK;
	private boolean didMove; 
	
	public Cell(int x, int y){
		myRow = x;
		myCol = y;
		myNeighbors = new ArrayList<Cell>();
	}
	
	
	public Color getTrans(){
		return TRANS;
	}
	
	public Color getDefault(){
		return DEFAULT_COLOR;
	}
	
	public Color getColor(){
		return color;
	} 
	
	public void makeCircle(Group root, double size, int x, int y){
		myCirc = new Circle((x*size) + size/2, (y*size) - size/2, size/8);
		myCirc.setFill(TRANS);
		root.getChildren().add(myCirc);
	}
	
	public void setMoved(boolean moved) { 
		didMove = moved;
	}
	
	public void setColor(Color c){
		myShape.setFill(c);
	}
	
	public void setColorCircle(Color c){
		myCirc.setFill(c);
	}
	
	public int getX(){
		return myRow;
	}
	
	public int getY(){
		return myCol;
	}
	
	public void setNeighbors(List<Cell> neighbors){
		myNeighbors = neighbors;
	}
	
	public List<Cell> getNeighbors(){
		return myNeighbors;
	}
	
	public void setToRoot(Group group, Shape s){
		myShape = s;
		myShape.setFill(DEFAULT_COLOR);
		group.getChildren().add(myShape); //this line
	}
}
