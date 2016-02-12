package cellclasses;

import javafx.scene.Group;

public class SugarScapeCell extends Cell{
	private boolean agent;
	//Agent attributes
	private boolean gender; //True if female, false if male 
	private int vision;
	private int sugarMetabolism; 
	private int sugarAgent;
	
	//Sugar attributes 
	private int sugarAmount; 
	private int sugarGrowBackRate; 
	private int sugarGrowBackInterval; 
//	private int sugarTick; 
	
	public SugarScapeCell(Group root, double size, int x, int y) {
		super(root, size, x, y);
		agent = false;
		
	}
	
	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public void setAgent(boolean agent) {
		this.agent = agent;
	}

	public void setVision(int vision) {
		this.vision = vision;
	}

	public void setSugarMetabolism(int sugarMetabolism) {
		this.sugarMetabolism = sugarMetabolism;
	}

	public void setSugar(int sugarAgent) {
		this.sugarAgent = sugarAgent;
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
	
	public void makeSugar(int sugarAmount, int sugarGrowBackRate, int sugarGrowBackInterval){
		this.sugarAmount = sugarAmount;
		this.sugarGrowBackRate = sugarGrowBackRate;
		this.sugarGrowBackInterval = sugarGrowBackInterval; 
	}
	
	public void killAgent(){
		agent = false; 
	}

}
