package com.View;

import com.App;
import com.Model.Game;
import com.Model.Plane;

import javafx.scene.input.KeyCode;

public class KeyHoldActionsThread extends Thread{
	private static KeyHoldActionsThread instance = new KeyHoldActionsThread();
	public static KeyHoldActionsThread getInstance(){ return instance;}

	private boolean isFinnished=false;

	@Override
	public void run() {
		while (!isFinnished){
			// TODO: maybe kill the thread when game is paused
			if (Game.getInstance().isGameRunning()){
				if (App.isKeyActive(KeyCode.W)) Plane.getInstance().goUp(); 
				if (App.isKeyActive(KeyCode.A)) Plane.getInstance().goLeft(); 
				if (App.isKeyActive(KeyCode.S)) Plane.getInstance().goDown();
				if (App.isKeyActive(KeyCode.D)) Plane.getInstance().goRight();
			}
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void kill(){ isFinnished=true;}
	
}
