package cellsociety_team24;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	
    private final int WORLD_SIZE = 800;
    
    @Override
    public void start (Stage s) throws SAXException, IOException, ParserConfigurationException {
        Group root = new Group();
        Scene scene = new Scene(root, WORLD_SIZE, WORLD_SIZE, Color.BLACK);
        FileReader reader = new FileReader(new File("xml_files/gol.xml")); 
        Simulator sim = makeSim(root, reader);
        sim.start();
        s.setTitle(reader.readProperty("title"));
        s.setScene(scene);
        s.show();
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
        	default: 
        		gi = new GOLGridInitializer(gr, reader);
        		gi.makeGrid();
        		rule = new GOLRuleEnforcer(gi.getGrid());
        }
        return new Simulator(gi.getGrid(), rule);
    }
    
    public static void main (String[] args) {
        launch(args);
    }
}
