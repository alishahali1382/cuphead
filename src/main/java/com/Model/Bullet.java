package com.Model;

import com.App;
import com.Transitions.BulletTransition;

import javafx.scene.paint.ImagePattern;

public class Bullet extends GameObject{
	private static ImagePattern bulletImage = new ImagePattern(App.loadImage("assets/Bullet/mm_schmup_peashot_bullet_A_0001.png"));
	private double vx=15;
	private BulletTransition transition;
	private boolean hit;

	public Bullet(double x, double y){
		super(x, y, 70, 11);
		view.setFill(bulletImage);
		transition = new BulletTransition(this.getView());
		transition.play();
	}

	public void move(){
		// TODO: check if game is paused
		view.setX(view.getX()+vx);
		if (view.getX()>Game.WIDTH) setAlive(false);
	}

	@Override
	public void setAlive(boolean alive){
		super.setAlive(alive);
		if (!alive){
			transition.stop();
		}
	}
	
	public void setHit(){ hit=true;}
	public boolean getHit(){ return hit;}

	
}
