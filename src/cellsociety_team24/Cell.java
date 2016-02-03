package cellsociety_team24;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Cell {
	Color color;
	private int myX;
	private int myY;
	Rectangle myRect;
	
	
	public Cell(Group root, double size, int x, int y) {
		// TODO Auto-generated constructor stub
		myX = x;
		myY = y;
		
		Rectangle myRect = new Rectangle(x*size, y*size, size, size);
		myRect.setFill(Color.BLACK);
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
		return myX;
	}
	
	public int getY(){
		return myY;
	}

}
