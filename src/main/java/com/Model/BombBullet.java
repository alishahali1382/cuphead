package com.Model;

import com.App;
import com.Transitions.BombBulletTransition;

import javafx.scene.paint.ImagePattern;

public class BombBullet extends GameObject{
	private static ImagePattern bulletImage = new ImagePattern(App.loadImage("assets/BombBullet/mm_schmup_bomb_bullet_0001.png"));
	private double vx=2, vy=3, g=0.3;
	private BombBulletTransition transition;
	private boolean hit;

	public BombBullet(double x, double y){
		super(x, y, 25, 28);
		view.setFill(bulletImage);
		transition = new BombBulletTransition(this.getView());
		transition.play();
	}

	public void move(){
		view.setX(view.getX()+vx);
		view.setY(view.getY()+vy);
		vy+=g;
		if (view.getX()>Game.WIDTH) setAlive(false);
		if (view.getY()>Game.HEIGHT) setAlive(false);
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
