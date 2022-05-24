package com.Model;

import javafx.scene.shape.Rectangle;

public abstract class GameObject {
	public final static double GameW=Game.WIDTH, GameH=Game.HEIGHT;

	protected Rectangle view;
	private boolean alive;

	public GameObject(double x, double y, double width, double height){
		view = new Rectangle(x, y, width, height);
		alive = true;
	}

	public Rectangle getView(){ return view;}

	public boolean isAlive(){ return alive;}
	public boolean isDead(){ return !alive;}
	public void setAlive(boolean alive){ this.alive=alive;}
	
	public boolean intersects(GameObject object){
		return this.view.getBoundsInParent().intersects(object.getView().getBoundsInParent());
	}

	public double getX(){ return view.getX();}
	public double getY(){ return view.getY();}
	public double getWidth(){ return view.getWidth();}
	public double getHeight(){ return view.getHeight();}


}
