package com.Model;

import java.util.ArrayList;
import java.util.Random;

import com.Transitions.BulletHitTransition;
import com.Transitions.MiniBossDeathTransition;
import com.View.GamePage;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

public class Game {
	private static Game instance = new Game();
	public static Game getInstance(){ return instance;}
	
	private static Random random = new Random(System.currentTimeMillis());
	public static final int WIDTH=1080, HEIGHT=680;
	
	private int score=0;
	private boolean gameRunning;

	private ArrayList<Bullet> allBullets = new ArrayList<>();
	private ArrayList<MiniBoss> allMiniBoss = new ArrayList<>();

	public int getScore(){ return score;}
	public void addScore(int score){ this.score+=score;}

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
	public void generateRandomEggBullet(){
		if (random.nextInt(300)!=0) return ;
		if (EggBullet.getInstance()!=null) return ;
		
		double x=Boss.getInstance().getX();
		double y=Boss.getInstance().getY() + Boss.getInstance().getWidth()/2 - EggBullet.height/2;
		EggBullet eggBullet = new EggBullet(x, y);
		GamePage.getInstance().addGameObject(eggBullet);
		EggBullet.setInstance(eggBullet);
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
		allMiniBoss.forEach(this::handleMiniBossDeath);
		allMiniBoss.removeIf(MiniBoss::isDead);
	}
	
	public void checkBulletHits(){
		for (Bullet bullet : allBullets) {
			Boss boss = Boss.getInstance();
			if (boss.getCollisionView().getBoundsInParent().intersects(bullet.getView().getBoundsInParent())){
				bullet.setAlive(false);
				bullet.setHit();
				boss.setHP(boss.getHP()-1); // gain 1 damage
				addScore(4);
				continue ;
			}
			EggBullet eggBullet = EggBullet.getInstance();
			if (eggBullet!=null && eggBullet.intersects(bullet)){
				bullet.setAlive(false);
				bullet.setHit();
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
	public void moveAllGameObjects(){
		allBullets.forEach(Bullet::move);
		allMiniBoss.forEach(MiniBoss::move);
		if (EggBullet.getInstance()!=null){
			EggBullet.getInstance().move();
		}
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
	private void handleMiniBossDeath(MiniBoss miniBoss){
		if (miniBoss.getHP()<=0){
			new MiniBossDeathTransition(miniBoss.getView()).play();
			addScore(5);
		}
	}

	
	private static final int maxBlinkingFrames=200;
	private int playerBlinkingFramesLeft=0;
	FadeTransition blinkTransition;
	
	private void killPlane(){
		int HP=Plane.getInstance().getHP()-1;
		System.out.println(HP);
		Plane.getInstance().setHP(HP);
		Plane.getInstance().playExplosionAnimation();
		if (HP>0){
			playerBlinkingFramesLeft=maxBlinkingFrames;
			FadeTransition transition = new FadeTransition(Duration.millis(350), Plane.getInstance().getView());
			transition.setFromValue(0.9);
			transition.setToValue(0.2);
			transition.setCycleCount(Animation.INDEFINITE);
			transition.setAutoReverse(true);
			transition.play();
			blinkTransition=transition;
		}
	}
	public void checkPlaneCollision(){
		if (playerBlinkingFramesLeft>0){
			playerBlinkingFramesLeft--;
			if (playerBlinkingFramesLeft==0){
				Plane.getInstance().getView().setOpacity(1);
				blinkTransition.stop();
			}
			return ;
		}
		for (MiniBoss miniBoss : allMiniBoss) {
			if (Plane.getInstance().intersects(miniBoss)){
				killPlane();
				miniBoss.setHP(miniBoss.getHP()-2); // gain 2 damage
				return ;
			}
		}
		if (EggBullet.getInstance()!=null && Plane.getInstance().intersects(EggBullet.getInstance())){
			killPlane();
			return ;
		}
		if (Boss.getInstance().intersects(Plane.getInstance())){
			Boss.getInstance().setHP(Boss.getInstance().getHP()-4); // gain 4 damage
			killPlane();
		}
	}


}
