package com.Transitions;

import com.App;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class BossFlyTransition extends Transition{
	private static ImagePattern imagePatterns[];
	
	private static ImagePattern loadImage(String filename){
		try {
			return new ImagePattern(new Image(App.getURL(filename).toExternalForm()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	private static void loadAllImages(){
		if (imagePatterns!=null) return ;
		imagePatterns = new ImagePattern[6];
		for (int i=0; i<6; i++){
			String filename="assets/BossFly/"+(i+1)+".png";
			imagePatterns[i] = loadImage(filename);
		}
	}

	private Rectangle rectangle;

	public BossFlyTransition(Rectangle rectangle){
		this.rectangle=rectangle;
		setCycleDuration(Duration.millis(500));
		setCycleCount(INDEFINITE);
		setInterpolator(Interpolator.LINEAR);
		setAutoReverse(true);

		loadAllImages();
	}

	@Override
	protected void interpolate(double arg0) {
		int val=((int) Math.floor(arg0*6))%6;
		rectangle.setFill(imagePatterns[val]);
	}
	
}
