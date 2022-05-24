package com.Transitions;

import com.App;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class PlaneFlyTransition extends Transition{
	
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
		imagePatterns = new ImagePattern[3][4];
		for (int i=0; i<4; i++){
			String filename="assets/PlaneFly/mugman_plane_idle_up_"+String.format("%04d", i+1)+".png";
			imagePatterns[0][i] = loadImage(filename);
			
			filename="assets/PlaneFly/mugman_plane_idle_straight_"+String.format("%04d", i+1)+".png";
			imagePatterns[1][i] = loadImage(filename);
			
			filename="assets/PlaneFly/mugman_plane_idle_down_"+String.format("%04d", i+1)+".png";
			imagePatterns[2][i] = loadImage(filename);
		}
	}

	private Rectangle rectangle;

	public PlaneFlyTransition(Rectangle rectangle){
		this.rectangle=rectangle;
		setCycleDuration(Duration.millis(350));
		setCycleCount(INDEFINITE);
		setInterpolator(Interpolator.LINEAR);

		loadAllImages();
	}

	@Override
	protected void interpolate(double arg0) {
		int val=((int) Math.floor(arg0*4))%4;
		
		int movementH=0;
		if (App.isKeyActive(KeyCode.W)) movementH--;
		if (App.isKeyActive(KeyCode.S)) movementH++;
		
		rectangle.setFill(imagePatterns[movementH+1][val]);
		
		// TODO: optimize here so that we dont reload images every frame
	}
	
}
