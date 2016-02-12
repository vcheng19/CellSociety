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
	
	public SugarScapeCell(Group root, double size, int x, int y) {
		super(root, size, x, y);
		agent = false;
		moved = false;
	}
	
	public boolean isGender() {
		return gender;
	}
	
	public void setMoved(){
		moved = !moved; 
	}
	
	public boolean didMove(){
		return moved;
	}
	
	public boolean getAgent(){
		return agent; 
	}
	
	public void setAge(int age){
		this.age = age; 
	}
	
	public int getAge(){
		return age; 
	}
	
	public int getMaxAge(){
		return maxAge; 
	}
	
	public void setAgent(boolean agent) {
		this.agent = agent;
	}
	
	public void updateAge(){
		age++; 
	}

	public void setSugarMetabolism(int sugarMetabolism) {
		this.sugarMetabolism = sugarMetabolism;
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
	
	public void updateSugarAmount(boolean increase){
		if(increase){	
			sugarAmount += sugarGrowBackRate;
			if(sugarAmount > sugarAmountMax) sugarAmount = sugarAmountMax;
			sugarTick = 0; 
		}
		else{
			sugarAmount--;
		}
		if()
	}

	public void setSugarAmount(int sugarAmount) {
		this.sugarAmount = sugarAmount;
		
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
		age = 0; 
		maxAge = 0; 
		sugarAgent = 0; 
		sugarMetabolism = 0; 
	}
	

}
