package com.Controller;

import com.App;
import com.Model.Boss;
import com.Model.Game;
import com.Model.Plane;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

public class GameController {
	private static GameController instance = new GameController();
	public static GameController getInstance(){ return instance;}

	public static double deltaTime=0.017;

	private AnimationTimer animationTimer;

	public void startGame(){
		Game.getInstance().setGameRunning(true);

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
		// TODO: add calculated score to scoreboard ...
	}

	
	// main game loop 
	private void update(){
		if (App.isKeyActive(KeyCode.SPACE)){
			Plane.getInstance().attack();
		}

		Boss.getInstance().randomMove();
		Game.getInstance().moveAllBullets();

		Game.getInstance().checkBulletHits();
		Game.getInstance().removeDeadBullets();
		
		
	}

}
