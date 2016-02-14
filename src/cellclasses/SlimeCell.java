package cellclasses;

import javafx.scene.paint.Color;

public class SlimeCell extends Cell {
	private boolean slime;  //might not need this

	
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
