package cellsociety_team24;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UserInterface {
	
	Simulator mySimulator; 
	private Button chooseFile; 
	private Button pause; 
	private Button play; 
	private Button step;
	private Group root;
	private Group ui = new Group();
	private final int UIStartX = 600; 
	Stage myStage;
	private FileReader reader; 
	
	public UserInterface(Group root, Stage s) {
		this.root = root;
		root.getChildren().add(ui);
		myStage = s; 
	}
	
	void drawInterface() { 
		Rectangle rect = new Rectangle(600, 0, 200, 600);
		rect.setFill(Color.GREY);
		ui.getChildren().add(rect);
		addButtons();
	}
	
	void addButtons() { 
		chooseFile = makeButton("Choose file", event -> openFileChooser(), 30, 40);
		play = makeButton("Play", event -> mySimulator.start(), 30, 100);
		pause = makeButton("Pause", event -> mySimulator.stop(), 30, 150);	
		step = makeButton("Step", event -> mySimulator.byStep(), 30, 200);
	}
	
	void openFileChooser() { 
		FileChooser fc = new FileChooser(); 
		File selectedFile = fc.showOpenDialog(myStage);
		try {
			reader = new FileReader(selectedFile);
			mySimulator = makeSim(root, reader);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			System.out.println(e.getMessage());
		}
	}
	
    public Simulator makeSim(Group gr, FileReader reader) { 
        GridInitializer gi;
        RuleEnforcer rule;
        String sim_type = reader.readProperty("sim_type");
        switch(sim_type) { 
        	case "Game of life":
        		gi = new GOLGridInitializer(gr, reader);
        		gi.makeGrid();
        		rule = new GOLRuleEnforcer(gi.getGrid());
        		break;
        	case "Segregation":
        		gi = new SegregationGridInitializer(gr,reader);
        		gi.makeGrid();
        		rule = new SegregationRuleEnforcer(gi.getGrid(), Integer.parseInt(reader.readProperty("percentage")));
        		break;
        	default: 
        		gi = new GOLGridInitializer(gr, reader);
        		gi.makeGrid();
        		rule = new GOLRuleEnforcer(gi.getGrid());
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
    
    FileReader getFileInfo() { 
    	return reader;
    }
}
