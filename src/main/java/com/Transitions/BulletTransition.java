package com.Transitions;

import java.util.Random;

import com.App;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class BulletTransition extends Transition{
	private static Random random = new Random(System.currentTimeMillis());
	private static ImagePattern imagePatterns[][];
	
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
		imagePatterns = new ImagePattern[3][3];
		for (int i=0; i<3; i++){
			// mm_schmup_peashot_bullet_A_0001.png
			String filename="assets/Bullet/mm_schmup_peashot_bullet_A_"+String.format("%04d", i+1)+".png";
			imagePatterns[0][i] = loadImage(filename);
			
			filename="assets/Bullet/mm_schmup_peashot_bullet_B_"+String.format("%04d", i+1)+".png";
			imagePatterns[1][i] = loadImage(filename);
			
			filename="assets/Bullet/mm_schmup_peashot_bullet_C_"+String.format("%04d", i+1)+".png";
			imagePatterns[2][i] = loadImage(filename);
		}
	}

	private Rectangle rectangle;
	private int bulletType;

	public BulletTransition(Rectangle rectangle){
		this.rectangle=rectangle;
		setCycleDuration(Duration.millis(350));
		setCycleCount(10); // this should be finite for the garbage collector to remove it
		setInterpolator(Interpolator.LINEAR);
		bulletType=random.nextInt(3);

		loadAllImages();
	}

	@Override
	protected void interpolate(double arg0) {
		int val=((int) Math.floor(arg0*3))%3;
		rectangle.setFill(imagePatterns[bulletType][val]);
	}
	
}
