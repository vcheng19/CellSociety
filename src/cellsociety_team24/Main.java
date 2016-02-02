package cellsociety_team24;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jdk.internal.org.xml.sax.XMLReader;

public class Main extends Application {
    public static final int SIZE = 400;
    @Override
    public void start (Stage s) {
        s.setTitle("Cell Society");
        Group root = new Group();
        Scene scene = new Scene(root, SIZE, SIZE, Color.BLACK);
        s.setScene(scene);
        s.show();
    }

    public static void main (String[] args) {
        launch(args);
    }
}
