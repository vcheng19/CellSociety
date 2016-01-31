package cellsociety_team24;

import javafx.scene.paint.Color;

public class GameOfLifeCell extends Cell {
	private boolean dead;
	
	public GameOfLifeCell(){
		super();
		dead = true;
	}
	
	public void makeAlive(){
		dead = false;
		color = Color.WHITE;
	}
	
	public void killCell(){
		dead = true;
		color = Color.BLACK;
	}
	
	public boolean isDead(){
		return dead;
	}
}
