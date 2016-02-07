package cellsociety_team24;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class WaTorCell extends Cell{
	String type; 
	private int turnsAlive; 
	private int energyLevel;
	private boolean didMove;
	
	public WaTorCell(Group root, double size, int x, int y) {
		super(root, size, x, y);
		makeOcean();
		turnsAlive = 0; 
		energyLevel = 0;
		didMove = false;
	}
	
	public boolean getMoved() { 
		return didMove;
	}
	
	public void setMoved(boolean moved) { 
		didMove = moved;
	}
	
	public void makeFish(int energy, int ta){
		type = "fish";
		energyLevel = energy;
		turnsAlive = ta;
		myRect.setFill(Color.GREEN);
	}
	
	public void makeShark(int energy, int ta){
		type = "shark";
		energyLevel = energy;
		turnsAlive = ta;
		myRect.setFill(Color.YELLOW);
	}
	
	public void makeOcean(){
		type = "ocean";
		energyLevel = 0;
		myRect.setFill(Color.BLUE);
	}
	
	public boolean isFish(){
		return type == "fish"; 
	}
	
	public boolean isShark(){
		return type == "shark";
	}
	
	public boolean isOcean(){
		return type == "ocean"; 
	}
	
	public int getEnergy(){
		return energyLevel; 
	}
	
	public void setEnergy(int n){
		energyLevel = n;
	}
	
	public int getTurnsAlive(){
		return turnsAlive; 
	}
	
	public void updateTurn(){
		turnsAlive++;
	}
	
	public void resetTurns(){
		turnsAlive = 0;
	}
}
