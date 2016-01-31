package cellsociety_team24;

import javafx.scene.paint.Color;

public abstract class Cell {
	Color color;
	
	public Cell() {
		// TODO Auto-generated constructor stub
		color = Color.BLACK;
	}
	
	/**public Cell(Color c){
		color = c;
	}
	
	public void setColor(Color c){
		color = c;
	}	*/
	
	public Color getColor(){
		return color;
	}
	

}
