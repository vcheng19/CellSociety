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
	
    private final int WORLD_SIZE = 600;
    
    @Override
    public void start (Stage s) throws SAXException, IOException, ParserConfigurationException {
        Group root = new Group();
        Scene scene = new Scene(root, WORLD_SIZE + 200, WORLD_SIZE, Color.BLACK);
        UserInterface ui = new UserInterface(root, s);
        ui.drawInterface();
        s.setTitle("Cell Society");
        s.setScene(scene);
        s.show();
    }
    
    public static void main (String[] args) {
        launch(args);
    }
}
