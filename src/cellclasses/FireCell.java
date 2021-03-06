package cellclasses;

import javafx.scene.paint.Color;

public class FireCell extends Cell {
	private int cellType;
	private static final int FIRE_CONSTANT = 1;
	private static final int TREE_CONSTANT = 2;
	
	public FireCell(int x, int y){
		super(x,y);
	}
	
	public void makeTree(){
		setColor(Color.GREEN);
		cellType = TREE_CONSTANT;
	}
	
	public void makeEmpty(){
		setColor(Color.YELLOW);
		cellType = 0;
	}
	
	public void makeFire(){
		setColor(Color.RED);
		cellType = FIRE_CONSTANT;
	}
	
	public int getCellType(){
		return cellType;
	}
	
	public boolean isFire(){
		return cellType == FIRE_CONSTANT;
	}
	
	public boolean isTree(){
		return cellType == TREE_CONSTANT;
	}
}
