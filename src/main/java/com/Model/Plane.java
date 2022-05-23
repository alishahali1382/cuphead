package com.Model;

import com.App;
import com.View.GamePage;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Plane{
	private static Plane instance = new Plane();
	public static Plane getInstance(){ return instance;}

	private static final long movementSpeed=300;  // pixel/second
	private final double GameW, GameH;

	private Rectangle view;

	private Plane(){
		view = new Rectangle(0, 0, 100, 90);
		GameW=GamePage.getInstance().WIDTH;
		GameH=GamePage.getInstance().HEIGHT;
		
		// view.setFill(new ImagePattern(new Image(App.getURL("assets/Plane.png").toExternalForm())));
	}

	public Rectangle getView(){ return view;}
	






	// some shit stuff to seperate movement and firing from thread
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
