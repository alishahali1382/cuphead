package com.Controller;

import java.io.IOException;

import com.App;
import com.Model.Boss;
import com.Model.Game;
import com.Model.Plane;
import com.Model.User;
import com.View.GamePage;
import com.View.GameViewController;
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
	private long startTime;
	private AnimationTimer animationTimer;

	public void startGame(){
		startTime=System.currentTimeMillis();
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

		GamePage.getInstance().stopThemeMusic();
		
		if (win){
			handleWin();
		}
		
		KeyHoldActionsThread.getInstance().kill();
		GamePage.getInstance().clearAll();
		try {
			App.setRootFromFXML("MainMenu");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void handleWin(){
		String address=App.getURL("sounds/victory.wav").toString();
		Media victorySound = new Media(address);
		MediaPlayer mediaPlayer = new MediaPlayer(victorySound);
		mediaPlayer.play();
		User user=User.getLoggedInUser();
		if (user!=null) user.updateHighScore(Game.getInstance().getScore());
	}

	private int miniBossTimer;

	private String getTimeString(long time){
		time/=1000;
		return String.format("%02d:%02d", time/60, time%60);
	}

	// main game loop
	private void update(){
		if (!Game.getInstance().isGameRunning()) return ;
		GameViewController.setTimerText(getTimeString(System.currentTimeMillis()-startTime));
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
		Game.getInstance().checkBombBulletHits();
		Game.getInstance().removeDeadBullets();
		Game.getInstance().removeDeadBombBullets();
		Game.getInstance().removeDeadMiniBoss();
		
		Game.getInstance().checkPlaneCollision();
		

		if (Boss.getInstance().getHP()<=0){
			Game.getInstance().addScore((int) Math.max(0, 1000-playTime));
			endGame(true);
		}
		if (Plane.getInstance().getHP()<=0){
			endGame(false);
		}

	}


	public double getPlayTime(){ return playTime;}
}
