package com.Model;

import java.util.ArrayList;

import com.App;

import javafx.scene.shape.Rectangle;

public class Game {
	private static Game instance = new Game();
	public static Game getInstance(){ return instance;}
	
	public static final int WIDTH=App.WIDTH, HEIGHT=App.HEIGHT;
	
	private boolean gameRunning;

	static ArrayList<Bullet> allBullets = new ArrayList<>();

	public boolean isGameRunning(){ return gameRunning;}
	public void setGameRunning(boolean isRunning){ gameRunning=isRunning;}


    public void removeDeadBullets(){
    	allBullets.removeIf(Bullet::isDead);
    }
    public boolean isEnemyHitByBullet(Rectangle enemy){
    	for (Bullet bullet : allBullets) {
    		if (enemy.getBoundsInParent().intersects(bullet.getBoundsInParent())){
    			bullet.setAlive(false);
    			return true;
    		}
    	}
    	return false;
    }
    public void moveAllBullets(){
    	allBullets.forEach(Bullet::move);
    }

	


}
