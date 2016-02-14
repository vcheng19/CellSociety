package cellclasses;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class ForagingAntCell extends Cell{
	
	private String type; 
	private boolean antHasFood; 
	private int foodPh; 
	private int homePh; 
	private int numAnts; 
	private boolean hasEaten; 
	
	public ForagingAntCell(int x, int y) {
		super(x, y);
		setColor(Color.BEIGE);
		numAnts = 0; 
		hasEaten = false; 
	}
	
	public void addAnt() { 
		numAnts++; 
	}
	
	public int getAnts() { 
		return numAnts; 
	}
	
	public void makeAnt() { 
		type = "ant"; 
		setColor(Color.BLACK);
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
	
	public boolean hasFood() { 
		return hasEaten; 
	}
	
	public void setHasEaten(boolean eaten) { 
		hasEaten = eaten; 
	}

}
