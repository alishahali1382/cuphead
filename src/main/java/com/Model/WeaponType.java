package com.Model;

import com.App;
import com.View.GamePage;

import javafx.scene.paint.ImagePattern;

public enum WeaponType{
	BULLET("assets/shmup_icon_bullet_0001.png", 85){
		private double bulletSpawnPlace=0.4;
		@Override
		public void attack(Plane plane) {
			Bullet bullet = new Bullet(plane.getX()+plane.getWidth()/2, plane.getY()+plane.getHeight()*bulletSpawnPlace);
			bulletSpawnPlace=1-bulletSpawnPlace;
			Game.getInstance().addBullet(bullet);
			GamePage.getInstance().addGameObject(bullet);
		}
	},
	BOMB("assets/shmup_icon_bomb_0001.png", 250){
		@Override
		public void attack(Plane plane) {
			BombBullet bombBullet = new BombBullet(plane.getX()+plane.getWidth()/2, plane.getY()+plane.getHeight());
			Game.getInstance().addBombBullet(bombBullet);
			GamePage.getInstance().addGameObject(bombBullet);
		}
	}
	;

	private long delayBetweenAttacks;
	private ImagePattern imagePattern;

	public ImagePattern getImagePattern(){
		return imagePattern;
	}
	public long getDelayBetweenAttacks(){
		return delayBetweenAttacks;
	}
	private WeaponType(String filename, long delayBetweenAttacks){
		this.imagePattern = new ImagePattern(App.loadImage(filename));
		this.delayBetweenAttacks=delayBetweenAttacks;
	}
	public abstract void attack(Plane plane);
}