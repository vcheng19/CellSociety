package cellclasses;

import java.util.concurrent.Callable;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class SegregationCell extends Cell {
	private boolean isEmpty;
	private boolean isRed;
	private boolean isBlue;
	
	public SegregationCell(Group root, double size, int x, int y){
		super(root, size, x, y);
		isEmpty = true;
		isRed = false;
		isBlue = false;
	}
	
	public boolean isEmpty(){
		return isEmpty;
	}
	
	public boolean isRed(){
		return isRed;
	}
	
	public boolean isBlue(){
		return isBlue;
	}
	
	public void makeRed(){
		setColor(Color.RED);
		setFalse();
		isRed = true;
	}
	
	public void makeBlue(){
		setColor(Color.BLUE);
		setFalse();
		isBlue = true;
	}
	
	public void makeEmpty(){
		setColor(DEFAULT_COLOR);
		setFalse();
		isEmpty = true;
	}
	
	private void setFalse(){
		isEmpty = false;
		isRed = false;
		isBlue = false;
	}
}
