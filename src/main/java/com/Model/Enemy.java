package com.Model;

public class Enemy extends GameObject{
	private double HP;

	public Enemy(double x, double y, double width, double height, double HP){
		super(x, y, width, height);
		this.HP=HP;
	}

	public double getHP(){ return HP;}
	public void setHP(double HP){
		this.HP=HP;
		if (HP<=0) setAlive(false);
	}
	public boolean isKilled(){ return HP<=0;}

	
}
