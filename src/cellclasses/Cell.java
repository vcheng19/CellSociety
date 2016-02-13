package cellclasses;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public abstract class Cell {
	Group myRoot;
	Color color;
	private int myRow;
	private int myCol;
	//Rectangle myRect;
	Shape myShape;
	private ArrayList<Cell> myNeighbors;
	final static Color DEFAULT_COLOR = Color.BLACK;
	
	public Cell(int x, int y){
		myRow = x;
		myCol = y;
		myNeighbors = new ArrayList<Cell>();
	}
	
	public Cell(Group root, Shape s, int x, int y) {
		myRow = x;
		myCol = y;
		myShape = s;
		//myRect = new Rectangle(x*size, y*size, size, size);
		s.setFill(DEFAULT_COLOR);
		root.getChildren().add(myShape);
	}
	
	public Cell(Group root, double size, int x, int y) {
		myRow = x;
		myCol = y;
		myShape = new Rectangle(x*size, y*size, size, size);
		myShape.setFill(DEFAULT_COLOR);
		root.getChildren().add(myShape);
	}
	
	public Color getColor(){
		return color;
	} 
	
	public void setColor(Color c){
		myShape.setFill(c);
	}
	
	public int getX(){
		return myRow;
	}
	
	public int getY(){
		return myCol;
	}
	
	public void setNeighbors(ArrayList<Cell> neighbors){
		//System.out.println(neighbors.size());
		myNeighbors = neighbors;
	}
	
	public ArrayList<Cell> getNeighbors(){
		//System.out.println(myNeighbors.size());
		return myNeighbors;
	}
	
	public void setToRoot(Group group, Shape s){
		myShape = s;
		myShape.setFill(DEFAULT_COLOR);
		group.getChildren().add(myShape); //this line
	}
}
