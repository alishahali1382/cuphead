package com.Transitions;

import com.App;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class BombBulletTransition extends Transition{
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
		imagePatterns = new ImagePattern[7];
		for (int i=0; i<7; i++){
			String filename="assets/BombBullet/mm_schmup_bomb_bullet_"+String.format("%04d", i+1)+".png";
			imagePatterns[i] = loadImage(filename);
		}
	}

	private Rectangle rectangle;

	public BombBulletTransition(Rectangle rectangle){
		this.rectangle=rectangle;
		setCycleDuration(Duration.millis(350));
		setCycleCount(10); // this should be finite for the garbage collector to remove it
		setInterpolator(Interpolator.LINEAR);

		loadAllImages();
	}

	@Override
	protected void interpolate(double arg0) {
		int val=((int) Math.floor(arg0*7))%7;
		rectangle.setFill(imagePatterns[val]);
	}
	
}
