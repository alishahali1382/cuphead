package com.Transitions;

import com.App;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class MiniBossFlyTransition extends Transition{
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
		imagePatterns = new ImagePattern[16];
		for (int i=0; i<16; i++){
			String filename="assets/Flappy Birds/Fly/flappy_bird_fly_"+String.format("%04d", i+1)+".png";
			imagePatterns[i] = loadImage(filename);
		}
	}

	private Rectangle rectangle;

	public MiniBossFlyTransition(Rectangle rectangle){
		this.rectangle=rectangle;
		setCycleDuration(Duration.millis(800));
		setCycleCount(INDEFINITE);
		setInterpolator(Interpolator.LINEAR);
		// setAutoReverse(true);
		// TODO: stop animation on object death
		loadAllImages();
	}

	@Override
	protected void interpolate(double arg0) {
		int val=((int) Math.floor(arg0*16))%16;
		rectangle.setFill(imagePatterns[val]);
	}
	
}
