package com.Model;

import java.util.ArrayList;

import com.App;
import com.View.GamePage;

public class Game {
	private static Game instance = new Game();
	public static Game getInstance(){ return instance;}
	
	public static final int WIDTH=App.WIDTH, HEIGHT=App.HEIGHT;
	
	private boolean gameRunning;

	// TODO: make private
	public ArrayList<Bullet> allBullets = new ArrayList<>();

	public boolean isGameRunning(){ return gameRunning;}
	public void setGameRunning(boolean isRunning){ gameRunning=isRunning;}

	public void addBullet(Bullet bullet){
		allBullets.add(bullet);
	}
	public void removeDeadBullets(){
		allBullets.forEach(this::removeDeadBulletFromScreen);
		allBullets.removeIf(Bullet::isDead);
	}
	public void checkBulletHits(){
		for (Bullet bullet : allBullets) {
			Boss boss = Boss.getInstance();
			if (boss.getCollisionView().getBoundsInParent().intersects(bullet.getView().getBoundsInParent())){
				bullet.setAlive(false);
				boss.setHP(boss.getHP()-1);
				continue ;
			}
		}
	}
	public void moveAllBullets(){
		allBullets.forEach(Bullet::move);
	}

	private void removeDeadBulletFromScreen(GameObject object){
		if (object.isDead()){
			GamePage.getInstance().removeGameObject(object);
		}
	}

	


}
