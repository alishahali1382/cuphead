package com.Model;

import java.util.ArrayList;
import java.util.Random;

import com.App;
import com.View.GamePage;

public class Game {
	private static Game instance = new Game();
	public static Game getInstance(){ return instance;}
	
	private static Random random = new Random(System.currentTimeMillis());
	public static final int WIDTH=App.WIDTH, HEIGHT=App.HEIGHT;
	
	private boolean gameRunning;

	// TODO: make private
	public ArrayList<Bullet> allBullets = new ArrayList<>();
	public ArrayList<MiniBoss> allMiniBoss = new ArrayList<>();

	public boolean isGameRunning(){ return gameRunning;}
	public void setGameRunning(boolean isRunning){ gameRunning=isRunning;}

	public void killAllMiniBoss(){
		allMiniBoss.forEach(MiniBoss::kill);
		allMiniBoss.clear();
	}
	public void generateRandomMiniBoss(){
		killAllMiniBoss();
		double x=WIDTH, y=random.nextInt(400)+50;
		for (int i=0; i<4; i++){
			MiniBoss miniBoss = new MiniBoss(x, y);
			x+=150;
			allMiniBoss.add(miniBoss);
			GamePage.getInstance().addGameObject(miniBoss);
		}
	}

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
	public void moveAllMiniBoss(){
		allMiniBoss.forEach(MiniBoss::move);
	}

	private void removeDeadBulletFromScreen(GameObject object){
		if (object.isDead()){
			GamePage.getInstance().removeGameObject(object);
		}
	}

	


}
