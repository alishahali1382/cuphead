package com.View;

import java.util.ArrayList;

import com.App;
import com.Model.Plane;

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
	private boolean gameRunning;
	
	public void startGame(){
		game = new Pane();
		initBackground();
		game.getChildren().add(Plane.getInstance());
		
		gameRunning=true;		
		App.setRoot(game);
		KeyHoldActionsThread.getInstance().setDaemon(true);
		KeyHoldActionsThread.getInstance().start();
	}
	
	public boolean isGameRunning(){ return gameRunning;}

	private void initBackground(){
		Image backgroundImage= new Image(App.getURL("assets/background.png").toExternalForm());
		
		ImageView background1 = new ImageView(backgroundImage);
		ImageView background2 = new ImageView(backgroundImage);
		
		background1.setFitWidth(backgroundImage.getWidth() * HEIGHT/backgroundImage.getHeight());
		background1.setFitHeight(HEIGHT);
		
		background2.setFitWidth(backgroundImage.getWidth() * HEIGHT/backgroundImage.getHeight());
		background2.setFitHeight(HEIGHT);
		
		game.getChildren().add(background1);
		game.getChildren().add(background2);

		double W=background1.getFitWidth();

		TranslateTransition translateTransition1 = new TranslateTransition(Duration.millis(30000), background1);
		translateTransition1.setFromX(0);
		translateTransition1.setToX(-W);
		translateTransition1.setInterpolator(Interpolator.LINEAR);
	
		TranslateTransition translateTransition2 = new TranslateTransition(Duration.millis(30000), background2);
		translateTransition2.setFromX(W);
		translateTransition2.setToX(0);
		translateTransition2.setInterpolator(Interpolator.LINEAR);

		ParallelTransition transition = new ParallelTransition(translateTransition1, translateTransition2);
		transition.setCycleCount(Animation.INDEFINITE);

		transition.play();
		allTransitions.add(transition);
	}





}
