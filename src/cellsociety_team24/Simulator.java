package cellsociety_team24;

import cellclasses.Cell;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import ruleEnforcers.FireRuleEnforcer;
import ruleEnforcers.RuleEnforcer;

public class Simulator {
	Cell[][] myGrid;
	RuleEnforcer myRule;
	public static double FRAMES_PER_SECOND = 2;
	private static final double MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private boolean isPlaying;
	private Timeline animation = new Timeline();
	private KeyFrame frame;
	
	public Simulator(Cell[][] grid, RuleEnforcer rule) {
		myGrid = grid;
		myRule = rule;		
	}

	public void step(){
		if(isPlaying){
			myRule.iterateGrid();
		}
	}
	
	public void start(){
		 frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step());
	     animation.setCycleCount(Timeline.INDEFINITE);
	     animation.getKeyFrames().add(frame);
	     animation.play();
		 isPlaying = true;
	}

	public void adjustSpeed(double ratio){
		animation.setRate(ratio);
	}
	
	public void stop(){
		isPlaying = false;
	}
	
	public void resume(){
		isPlaying = true;
	}
	
	public void byStep(){
		stop();
		if(!isPlaying){
			myRule.iterateGrid();
		}
	}
	
	public void retrieveGrid() { 
		System.out.println(ash(myRule));
	}
	
	public boolean ash(RuleEnforcer re) { 
		System.out.println("it is what it is");
		return FireRuleEnforcer.class.isInstance(re);
	}
}
