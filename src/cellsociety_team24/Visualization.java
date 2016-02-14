package cellsociety_team24;

import javafx.scene.Group;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Visualization {
	Group graph = new Group();

	public Visualization(Group root) {
		root.getChildren().add(graph);
	}
	
	public void drawVis() { 
		Rectangle rect = new Rectangle(0, 600, 800, 150);
		rect.setFill(Color.BEIGE);
		graph.getChildren().add(rect);
	}
	
	public void move() { 
	    final NumberAxis xAxis = new NumberAxis();
	    final NumberAxis yAxis = new NumberAxis();
	    xAxis.setLabel("Generations");
	    final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
	    lineChart.setTitle("Populations Over Time");
	    XYChart.Series<Number, Number> series_a = new XYChart.Series<Number, Number>();
	    XYChart.Series<Number, Number> series_b = new XYChart.Series<Number, Number>();
	    series_a.setName("My Data");
	    series_a.getData().add(new XYChart.Data<Number, Number>(1, 23));
	    series_a.getData().add(new XYChart.Data<Number, Number>(2, 114));
	    series_b.getData().add(new XYChart.Data<Number, Number>(1, 15));
	    series_b.getData().add(new XYChart.Data<Number, Number>(2, 124));
	    graph.getChildren().add(lineChart);
	    lineChart.getData().add(series_a);
	    lineChart.getData().add(series_b);
	}
}
