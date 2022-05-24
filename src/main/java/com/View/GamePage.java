package com.View;

import java.util.ArrayList;

import com.App;
import com.Controller.GameController;
import com.Model.Boss;
import com.Model.Game;
import com.Model.GameObject;
import com.Model.Plane;
import com.Transitions.BossFlyTransition;
import com.Transitions.PlaneFlyTransition;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
	
	public void startGame(){
		game = new Pane();
		initBackground();
		game.getChildren().add(Boss.getInstance().getView());
		game.getChildren().add(Plane.getInstance().getView());

		GameController.getInstance().startGame();
		
		initBossAnimation();
		initPlaneAnimation();
		
		App.setRoot(game);
		KeyHoldActionsThread.getInstance().setDaemon(true);
		KeyHoldActionsThread.getInstance().start();
	}

	public void addGameObject(GameObject object){
		game.getChildren().add(object.getView());
		// TODO: add these behind plane
	}
	public void removeGameObject(GameObject object){
		game.getChildren().remove(object.getView());
	}
	
	private void initSingleBackgroundAnimation(String filename, double millis){
		Image backgroundImage= new Image(App.getURL(filename).toExternalForm());

		ImageView background1 = new ImageView(backgroundImage);
		ImageView background2 = new ImageView(backgroundImage);

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

	private void initBackground(){
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

	private void initBossAnimation(){
		BossFlyTransition transition = new BossFlyTransition(Boss.getInstance().getView());
		transition.play();
		allTransitions.add(transition);
	}

	private void initPlaneAnimation(){
		PlaneFlyTransition transition = new PlaneFlyTransition(Plane.getInstance().getView());
		transition.play();
		allTransitions.add(transition);
	}



}
