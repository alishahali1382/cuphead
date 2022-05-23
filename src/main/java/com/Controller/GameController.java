package com.Controller;

import com.App;
import com.Model.Boss;
import com.Model.Bullet;
import com.Model.Game;
import com.Model.Plane;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class GameController {
	private static GameController instance = new GameController();
	public static GameController getInstance(){ return instance;}

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
		Boss.getInstance().randomMove();
		// Bullet.moveAllBullets();
		// Bullet.removeDeadBullets();


	}

}
