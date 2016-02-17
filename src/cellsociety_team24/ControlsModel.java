//This entire file is part of my masterpiece.
//Carolyn Yao

package cellsociety_team24;

import java.io.File;
import java.util.ResourceBundle;

import cellclasses.Cell;
import filereadcheck.FileReader;
import filereadcheck.FileWriter;
import gridinitializers.FireGridInitializer;
import gridinitializers.ForagingAntGridInitializer;
import gridinitializers.GOLGridInitializer;
import gridinitializers.GridInitializer;
import gridinitializers.SegregationGridInitializer;
import gridinitializers.SlimeGridInitializer;
import gridinitializers.SugarScapeGridInitializer;
import gridinitializers.WaTorGridInitializer;
import information.FireInformation;
import information.ForagingAntsInformation;
import information.GOLInformation;
import information.SegregationInformation;
import information.SimulationInformation;
import information.WaTorInformation;
import javafx.scene.Group;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ControlsModel {
	private Simulator mySimulator; 
	Group root; 
	FileReader fileData;
	FileWriter writer;
	Stage myStage;
	final static String ERROR_RESOURCES = "resources/ErrorMsgs";
	static ResourceBundle myResources = ResourceBundle.getBundle(ERROR_RESOURCES); 
	
	public ControlsModel(Group g, Stage s) {
		root = g; 
		myStage = s;
	}
	
	void pressButton(String action) { 
		try { 
			switch(action) { 
				case "Play": 
					mySimulator.start();
					break;
				case "Pause": 
					mySimulator.stop();
					break;
				case "Step":
					mySimulator.byStep();
					break;
				case "Reset": 
					mySimulator.stop();
					mySimulator = makeSim(root);
					break;
				default:
			}
		} catch (Exception e) {
			printNoSimYet();
		}
	}
	
	void selectShape(String t) { 
		writer.editProperty("grid_shape", t);
	}
	void selectEdge(String t) { 
		writer.editProperty("edge_type", t);
	}
	
	void changeSimSpeed(double x) { 
		try {
			mySimulator.adjustSpeed(x);
		} catch (Exception e) { 
			printNoSimYet();
		}
	}
	
	void saveGridXML() { 
		mySimulator.stop();
		Cell[][] curGrid = mySimulator.retrieveGrid();
		writeInfoToFile(fileData.readProperty("sim_type"), curGrid);
	}
	
	void printNoSimYet() { 
		System.out.println(myResources.getString("NoSimLoaded"));
	}
	
	void openFileChooser() { 
		FileChooser fc = new FileChooser(); 
		File selectedFile = fc.showOpenDialog(myStage);
		try {
			fileData = new FileReader(selectedFile);
			
		} catch (Exception e) {
			System.out.println(myResources.getString("NoFileSelected"));
		}
		generateSim();
	}
	
	private void generateSim() { 
		// will correct information, fill in empty grid_shape and edge_type to default
		fileData.validateSim();
		mySimulator = makeSim(root);
	}
	
    public Simulator makeSim(Group gr) { 
        GridInitializer gi = chooseSim(fileData, gr, chooseGridShape(fileData.readProperty("grid_shape")));
        return new Simulator(gi.getGrid(), gi.myRuleEnforcer);
    }
    
    public GridInitializer chooseSim(FileReader fileData, Group gr, Grid gridShape) { 
        switch(fileData.readProperty("sim_type")) { 
    	case "Game of life":
    		return new GOLGridInitializer(gridShape, gr, fileData);
    	case "Segregation":
    		return new SegregationGridInitializer(gridShape, gr, fileData);
    	case "Fire":
    		return new FireGridInitializer(gridShape, gr, fileData);
    	case "WaTor":
    		return new WaTorGridInitializer(gridShape, gr, fileData);
    	case "SugarScape":
    		return new SugarScapeGridInitializer(gridShape, gr, fileData);
    	case "Foraging Ant": 
    		return new ForagingAntGridInitializer(gridShape, gr, fileData); 
    	case "Slime":
    		return new SlimeGridInitializer(gridShape, gr, fileData); 
    	default: 
    		return new GOLGridInitializer(gridShape, gr, fileData);
        }
    }
    
	public void writeInfoToFile(String sim_type, Cell[][] grid) { 
		SimulationInformation info;
		switch(sim_type) { 
    	case "Game of life":
    		info = new GOLInformation(grid);
    		break;
    	case "Segregation":
    		info = new SegregationInformation(grid);
    		break;
    	case "Fire":
    		info = new FireInformation(grid);
    		break;
    	case "WaTor":
    		info = new WaTorInformation(grid);
    		break;
    	case "Foraging Ant": 
    		info = new ForagingAntsInformation(grid);
    		break;
    	default: 
    		info = new GOLInformation(grid);
		}
		info.populateData();
		info.writeToXML(fileData.getWriter());
	}
	
    private Grid chooseGridShape(String grid_shape){
    	Grid thisGrid;
    	switch(grid_shape){
    	case "Square":
    		thisGrid = new SquareGrid();
    		break;
    	case "Triangle":
    		thisGrid = new TriangleGrid();
    		break;
    	case "Hexagon":
    		thisGrid = new HexagonGrid();
    		break;
    	default:
    		thisGrid = new SquareGrid();
    	}
        return thisGrid;
    }
}
