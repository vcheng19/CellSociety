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
    public static final int WORLD_SIZE = 800;
    @Override
    public void start (Stage s) throws SAXException, IOException, ParserConfigurationException {
        Group root = new Group();
        Scene scene = new Scene(root, WORLD_SIZE, WORLD_SIZE, Color.BLACK);
        FileReader reader = new FileReader(new File("xml_files/test.xml")); 
        s.setTitle(reader.readProperty("title"));
        GridInitializer gi= new GridInitializer(root, reader);
        gi.makeGrid();
        RuleEnforcer rule;
        String sim_type = reader.readProperty("sim_type");
        switch(sim_type) { 
        	case "Game of Life":
        		rule = new GOLRuleEnforcer(gi.getGrid());
        	default: 
        		rule = new GOLRuleEnforcer(gi.getGrid());
        }
        Simulator sim = new Simulator(gi.getGrid(), rule);
        sim.start();
        s.setScene(scene);
        s.show();
    }
    

    public static void main (String[] args) {
        launch(args);
    }
}
