package cellclasses;

import javafx.scene.Group;

public class SugarScapeCell extends Cell{
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
		// TODO Auto-generated constructor stub
	}
	
}
