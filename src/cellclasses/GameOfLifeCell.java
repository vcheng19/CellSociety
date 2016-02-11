package cellclasses;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class GameOfLifeCell extends Cell {
	private boolean dead;
	
	public GameOfLifeCell(Group root, double size, int x, int y){
		super(root, size, x, y);
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
