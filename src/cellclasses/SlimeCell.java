package cellclasses;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class SlimeCell extends Cell {
	boolean slime;
	
	public SlimeCell(Group root, double size, int x, int y) {
		super(root, size, x, y);
		// TODO Auto-generated constructor stub
		slime = false;
	}
	
	public SlimeCell(int x, int y){
		super(x,y);
		slime = false;
	}
	
	public boolean isSlime(){
		return slime;
	}
	
	public void makeSlime(){
		setColor(Color.DARKRED);
	}
	
	public void makeWhite(){
		setColor(Color.WHITE);
	}
	
	public void makeGreen(){
		setColor(Color.CHARTREUSE);
	}

}
