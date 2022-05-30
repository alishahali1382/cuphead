package com.Model;

import com.App;
import com.View.GamePage;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class EggBullet extends GameObject{
	private static EggBullet instance;
	public static void setInstance(EggBullet eggBullet){ instance=eggBullet;}
	public static EggBullet getInstance(){ return instance;}
	
	private static ImagePattern imagePattern;
	private static ImagePattern loadImage(String filename){
		try {
			return new ImagePattern(new Image(App.getURL(filename).toExternalForm()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public static final double width=90, height=70;
	private static final double vx=-5;

	public EggBullet(double x, double y) {
		super(x, y, width, height);
		if (imagePattern==null) imagePattern=loadImage("assets/egg.png");
		view.setFill(imagePattern);
	}

	public void move(){
		view.setX(getX()+vx);
		if (getX()+width<0) setAlive(false);
		view.setRotate(view.getRotate()-1);
	}

	@Override
	public void setAlive(boolean alive){
		super.setAlive(alive);
		if (!alive){
			GamePage.getInstance().removeGameObject(this);
			instance=null;
		}
	}
	
}
