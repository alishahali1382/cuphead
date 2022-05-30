package com.Model;

import com.Transitions.ExplosionTransition;
import com.View.GameViewController;

import javafx.scene.shape.Rectangle;

public class Plane extends GameObject{
	private static Plane instance;
	public static void setInstance(){ instance = new Plane();}
	public static Plane getInstance(){ return instance;}

	private static final long movementSpeed=350;  // pixel/second
	
	private static int maxHP=5;
	public void setMaxHP(int HP){ maxHP=HP;}

	private WeaponType weaponType = WeaponType.BULLET;
	private int HP;

	private Plane(){
		super(0, 0, 80, 65);
		setHP(maxHP);
	}

	public WeaponType getWeaponType(){ return weaponType;}

	public int getHP(){ return HP;}
	public void setHP(int HP){
		this.HP=HP;
		GameViewController.getInstance().setRemainingLives(HP);
	}
	
	public void weaponSwitch(){
		weaponType=(weaponType==WeaponType.BULLET?WeaponType.BOMB:WeaponType.BULLET);
		GameViewController.getInstance().setWeaponTypeRectangle();
	}

	private long lastAttackTime=0;
	public void attack(){
		long curr=System.currentTimeMillis();
		if (curr-lastAttackTime<weaponType.getDelayBetweenAttacks()) return ;
		lastAttackTime=curr;
		weaponType.attack(this);		
	}

	public void playExplosionAnimation(){
		double x=getX()+getWidth()/2, y=getY()+getHeight()/2, len=90;
		new ExplosionTransition(new Rectangle(x-len/2, y-len/2, len, len)).play();
	}




	// some shit stuff to seperate movement from thread
	private long lastPressTimeU=0;
	private long lastPressTimeL=0;
	private long lastPressTimeD=0;
	private long lastPressTimeR=0;

	public long getpressTimeDifference(long last){
		long diff=System.currentTimeMillis()-last;
		if (diff>200) return 0;
		return diff;
	}
	public void goUp(){
		long diff=getpressTimeDifference(lastPressTimeU);
		lastPressTimeU=System.currentTimeMillis();
		if (diff>0) diff=diff*movementSpeed/1000;
		else diff=15;
		
		view.setY(view.getY()-diff);
		if (view.getY()<0) view.setY(0);
	}
	public void goLeft(){
		long diff=getpressTimeDifference(lastPressTimeL);
		lastPressTimeL=System.currentTimeMillis();
		if (diff>0) diff=diff*movementSpeed/1000;
		else diff=15;
		
		view.setX(view.getX()-diff);
		if (view.getX()<0) view.setX(0);
	}
	public void goDown(){
		long diff=getpressTimeDifference(lastPressTimeD);
		lastPressTimeD=System.currentTimeMillis();
		if (diff>0) diff=diff*movementSpeed/1000;
		else diff=15;
		
		view.setY(view.getY()+diff);
		if (view.getY()>GameH-view.getHeight()) view.setY(GameH-view.getHeight());
	}
	public void goRight(){
		long diff=getpressTimeDifference(lastPressTimeR);
		lastPressTimeR=System.currentTimeMillis();
		if (diff>0) diff=diff*movementSpeed/1000;
		else diff=15;
		
		view.setX(view.getX()+diff);
		if (view.getX()>GameW-view.getWidth()) view.setX(GameW-view.getWidth());
	}
	// -------------------------------------------------

}
