package cellclasses;

import javafx.scene.paint.Color;

public class WaTorCell extends Cell{
	private String type; 
	private int turnsAlive; 
	private int energyLevel;
	private boolean didMove;
	
	public WaTorCell(int x, int y){
		super(x,y);
		setUp();
	}
	
	private void setUp(){
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
		setColor(Color.GREEN);
	}
	
	public void makeShark(int energy, int ta){
		type = "shark";
		energyLevel = energy;
		turnsAlive = ta;
		setColor(Color.YELLOW);
	}
	
	public void makeOcean(){
		type = "ocean";
		energyLevel = 0;
		setColor(Color.BLUE);
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
