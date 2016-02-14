package cellclasses;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class GameOfLifeCell extends Cell {
	private boolean dead;
	
	public GameOfLifeCell(int x, int y){
		super(x,y);
		dead = true;
	}
	
	public void makeAlive(){
		dead = false;
		setColor(Color.WHITE);
	}
	
	public void killCell(){
		dead = true;
		setColor(Color.BLACK);
	}
	
	public boolean isDead(){
		return dead;
	}
}
