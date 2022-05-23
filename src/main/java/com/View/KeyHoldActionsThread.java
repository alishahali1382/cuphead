package com.View;

import com.App;
import com.Model.Game;
import com.Model.Plane;

public class KeyHoldActionsThread extends Thread{
	private static KeyHoldActionsThread instance = new KeyHoldActionsThread();
	public static KeyHoldActionsThread getInstance(){ return instance;}

	@Override
	public void run() {
		while (true){
			// TODO: maybe kill the thread when game is paused
			if (Game.getInstance().isGameRunning()){
				if (App.isKeyActive("W")) Plane.getInstance().goUp(); 
				if (App.isKeyActive("A")) Plane.getInstance().goLeft(); 
				if (App.isKeyActive("S")) Plane.getInstance().goDown();
				if (App.isKeyActive("D")) Plane.getInstance().goRight();
			}
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
