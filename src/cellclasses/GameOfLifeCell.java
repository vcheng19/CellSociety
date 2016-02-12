package cellclasses;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class GameOfLifeCell extends Cell {
	private boolean dead;
	
	public GameOfLifeCell(Group root, double size, int x, int y){
		super(root, size, x, y);
		dead = true;
	}
	
	public GameOfLifeCell(int x, int y){
		super(x,y);
		dead = true;
	}
	
//	public GameOfLifeCell(Group root, Shape s, int x, int y) {
//		super(root, s, x, y);
//		//myRect = new Rectangle(x*size, y*size, size, size);
//		s.setFill(DEFAULT_COLOR);
//		root.getChildren().add(s);
//	}
	
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
