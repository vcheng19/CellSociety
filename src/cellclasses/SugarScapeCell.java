package cellclasses;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class SugarScapeCell extends Cell{
	private boolean agent;
	private boolean moved; 
	//Agent attributes
	private boolean gender; //True if female, false if male 
	private int vision;
	private int sugarMetabolism; 
	private int sugarAgent;
	private int age; 
	private int maxAge; 
	
	//Sugar attributes 
	private int sugarAmount; 
	private int sugarAmountMax; 
	private int sugarGrowBackRate; 
	private int sugarGrowBackInterval; 
	private int sugarTick; 
	
	//Interaction attributes
	private boolean occupied; 
	
	public SugarScapeCell(Group root, double size, int x, int y) {
		super(root, size, x, y);
		agent = false;
		moved = false;
	}
	
	public boolean isGender() {
		return gender;
	}
	
	public boolean isOccupied(){
		return occupied;
	}
	
	public void setOccupied(){
		occupied = !occupied;
	}
	
	public int getVision(){
		return vision; 
	}
	
	public void setVision(int vision){
		this.vision = vision; 
	}
	
	public void setMoved(){
		moved = !moved; 
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
			if(sugarAmount > sugarAmountMax) sugarAmount = sugarAmountMax;
			sugarTick = 0; 
		}
		else{
			sugarAmount = 0;
			myRect.setFill(Color.BLUE);
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
	
	public void makeAgent(int vision, int sugarAgent, int sugarMetabolism){
		this.vision = vision; 
		this.sugarAgent = sugarAgent;
		this.sugarMetabolism = sugarMetabolism; 
		agent = true; 
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
		myRect.setFill(Color.RED);
	}
	
	public void killAgent(){
		agent = false; 
		occupied = false; 
		vision = 0;
		age = 0; 
		maxAge = 0; 
		sugarAgent = 0; 
		sugarMetabolism = 0; 
		myRect.setFill(Color.BLACK);
	}
	
	public void moveAgent(SugarScapeCell agent, SugarScapeCell destination){
		destination.setOccupied();
		destination.setSugarAmount(agent.getSugarAgent());
		destination.setVision(agent.getVision()); 
		destination.setAge(agent.getAge()); 
		destination.setMaxAge(agent.getMaxAge());
		destination.eatSugar(destination.getSugarAmount()); 
		agent.killAgent();
		myRect.setFill(Color.PURPLE);
	}


}
