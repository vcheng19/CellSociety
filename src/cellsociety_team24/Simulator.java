package cellsociety_team24;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Simulator {
	Cell[][] myGrid;
	RuleEnforcer myRule;
	public static final int FRAMES_PER_SECOND = 1;
	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
   //one method: step
	
	public Simulator(Cell[][] grid, RuleEnforcer rule) {
		myGrid = grid;
		myRule = rule;		
	}
	
	public void start(){
		 KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step());
		 Timeline animation = new Timeline();
	     animation.setCycleCount(Timeline.INDEFINITE);
	     animation.getKeyFrames().add(frame);;
	     animation.play();
	}
	
	//calls changeState
	public void step(){
		myRule.iterateGrid();
	}

}
