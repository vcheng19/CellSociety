package cellsociety_team24;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class WaTorCell extends Cell{
//	private boolean fish;
//	private boolean shark;
//	private boolean ocean; 
	String type; 
	private int turnsAlive; 
	private int energyLevel;
	private int sharkEnergy;
	private int fishEnergy;
	private boolean didMove;
	
	public WaTorCell(Group root, double size, int x, int y, int sharkEnergy, int fishEnergy) {
		super(root, size, x, y);
		type = "ocean";
		turnsAlive = 0; 
		energyLevel = 0;
		didMove = false;
		this.sharkEnergy = sharkEnergy; 
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
	
	public void updateEnergy(boolean eaten){
		if(eaten){
			energyLevel+=fishEnergy;
		}
		else{
			energyLevel--;
		}
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
	
	public void resetTurn(){
		turnsAlive = 0;
	}
	
}
