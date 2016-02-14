package cellclasses;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public abstract class Cell {
	Group myRoot;
	Color color;
	private Circle myCirc; 
	private int myRow;
	private int myCol;
	final static Color TRANS = new Color(0, 0, 0, 0);
	//Rectangle myRect;
	private Shape myShape;
	private ArrayList<Cell> myNeighbors;
	final static Color DEFAULT_COLOR = Color.BLACK;
	public boolean didMove; 
	
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
//		myCirc = new Circle((x*size) + size/2, (y*size) - size/2, size/8);
//		myCirc.setFill(TRANS);
		myShape.setFill(DEFAULT_COLOR);
		root.getChildren().add(myShape);
//		root.getChildren().add(myCirc);
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
