package com.Model;

public class Enemy extends GameObject{
	private int HP;

	public Enemy(double x, double y, double width, double height, int HP){
		super(x, y, width, height);
		this.HP=HP;
	}

	public int getHP(){ return HP;}
	public void setHP(int HP){
		this.HP=HP;
		if (HP<=0) setAlive(false);
	}
	public boolean isKilled(){ return HP<=0;}

	
}
