package com.Model;

import javafx.scene.shape.Rectangle;

public class Bullet extends Rectangle{
	private double vx=5;
	private boolean alive = true;

	public Bullet(double x, double y){
		super(x, y, 100, 20);
	}

	public boolean isAlive(){ return alive;}
	public boolean isDead(){ return !alive;}
	public void setAlive(boolean alive){ this.alive=alive;}

	public void move(){
		// TODO: check if game is paused
		setTranslateX(getX()+vx);
		if (getX()>Game.WIDTH) alive=false;
	}

	
}
