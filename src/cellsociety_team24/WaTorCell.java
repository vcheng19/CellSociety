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
	
	public WaTorCell(Group root, double size, int x, int y, int sharkEnergy, int fishEnergy) {
		super(root, size, x, y);
		type = "ocean";
		turnsAlive = 0; 
		energyLevel = 0;
		this.sharkEnergy = sharkEnergy; 
	}
	
	public void makeFish(){
		type = "fish";
		myRect.setFill(Color.GREEN);
	}
	
	public void makeShark(){
		type = "shark";
		myRect.setFill(Color.YELLOW);
		energyLevel = sharkEnergy; 
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
	
	public int getTurnAlive(){
		return turnsAlive; 
	}
	
	public void updateTurn(){
		turnsAlive++;
	}
	
	public void resetTurn(){
		turnsAlive = 0;
	}
	
}
