package cellclasses;

import javafx.scene.Group;

public class SugarScapeCell extends Cell{
	boolean agent;
	//Agent attributes
	boolean gender; //True if female, false if male 
	int vision;
	int sugarMetabolism; 
	int sugar; 
	
	//Sugar attributes 
	int sugarAmount;
	int sugarGrowBackRate; 
	int sugarGrowBackInterval; 
	
	public SugarScapeCell(Group root, double size, int x, int y) {
		super(root, size, x, y);
		agent = false;
		
	}
	
	public boolean isAgent(){
		return agent; 
	}
	
	public boolean isFemale() {
		return gender;
	}

	public int getVision() {
		return vision;
	}

	public int getSugarMetabolism() {
		return sugarMetabolism;
	}

	public int getSugar() {
		return sugar;
	}

	public int getSugarAmount() {
		return sugarAmount;
	}

	public int getSugarGrowBackRate() {
		return sugarGrowBackRate;
	}

	public int getSugarGrowBackInterval() {
		return sugarGrowBackInterval;
	}
	
	public void eatSugar(){
		sugar++;
	}
	
	public void generateSugar(){
		sugarAmount++; 
	}
	
	public void makeAgent(){
		agent = true; 
		int pick = (int) Math.random() * 1;
		if(pick == 0) gender = true;
		else gender = false; 
	}
	
	public void killAgent(){
		agent = false; 
	}

}
