package cellsociety_team24;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Simulator {
	Cell[][] myGrid;
	RuleEnforcer myRule;
	public static final int FRAMES_PER_SECOND = 1;
	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private int time;
	private boolean isNotStopped;
	
	public Simulator(Cell[][] grid, RuleEnforcer rule) {
		myGrid = grid;
		myRule = rule;		
	}
	

	//calls changeState
	public void step(){
		if(isNotStopped){
			myRule.iterateGrid();
		}
	}
	
	public void start(){
		 time = MILLISECOND_DELAY;
		 isNotStopped = true;
		 KeyFrame frame = new KeyFrame(Duration.millis(time), e -> step());
		 Timeline animation = new Timeline();
	     animation.setCycleCount(Timeline.INDEFINITE);
	     animation.getKeyFrames().add(frame);;
	     animation.play();
	}
	

	public void speedUp(){
		time = time *2;
	}
	
	public void slowDown(){
		time = time/2;
	}
	
	public void stop(){
		isNotStopped = false;
	}
	
	public void resume(){
		isNotStopped = true;
	}

}
