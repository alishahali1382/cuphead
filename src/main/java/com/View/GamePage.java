package com.View;

import java.util.ArrayList;

import com.App;
import com.Controller.GameController;
import com.Model.Boss;
import com.Model.GameObject;
import com.Model.Plane;
import com.Transitions.BossFlyTransition;
import com.Transitions.PlaneFlyTransition;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class GamePage{
	private static GamePage instance = new GamePage(App.WIDTH, App.HEIGHT);
	public static GamePage getInstance(){ return instance;}
	
	private GamePage(int WIDTH, int HEIGHT){
		this.WIDTH=WIDTH;
		this.HEIGHT=HEIGHT;
	}
	
	
	public final int WIDTH, HEIGHT;
	private Pane game;
	private ArrayList<Transition> allTransitions = new ArrayList<>();
	private ColorAdjust colorAdjust = new ColorAdjust();
	private MediaPlayer themeMediaPlayer;

	public void startGame(Pane gamePane){
		App.stopMenuTheme();
		
		game = gamePane;
		initBackgroundAnimation();
		initThemeMusic();
		
		GameController.getInstance().startGame();
		
		initBossFlyAnimation();
		initPlaneFlyAnimation();
		
		playThemeMusic();
		
	}
	
	public void blackAndWhiteBackground(){
		colorAdjust.setSaturation(-0.7-colorAdjust.getSaturation());
	}

	private Media explosionSound = new Media(App.getURL("sounds/explosion.wav").toExternalForm());
	public void playExplosionSound(){
		if (isThemeMusicMute()) return ;
		new MediaPlayer(explosionSound).play();
	}
	
	private void initThemeMusic(){
		Media backgroundSound = new Media(App.getURL("sounds/theme.wav").toString());
		themeMediaPlayer = new MediaPlayer(backgroundSound);
		themeMediaPlayer.setCycleCount(-1);
	}
	
	private boolean themeMusicPaused;
	public void playThemeMusic(){
		themeMediaPlayer.play();
		themeMusicPaused=false;
	}
	public void pauseResumeThemeMusic(){
		if (themeMusicPaused) themeMediaPlayer.play();
		else themeMediaPlayer.pause();
		themeMusicPaused^=true;
	}
	public void stopThemeMusic(){
		themeMediaPlayer.stop();
	}
	public void muteUnmuteThemeMusic(){
		if (isThemeMusicMute()) themeMediaPlayer.setMute(false);
		else themeMediaPlayer.setMute(true);
	}
	public boolean isThemeMusicMute(){
		return themeMediaPlayer.isMute();
	}



	public void addGameObject(GameObject object){
		game.getChildren().add(object.getView());
	}
	public void removeGameObject(GameObject object){
		game.getChildren().remove(object.getView());
	}
	
	private void initSingleBackgroundAnimation(String filename, double millis){
		Image backgroundImage= new Image(App.getURL(filename).toExternalForm());

		ImageView background1 = new ImageView(backgroundImage);
		ImageView background2 = new ImageView(backgroundImage);

		background1.setEffect(colorAdjust);
		background2.setEffect(colorAdjust);

		double H=backgroundImage.getHeight();

		background1.setFitWidth(backgroundImage.getWidth() * HEIGHT/H);
		background1.setFitHeight(HEIGHT);
		
		background2.setFitWidth(backgroundImage.getWidth() * HEIGHT/H);
		background2.setFitHeight(HEIGHT);
		
		game.getChildren().add(background1);
		game.getChildren().add(background2);

		double W=background1.getFitWidth();

		TranslateTransition translateTransition1 = new TranslateTransition(Duration.millis(millis), background1);
		translateTransition1.setFromX(0);
		translateTransition1.setToX(-W);
		translateTransition1.setInterpolator(Interpolator.LINEAR);
	
		TranslateTransition translateTransition2 = new TranslateTransition(Duration.millis(millis), background2);
		translateTransition2.setFromX(W);
		translateTransition2.setToX(0);
		translateTransition2.setInterpolator(Interpolator.LINEAR);

		ParallelTransition transition = new ParallelTransition(translateTransition1, translateTransition2);
		transition.setCycleCount(Animation.INDEFINITE);

		transition.play();
		allTransitions.add(transition);
	}

	private void initBackgroundAnimation(){
		initSingleBackgroundAnimation("assets/Background/birdhouse_bg_0008.png", 20000);
		initSingleBackgroundAnimation("assets/Background/birdhouse_bg_0007.png", 20000);
		initSingleBackgroundAnimation("assets/Background/birdhouse_bg_0006.png", 20000);
		initSingleBackgroundAnimation("assets/Background/birdhouse_bg_0005.png", 20000);
		initSingleBackgroundAnimation("assets/Background/birdhouse_bg_0004.png", 20000);
		initSingleBackgroundAnimation("assets/Background/birdhouse_bg_0003.png", 20000);
		initSingleBackgroundAnimation("assets/Background/birdhouse_bg_0002.png", 20000);
		// initSingleBackgroundAnimation("assets/Background/birdhouse_bg_0001.png", 30000);
		// TODO: find the image with larger height(others have top transparent part)
	}

	private void initBossFlyAnimation(){
		BossFlyTransition transition = new BossFlyTransition(Boss.getInstance().getView());
		transition.play();
		allTransitions.add(transition);
	}

	private void initPlaneFlyAnimation(){
		PlaneFlyTransition transition = new PlaneFlyTransition(Plane.getInstance().getView());
		transition.play();
		allTransitions.add(transition);
	}

	
	public void addTransition(Transition transition){
		allTransitions.add(transition);
	}
	public void removeTransition(Transition transition){
		allTransitions.remove(transition);
	}

	public void addRectangle(Rectangle rectangle){
		game.getChildren().add(rectangle);
	}
	public void removeRectangle(Rectangle rectangle){
		game.getChildren().remove(rectangle);
	}

	public void clearAll(){
		game.getChildren().clear();
		for (Transition transition : allTransitions) {
			transition.stop();
		}
		allTransitions.clear();
	}
}
