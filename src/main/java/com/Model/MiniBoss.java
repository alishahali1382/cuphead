package com.Model;

import com.Transitions.MiniBossFlyTransition;
import com.View.GamePage;

public class MiniBoss extends Enemy{
	private static final double width=100, height=80;
	private MiniBossFlyTransition transition;

	public MiniBoss(double x, double y){
		super(x, y, width, height, 2);
		transition = new MiniBossFlyTransition(getView());
		transition.play();
	}

	public void move(){
		view.setX(view.getX()-3);
		if (getX()<=-width) setAlive(false);
	}
	public void kill(){
		transition.stop();
		GamePage.getInstance().removeGameObject(this);
	}
	

}
