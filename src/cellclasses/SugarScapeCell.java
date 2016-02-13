package cellclasses;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SugarScapeCell extends Cell{
	private boolean agent;
	private boolean moved; 
	//Agent attributes
	private boolean gender; //True if female, false if male 
	private int vision; //yes
	private int sugarMetabolism;//yes 
	private int sugarAgent;//yes
	private int age; 
	private int maxAge; 

	//Sugar attributes 
	private int sugarAmount; //Yes
	private int sugarAmountMax; //Yes
	private int sugarGrowBackRate; //Yes
	private int sugarGrowBackInterval; //Yes
	private int sugarTick; 
	
	//Interaction attributes
	private boolean occupied; 
	
	public SugarScapeCell(Group root, double size, int x, int y) {
		super(root, size, x, y);
		agent = false; 
		moved = false;
	}
	
	public void setState(boolean state){
		agent = state; 
	}
	
	public boolean isGender() {
		return gender;
	}
	
	public boolean isOccupied(){
		return occupied;
	}
	
	public void setOccupied(boolean move){
		occupied = move;
	}
	
	public int getVision(){
		return vision; 
	}
	
	public void setVision(int vision){
		this.vision = vision; 
	}
	
	public void setMoved(boolean move){
		moved = move; 
	}
	
	public boolean didMove(){
		return moved;
	}
	
	public boolean isAgent(){
		return agent; 
	}
	
	private void setAge(int age) {
		this.age = age; 
		
	}
	
	public int getAge(){
		return age; 
	}
	
	public void setMaxAge(int maxAge){
		this.maxAge = maxAge; 
	}
	
	public int getMaxAge(){
		return maxAge; 
	}
	
	public void updateAge(){
		age++; 
	}

	public void setSugarMetabolism(int sugarMetabolism) {
		this.sugarMetabolism = sugarMetabolism;
	}
	
	public int getSugarAgent(){
		return sugarAgent;
	}
	
	public int getSugarMetabolism(){
		return sugarMetabolism; 
	}

	public void setSugar(int sugarAgent) {
		this.sugarAgent = sugarAgent;
	}
	
	public void updateSugarTick(){
		sugarTick++;
	}
	
	public int getSugarTick(){
		return sugarTick;
	}
	
	public int getSugarAmount(){
		return sugarAmount; 
	}
	
	public void updateSugarAmount(boolean increase){
		if(increase){	
			sugarAmount += sugarGrowBackRate;
			if(sugarAmount > sugarAmountMax){
				sugarAmount = sugarAmountMax;
				myRect.setFill(Color.ORANGE);
			}
			else if(sugarAmount >= (sugarAmountMax - (sugarAmountMax *(1/3)))){
				myRect.setFill(Color.GOLD);
			}
			else if(sugarAmount >= (sugarAmountMax - (sugarAmountMax *(2/3)))){
				myRect.setFill(Color.BISQUE);
			}
			sugarTick = 0; 
		}
		else{
			sugarAmount = 0;
			myRect.setFill(Color.WHITE);
		}
	}

	public void setSugarAmount(int sugarAmount) {
		this.sugarAmount = sugarAmount;
	}
	
	public void eatSugar(int sugarConsumed){
		sugarAgent += sugarConsumed; 
	}

	public void setSugarGrowBackRate(int sugarGrowBackRate) {
		this.sugarGrowBackRate = sugarGrowBackRate;
	}

	public void setSugarGrowBackInterval(int sugarGrowBackInterval) {
		this.sugarGrowBackInterval = sugarGrowBackInterval;
	}
	
	public void makeAgent(int vision, int sugarAgent, int sugarMetabolism, int maxAge){
		this.vision = vision; 
		this.sugarAgent = sugarAgent;
		this.sugarMetabolism = sugarMetabolism; 
		this.maxAge = maxAge; 
		agent = true; 
		myCirc.setFill(Color.BLACK);
		int pick = (int) Math.random() * 1;
		if(pick == 0) gender = true;
		else gender = false; 
	}
	
	public void makeSugar(int sugarAmountMax, int sugarGrowBackRate, int sugarGrowBackInterval){
		this.sugarAmountMax = sugarAmountMax;
		sugarAmount = sugarAmountMax; 
		this.sugarGrowBackRate = sugarGrowBackRate;
		this.sugarGrowBackInterval = sugarGrowBackInterval; 
		sugarTick = 0; 
		myRect.setFill(Color.ORANGE);
	}
	
	public void killAgent(SugarScapeCell agent){
		agent.setState(false); 
		agent.setOccupied(false); 
		agent.setVision(0);
		agent.setAge(0); 
		agent.setMaxAge(0); 
		agent.setSugar(0); 
		agent.setSugarMetabolism(0); 
		myCirc.setFill(TRANS);
	}
	
	public void moveAgent(SugarScapeCell agent, SugarScapeCell destination){
		destination.setState(true);
		destination.setOccupied(true);
		destination.setMoved(true);
		destination.setSugarAmount(agent.getSugarAgent());
		destination.setVision(agent.getVision()); 
		destination.setAge(agent.getAge()); 
		destination.setMaxAge(agent.getMaxAge());
		destination.eatSugar(destination.getSugarAmount()); 
		agent.updateSugarAmount(false);
		agent.killAgent(agent);
		destination.myCirc.setFill(Color.BLACK);
	}
}
