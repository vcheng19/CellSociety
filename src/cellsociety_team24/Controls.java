package cellsociety_team24;

import java.io.File;
import cellclasses.Cell;
import filereadcheck.FileReader;
import gridinitializers.FireGridInitializer;
import gridinitializers.ForagingAntGridInitializer;
import gridinitializers.GOLGridInitializer;
import gridinitializers.GridInitializer;
import gridinitializers.SegregationGridInitializer;
import gridinitializers.WaTorGridInitializer;
import information.FireInformation;
import information.ForagingAntsInformation;
import information.GOLInformation;
import information.SegregationInformation;
import information.SimulationInformation;
import information.WaTorInformation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ruleEnforcers.FireRuleEnforcer;
import ruleEnforcers.ForagingAntRuleEnforcer;
import ruleEnforcers.GOLRuleEnforcer;
import ruleEnforcers.RuleEnforcer;
import ruleEnforcers.SegregationRuleEnforcer;
import ruleEnforcers.WaTorRuleEnforcer;

public class Controls {
	
	Simulator mySimulator; 
	private Button chooseFile; 
	private Button pause; 
	private Button play; 
	private Button step;
	private Button reset;
	private Button saveGrid; 
	private Slider slider; 
	private Group root;
	private Group ui = new Group();
	private final int UIStartX = 600; 
	private final int playPauseStepY = 100;
	Stage myStage;
	private FileReader reader; 
	
	public Controls(Group root, Stage s) {
		this.root = root;
		root.getChildren().add(ui);
		myStage = s; 
	}
	
	void drawInterface() { 
		Rectangle rect = new Rectangle(UIStartX, 0, 200, UIStartX);
		rect.setFill(Color.GREY);
		ui.getChildren().add(rect);
		addButtons();
		addTimeSlider();
	}
	
	void addButtons() { 
		chooseFile = makeButton("Choose file", event -> openFileChooser(), 40, 40);
		play = makeButton("Play", event -> tryButton("Play"), 20, playPauseStepY);
		pause = makeButton("Pause", event -> tryButton("Pause"), 80, playPauseStepY);	
		step = makeButton("Step", event -> tryButton("Step"), 140, playPauseStepY);
		reset =  makeButton("Reset to config", event -> tryButton("Reset"), 50, playPauseStepY + 60);
		saveGrid =  makeButton("Save Config in XML", event -> saveGridXML(), 30, playPauseStepY + 150);
	}
	
	void tryButton(String action) { 
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
					mySimulator = makeSim(root, reader);
					break;
				default:
			}
		} catch (Exception e) {
			System.out.println("No simulation loaded yet.");
		}
	}
	
	void saveGridXML() { 
		mySimulator.stop();
		Cell[][] curGrid = mySimulator.retrieveGrid();
		writeInfoToFile(reader.readProperty("sim_type"), curGrid);
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
		info.writeToXML(reader.getWriter());
	}
	
	void addTimeSlider() { 
		slider = new Slider(0, 4, 1);
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		slider.setMajorTickUnit(0.5f);
		slider.setBlockIncrement(0.5f);
		ui.getChildren().add(slider);
		slider.setLayoutX(UIStartX + 30);
		slider.setLayoutY(400);
		slider.setOnMouseClicked(event -> changeSimSpeed(slider.getValue()));
	}
	
	void changeSimSpeed(double x) { 
		try {
			mySimulator.adjustSpeed(x);
		} catch (Exception e) { 
			System.out.println("No simulation started");
		}
	}
	
	void openFileChooser() { 
		FileChooser fc = new FileChooser(); 
		File selectedFile = fc.showOpenDialog(myStage);
		try {
			reader = new FileReader(selectedFile);
		} catch (Exception e) {
			System.out.println("No file selected");
		}
		generateSim();
	}
	
	private void generateSim() { 
		reader.validateSim();
		mySimulator = makeSim(root, reader);
	}
	
    public Simulator makeSim(Group gr, FileReader reader) { 
        GridInitializer gi;
        RuleEnforcer rule;
        String sim_type = reader.readProperty("sim_type");
        switch(sim_type) { 
        	case "Game of life":
        		gi = new GOLGridInitializer(gr, reader);
        		rule = new GOLRuleEnforcer(gi.getGrid(), reader);
        		break;
        	case "Segregation":
        		gi = new SegregationGridInitializer(gr,reader);
        		rule = new SegregationRuleEnforcer(gi.getGrid(), reader);
        		break;
        	case "Fire":
        		gi = new FireGridInitializer(gr,reader);
        		rule = new FireRuleEnforcer(gi.getGrid(), reader);
        		break;
        	case "WaTor":
        		gi = new WaTorGridInitializer(gr, reader);
        		rule = new WaTorRuleEnforcer(gi.getGrid(), reader);
        		break;
        	case "Foraging Ant": 
        		gi = new ForagingAntGridInitializer(gr, reader); 
        		rule = new ForagingAntRuleEnforcer(gi.getGrid(), reader); 
        		break;
        	default: 
        		gi = new GOLGridInitializer(gr, reader);
        		rule = new GOLRuleEnforcer(gi.getGrid(), reader);
        }
        return new Simulator(gi.getGrid(), rule);
    }
	
    private Button makeButton (String label, EventHandler<ActionEvent> handler, int x, int y) {
        Button result = new Button();
        result.setText(label);
        result.setOnAction(handler);
        result.setLayoutX(UIStartX + x);
        result.setLayoutY(y);
        ui.getChildren().add(result);
        return result;
    }
}
