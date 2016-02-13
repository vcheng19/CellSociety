package cellclasses;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class ForagingAntCell extends Cell{
	
	String type; 
	boolean antHasFood; 
	private int foodPh; 
	private int homePh; 
	private int numAnts; 
	
	public ForagingAntCell(Group root, double size, int x, int y) {
		super(root, size, x, y);
		setColor(Color.BEIGE);
		numAnts = 0; 
	}
	
	public void addAnt() { 
		type = "ant"; 
		numAnts++; 
	}
	
	public void makeFloor() { 
		type = "floor"; 
		setColor(Color.BEIGE);
		numAnts = 0; 
	}
	
	public void makeNest(int n) { 
		type = "nest";
		numAnts = n; 
		setColor(Color.BROWN);
	}
	
	public void makeFood() { 
		type = "food";
		setColor(Color.RED);
	}
	
	public boolean isType(String typ) { 
		return type == typ;
	}
	
	public boolean isAtFoodOrNest(int x, int y) { 
		return (getX() == x && getY() == y);
	}
	
	public void updateFoodPh(int n) { 
		foodPh = 0; 
	}
	
	public int getFoodPh() { 
		return foodPh; 
	}
	
	public void updateHomePh(int n) { 
		homePh = n; 
	}
	
	public int getHomePh() { 
		return homePh; 
	}
	
	

}
