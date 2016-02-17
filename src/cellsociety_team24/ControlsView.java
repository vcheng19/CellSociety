package cellsociety_team24;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ControlsView {
	private Button chooseFile; 
	private Button pause; 
	private Button play; 
	private Button step;
	private Button reset;
	private Button saveGrid; 
	private Slider slider; 
	private Group ui = new Group();
	private final int UIStartX = 600; 
	private final int playPauseStepY = 100;
	ComboBox<String> shapeChooser = new ComboBox<String>(); 
	ComboBox<String> edgeChooser = new ComboBox<String>();

	private final String[] shapes = { "Square", "Triangle", "Hexagon"};
	private final String[] edges = {"finite", "toroidal"}; 
	private ControlsModel myModel; 
	
	public ControlsView(Group root, ControlsModel model) {
		root.getChildren().add(ui);
		myModel = model;
	}
	
	void drawInterface() { 
		Rectangle rect = new Rectangle(UIStartX, 0, 200, UIStartX);
		rect.setFill(Color.GREY);
		ui.getChildren().add(rect);
		addButtons();
		addTimeSlider();
		setComboBox(shapeChooser, shapes, 40, 300);
		setComboBox(edgeChooser, edges, 40, 375);
        shapeChooser.setOnAction(event -> myModel.selectShape(shapeChooser.getSelectionModel().getSelectedItem()));
        edgeChooser.setOnAction(event -> myModel.selectEdge(edgeChooser.getSelectionModel().getSelectedItem()));
	}
	
	void setComboBox(ComboBox<String> chooser, String[] elements, int x, int y) { 
		chooser = new ComboBox<String>(); 
		chooser.getItems().addAll(elements);
		chooser.setLayoutX(UIStartX + x); 
		chooser.setLayoutY(y);
		ui.getChildren().add(chooser);
	}
	
	
	void addButtons() { 
		chooseFile = makeButton("Choose file", event -> myModel.openFileChooser(), 40, 40);
		play = makeButton("Play", event -> myModel.pressButton("Play"), 20, playPauseStepY);
		pause = makeButton("Pause", event -> myModel.pressButton("Pause"), 80, playPauseStepY);	
		step = makeButton("Step", event -> myModel.pressButton("Step"), 140, playPauseStepY);
		reset =  makeButton("Reset to config", event -> myModel.pressButton("Reset"), 50, playPauseStepY + 60);
		saveGrid =  makeButton("Save Config in XML", event -> myModel.saveGridXML(), 30, playPauseStepY + 150);
	}
	
	void addTimeSlider() { 
		slider = new Slider(0, 4, 1);
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		slider.setMajorTickUnit(0.5f);
		slider.setBlockIncrement(0.5f);
		slider.setLayoutX(UIStartX + 30); 
		slider.setLayoutY(450);
		ui.getChildren().add(slider);
		slider.setOnMouseClicked(event -> myModel.changeSimSpeed(slider.getValue()));
	}
	
    private Button makeButton (String label, EventHandler<ActionEvent> handler, int x, int y) {
        Button button = new Button();
        button.setText(label);
        button.setOnAction(handler);
        button.setLayoutX(UIStartX + x);
        button.setLayoutY(y);
        ui.getChildren().add(button);
        return button;
    }
}
