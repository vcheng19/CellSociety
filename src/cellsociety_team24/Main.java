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
        FileReader reader = new FileReader(new File("xml_files/gol.xml")); 
        s.setTitle(reader.readProperty("title"));
        GridInitializer gi= new GridInitializer(root, reader);
        gi.makeGrid();
        //Simulator sim = new Simulator();
        s.setScene(scene);
        s.show();
    }

    public static void main (String[] args) {
        launch(args);
    }
}
