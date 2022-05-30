package com.Model;

import com.View.GameViewController;

import javafx.scene.shape.Rectangle;

public class Boss extends Enemy{
	private static Boss instance;
	public static void makeNewBoss(){ instance = new Boss();}
	public static Boss getInstance(){ return instance;}
	
	private static double maxHP=100; // TODO: set a logical value
	public static void setMaxHP(double HP){ maxHP = HP;}
	
	
	private static final double width=400, height=300;



	private Boss(){
		super(1060-width, 0, width, height, maxHP);
		System.out.println(getHP());
		System.out.println(maxHP);
	}

	private double movingDirection=+5;

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
		Rectangle res = new Rectangle(getX()+90, getY()+30, getWidth()-100, getHeight()-60);
		return res;
	}

	@Override
	public void setHP(double HP){
		super.setHP(HP);
		GameViewController.getInstance().setBossHP(HP/maxHP);
	}

	public void damage(double val){
		setHP(getHP()-val);
	}


}
