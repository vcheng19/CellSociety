package cellsociety_team24;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class WaTorCell extends Cell{
	private boolean fish;
	private boolean shark;
	private boolean ocean; 
	private int turnAlive; 
	
	public WaTorCell(Group root, double size, int x, int y) {
		super(root, size, x, y);
		fish = false;
		shark = false; 
		ocean = true; 
		turnAlive = 0; 
		// TODO Auto-generated constructor stub
	}
	
	public void makeFish(){
		fish = true;
		shark = false; 
		ocean = false; 
		myRect.setFill(Color.GREEN);
	}
	
	public void makeShark(){
		fish = false;
		shark = true;
		ocean = false;
		myRect.setFill(Color.YELLOW);
	}
	
	public void makeOcean(){
		fish = false;
		shark = false;
		ocean = true; 
		myRect.setFill(Color.BLUE);
	}
	
	public boolean[] getState(){
		boolean[] state = new boolean[3]; 
		state[0] = fish; 
		state[1] = shark;
		state[2] = ocean; 
		
		return state; 
	}
	
	public int getTurnAlive(){
		return turnAlive; 
	}
	
	public void updateTurn(){
		turnAlive++;
	}
}
