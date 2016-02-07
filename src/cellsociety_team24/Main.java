package cellsociety_team24;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	
    private final int WORLD_SIZE = 600;
    private final int UI_WIDTH = 200;
    
    @Override
    public void start (Stage s){
        Group root = new Group();
        Scene scene = new Scene(root, WORLD_SIZE + UI_WIDTH, WORLD_SIZE, Color.BLACK);
        Controls ui = new Controls(root, s);
        ui.drawInterface();
        s.setTitle("Cell Society");
        s.setScene(scene);
        s.show();
    }
    
    public static void main (String[] args) {
        launch(args);
    }
}
