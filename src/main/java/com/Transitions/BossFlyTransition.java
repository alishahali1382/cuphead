package com.Transitions;

import com.App;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class BossFlyTransition extends Transition{
	private Rectangle rectangle;

	public BossFlyTransition(Rectangle rectangle){
		this.rectangle=rectangle;
		setCycleDuration(Duration.millis(500));
		setCycleCount(INDEFINITE);
		setInterpolator(Interpolator.LINEAR);
		setAutoReverse(true);
	}

	@Override
	protected void interpolate(double arg0) {
		int val=((int) Math.floor(arg0*6))%6;
		String filename="assets/BossFly/"+(val+1)+".png";
		rectangle.setFill(new ImagePattern(new Image(App.getURL(filename).toExternalForm())));
		// TODO: optimize here so that we dont reload images every frame
	}
	
}
