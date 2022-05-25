package com.Model;

import java.util.ArrayList;
import java.util.Random;

import com.App;
import com.Transitions.BulletHitTransition;
import com.Transitions.MiniBossDeathTransition;
import com.View.GamePage;

public class Game {
	private static Game instance = new Game();
	public static Game getInstance(){ return instance;}
	
	private static Random random = new Random(System.currentTimeMillis());
	public static final int WIDTH=App.WIDTH, HEIGHT=App.HEIGHT;
	
	private boolean gameRunning;

	private ArrayList<Bullet> allBullets = new ArrayList<>();
	private ArrayList<MiniBoss> allMiniBoss = new ArrayList<>();

	public boolean isGameRunning(){ return gameRunning;}
	public void setGameRunning(boolean isRunning){ gameRunning=isRunning;}

	public void killAllMiniBoss(){
		allMiniBoss.forEach(MiniBoss::remove);
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
		allBullets.forEach(this::playHitAnimationForBullet);
		allBullets.removeIf(Bullet::isDead);
	}
	public void removeDeadMiniBoss(){
		allMiniBoss.forEach(this::playDeathAnimationForMiniBoss);
		allMiniBoss.removeIf(MiniBoss::isDead);
	}
	
	public void checkBulletHits(){
		for (Bullet bullet : allBullets) {
			Boss boss = Boss.getInstance();
			if (boss.getCollisionView().getBoundsInParent().intersects(bullet.getView().getBoundsInParent())){
				bullet.setAlive(false);
				bullet.setHit();
				boss.setHP(boss.getHP()-1); // gain 1 damage
				continue ;
			}
			for (MiniBoss miniBoss : allMiniBoss) {
				if (bullet.intersects(miniBoss)){
					miniBoss.setHP(miniBoss.getHP()-1); // gain 1 damage
					bullet.setAlive(false);
					bullet.setHit();
					break ;
				}
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
	private void playHitAnimationForBullet(Bullet bullet){
		if (bullet.getHit()){
			new BulletHitTransition(bullet).play();
		}
	}
	private void playDeathAnimationForMiniBoss(MiniBoss miniBoss){
		if (miniBoss.getHP()<=0){
			new MiniBossDeathTransition(miniBoss.getView()).play();
		}
	}

	


}
