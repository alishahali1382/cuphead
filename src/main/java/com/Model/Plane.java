package com.Model;

import com.Transitions.BulletTransition;
import com.View.GamePage;

public class Plane extends GameObject{
	private static Plane instance = new Plane();
	public static Plane getInstance(){ return instance;}

	private static final long movementSpeed=300;  // pixel/second

	private WeaponType weaponType = WeaponType.BULLET;

	private Plane(){
		super(0, 0, 100, 90);
	}
	
	public void weaponSwitch(){
		weaponType=(weaponType==WeaponType.BULLET?WeaponType.BOMB:WeaponType.BULLET);

	}

	private long lastAttackTime=0;
	public void attack(){
		// just bullet atttack
		
		long curr=System.currentTimeMillis();
		if (curr-lastAttackTime<50) return ;
		
		
		lastAttackTime=curr;

		Bullet bullet = new Bullet(getX()+getWidth(), getY()+getHeight()/2);
		Game.getInstance().addBullet(bullet);
		GamePage.getInstance().addGameObject(bullet);
	}

	enum WeaponType{
		BULLET,
		BOMB,
		;
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
