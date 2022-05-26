package com.Transitions;

import com.App;
import com.View.GamePage;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class ExplosionTransition extends Transition{
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
		imagePatterns = new ImagePattern[12];
		for (int i=0; i<12; i++){
			String filename="assets/Hit Dust/birdhouse_cannon_dust_"+String.format("%04d", i+1)+".png";
			imagePatterns[i] = loadImage(filename);
		}
	}

	private Rectangle rectangle;

	public ExplosionTransition(Rectangle rectangle){
		loadAllImages();



		this.rectangle=rectangle;
		rectangle.setFill(imagePatterns[0]);
		GamePage.getInstance().addRectangle(rectangle);
		
		setCycleDuration(Duration.millis(650));
		setCycleCount(1);
		setInterpolator(Interpolator.LINEAR);
		setOnFinished(e -> {
			GamePage.getInstance().removeRectangle(rectangle);
		});;
		
	}

	@Override
	protected void interpolate(double arg0) {
		int val=((int) Math.floor(arg0*12))%12;
		rectangle.setFill(imagePatterns[val]);
	}
	
}
