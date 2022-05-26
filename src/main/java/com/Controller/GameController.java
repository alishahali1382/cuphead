package com.Controller;

import com.App;
import com.Model.Boss;
import com.Model.Game;
import com.Model.Plane;
import com.View.GamePage;
import com.View.KeyHoldActionsThread;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

public class GameController {
	private static GameController instance = new GameController();
	public static GameController getInstance(){ return instance;}

	public static double deltaTime=0.017;

	private double playTime=0;
	private AnimationTimer animationTimer;

	public void startGame(){
		playTime=0;
		Game.getInstance().setGameRunning(true);
		
		KeyHoldActionsThread.getInstance().setDaemon(true);
		KeyHoldActionsThread.getInstance().start();
		
		Boss.makeNewBoss();
		GamePage.getInstance().addGameObject(Boss.getInstance());
		GamePage.getInstance().addGameObject(Plane.getInstance());

		animationTimer = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				update();
			}
		};
		animationTimer.start();

	}

	public void pauseGame(){
		Game.getInstance().setGameRunning(false);
		animationTimer.stop();
	}
	
	public void resumeGame(){
		animationTimer.stop();
	}

	public void endGame(){
		Game.getInstance().setGameRunning(false);
		animationTimer.stop();
		KeyHoldActionsThread.getInstance().kill();
		
		// TODO: add calculated score to scoreboard ...
	}

	private int miniBossTimer;

	// main game loop
	private void update(){
		if (!Game.getInstance().isGameRunning()) return ;
		playTime+=deltaTime;
		if (App.isKeyActive(KeyCode.SPACE)){
			Plane.getInstance().attack();
		}
		if (miniBossTimer++>=300){
			miniBossTimer-=700;
			Game.getInstance().generateRandomMiniBoss();
		}
		Game.getInstance().generateRandomEggBullet();

		Boss.getInstance().randomMove();
		Game.getInstance().moveAllGameObjects();

		Game.getInstance().checkBulletHits();
		Game.getInstance().removeDeadBullets();
		Game.getInstance().removeDeadMiniBoss();
		
	}


	public double getPlayTime(){ return playTime;}
}
