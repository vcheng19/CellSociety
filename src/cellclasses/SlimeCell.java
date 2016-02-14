package cellclasses;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class SlimeCell extends Cell {
	boolean slime;  //might not need this
	
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
		slime = true;
	}
	
	public void makeWhite(){
		setColor(Color.WHITE);
		slime = false;
	}
	
	public void makeGreen(){
		setColor(Color.CHARTREUSE);
		slime = false;
	}
	
	public void makeEmpty(){
		setColor(Color.BLACK);
		slime = false;
	}
	
	

}
