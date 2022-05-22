package com.Model;

import com.View.GamePage;

import javafx.scene.shape.Rectangle;

public class Plane extends Rectangle{
	private static Plane instance = new Plane();
	public static Plane getInstance(){ return instance;}

	private final double GameW, GameH;

	private Plane(){
		super(0, 0, 100, 100);
		GameW=GamePage.getInstance().WIDTH;
		GameH=GamePage.getInstance().HEIGHT;
	}

	private static final long movementSpeed=300;  // pixel/second
	
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
		
		setY(getY()-diff);
		if (getY()<0) setY(0);
	}
	public void goLeft(){
		long diff=getpressTimeDifference(lastPressTimeL);
		lastPressTimeL=System.currentTimeMillis();
		if (diff>0) diff=diff*movementSpeed/1000;
		else diff=15;
		
		setX(getX()-diff);
		if (getX()<0) setX(0);
	}
	public void goDown(){
		long diff=getpressTimeDifference(lastPressTimeD);
		lastPressTimeD=System.currentTimeMillis();
		if (diff>0) diff=diff*movementSpeed/1000;
		else diff=15;
		
		setY(getY()+diff);
		if (getY()>GameH-getHeight()) setY(GameH-getHeight());
	}
	public void goRight(){
		long diff=getpressTimeDifference(lastPressTimeR);
		lastPressTimeR=System.currentTimeMillis();
		if (diff>0) diff=diff*movementSpeed/1000;
		else diff=15;
		
		setX(getX()+diff);
		if (getX()>GameW-getWidth()) setX(GameW-getWidth());
	}


}
