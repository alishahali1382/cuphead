package com.Controller;

import java.io.File;

import com.App;
import com.Model.Boss;
import com.Model.Game;
import com.Model.Plane;
import com.View.GamePage;
import com.View.KeyHoldActionsThread;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

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
		Plane.setInstance();
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

	public void endGame(boolean win){
		Game.getInstance().setGameRunning(false);
		animationTimer.stop();

		if (win){
			try {
				String address=App.getURL("sounds/victory.wav").toString();
				System.out.println(address);
				// File file = new File(address);
				// System.out.println(file.toURI().toString());
				Media victorySound = new Media(address);
				// Media victorySound = new Media("../sounds/victory.wav");
				MediaPlayer mediaPlayer = new MediaPlayer(victorySound);
				mediaPlayer.play();
				// TODO: shit
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		// TODO: wait a little and show animation

		KeyHoldActionsThread.getInstance().kill();
		GamePage.getInstance().clearAll();
		
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
		
		Game.getInstance().checkPlaneCollision();
		

		if (Boss.getInstance().getHP()<=0){
			Game.getInstance().addScore((int) Math.max(0, 1000-playTime));
			endGame(true);
		}
		if (Plane.getInstance().getHP()<=0){
			// TODO: not sure
			endGame(false);
		}

	}


	public double getPlayTime(){ return playTime;}
}
