package com.Model;

import javafx.scene.shape.Rectangle;

public class Boss extends Enemy{
	private static Boss instance = new Boss();
	public static Boss getInstance(){ return instance;}

	private static final double width=400, height=300;

	private Boss(){
		super(1060-width, 0, width, height, 50);
	}

	private double movingDirection=+2.5;

	private boolean canMove(){
		double y=view.getY()+movingDirection;
		if (y<0 || y+view.getHeight()>Game.HEIGHT) return false;
		return true;
	}
	public void randomMove(){
		if (!canMove()) movingDirection*=-1;
		view.setY(view.getY()+movingDirection);
	}
	
	public Rectangle getCollisionView(){
		Rectangle res = new Rectangle(getX()+100, getY()+20, getWidth()-100, getHeight()-40);
		return res;
	}

	


}
