package cellsociety_team24;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class SegregationCell extends Cell {
	private boolean isEmpty;
	public SegregationCell(Group root, double size, int x, int y){
		super(root, size, x, y);
		myRect.setFill(Color.WHITE);
		isEmpty = true;
	}
	
	public boolean isEmpty(){
		return isEmpty;
	}
	
	
	
	

}
