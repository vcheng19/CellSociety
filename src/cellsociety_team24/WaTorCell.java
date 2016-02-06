package cellsociety_team24;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class WaTorCell extends Cell{
	private boolean fish;
	private boolean shark;
	private boolean ocean; 
	private int turnAlive; 
	private int energyLevel;
	public WaTorCell(Group root, double size, int x, int y) {
		super(root, size, x, y);
		fish = false;
		shark = false; 
		ocean = true; 
		turnAlive = 0; 
		energyLevel = 0;
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
		energyLevel = 2; 
	}
	
	public void makeOcean(){
		fish = false;
		shark = false;
		ocean = true; 
		myRect.setFill(Color.BLUE);
	}
	
	public boolean isFish(){
		return fish; 
	}
	
	public boolean isShark(){
		return shark;
	}
	
	public boolean isOcean(){
		return ocean; 
	}
	
	public void updateEnergy(boolean shark, boolean increment){
		if(shark){
			if(increment){
				energyLevel+=2;
			}
			else{
				energyLevel--;
			}
		}
		else{
			energyLevel = 0; 
		}
	}
	
	public int getEnergy(){
		return energyLevel; 
	}
	
	public int getTurnAlive(){
		return turnAlive; 
	}
	
	public void updateTurn(){
		turnAlive++;
	}
	
	public void resetTurn(){
		turnAlive = 0;
	}
}
