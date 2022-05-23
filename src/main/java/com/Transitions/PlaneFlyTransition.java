package com.Transitions;

import com.App;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class PlaneFlyTransition extends Transition{
	private Rectangle rectangle;

	public PlaneFlyTransition(Rectangle rectangle){
		this.rectangle=rectangle;
		setCycleDuration(Duration.millis(350));
		setCycleCount(INDEFINITE);
		setInterpolator(Interpolator.LINEAR);
	}

	@Override
	protected void interpolate(double arg0) {
		int val=((int) Math.floor(arg0*4))%4;
		String filename="assets/PlaneFly/mugman_plane_idle_straight_"+String.format("%04d", val+1)+".png";
		rectangle.setFill(new ImagePattern(new Image(App.getURL(filename).toExternalForm())));
		// TODO: optimize here so that we dont reload images every frame
	}
	
}
