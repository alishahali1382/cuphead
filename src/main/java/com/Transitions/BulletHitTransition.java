package com.Transitions;

import com.App;
import com.Model.Bullet;
import com.View.GamePage;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class BulletHitTransition extends Transition{
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
			String filename="assets/BulletHit/bird_maf_flap_feather_dust_trail_"+String.format("%04d", i+1)+".png";
			imagePatterns[i] = loadImage(filename);
		}
	}

	private static double len=30;
	private Rectangle rectangle;

	public BulletHitTransition(Bullet bullet){
		loadAllImages();

		double x=bullet.getX(), y=bullet.getY();
		double w=bullet.getWidth(), h=bullet.getHeight();
		x+=w-h;
		x+=h/2;
		y+=h/2;
		
		rectangle = new Rectangle(x-len/2, y-len/2, len, len);
		rectangle.setFill(imagePatterns[0]);
		GamePage.getInstance().addRectangle(rectangle);
		
		setCycleDuration(Duration.millis(350));
		setCycleCount(1);
		setInterpolator(Interpolator.LINEAR);
		setOnFinished(e -> {
			GamePage.getInstance().removeRectangle(rectangle);
		});;
		
	}

	@Override
	protected void interpolate(double arg0) {
		int val=((int) Math.floor(arg0*7))%7;
		rectangle.setFill(imagePatterns[val]);
	}
	
}
