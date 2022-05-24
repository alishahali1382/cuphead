package com.Model;

import javafx.scene.shape.Rectangle;

public class Boss{
	private static Boss instance = new Boss();
	public static Boss getInstance(){ return instance;}

	private static final double width=400, height=300;

	private Rectangle view;

	private Boss(){
		view = new Rectangle(1060-width, 0, width, height);
	}

	public Rectangle getView(){ return view;}

	private int HP=50;
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


	public int getHP(){ return HP;}
	public void setHP(int HP){ this.HP=HP;}




}
