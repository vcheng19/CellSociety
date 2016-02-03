package cellsociety_team24;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Simulator {
	Cell[][] myGrid;
	RuleEnforcer myRule;
	public static final int FRAMES_PER_SECOND = 60;
	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
   //one method: step
	
	public Simulator(Cell[][] grid, RuleEnforcer rule) {
		// TODO Auto-generated constructor stub
		myGrid = grid;
		myRule = rule;		
	}
	
	//calls changeState
	public void step(){
		myGrid = myRule.iterateGrid(myGrid);
	}
	
	public void start(){
		 KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step());
		 Timeline animation = new Timeline();
	     animation.setCycleCount(Timeline.INDEFINITE);
	     animation.getKeyFrames().add(frame);;
	     animation.play();
	}

}
