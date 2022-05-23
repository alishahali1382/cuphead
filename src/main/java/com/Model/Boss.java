package com.Model;

import com.View.GamePage;

import javafx.scene.shape.Rectangle;

public class Boss extends Rectangle{
	private static Boss instance = new Boss();
	public static Boss getInstance(){ return instance;}

	private static final double width=400, height=300;

	private Boss(){
		super(1060-width, 0, width, height);

	}

	private int HP=50;
	private double movingDirection=+2.5;

	private boolean canMove(){
		double y=getY()+movingDirection;
		if (y<0 || y+getHeight()>Game.HEIGHT) return false;
		return true;
	}
	public void randomMove(){
		if (!canMove()) movingDirection*=-1;
		setY(getY()+movingDirection);
	}


	public int getHP(){ return HP;}
	public void setHP(int HP){ this.HP=HP;}




}
