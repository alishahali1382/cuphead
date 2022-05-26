package com.View;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GameViewController {
	private static GameViewController instance;
	public static GameViewController getInstance(){ return instance;}

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private Pane gamePane;

	@FXML
	private ProgressBar remainingBossHPProgressBar;

	@FXML
	private Text remainingBossHPText;

	@FXML
	private Text remainingHPText;

	@FXML
	private Text scoreText;

	@FXML
	private Rectangle weaponTypeRectangle;

	public Pane getGamePane(){ return gamePane;}

	public void setBossHP(double hp){
		if (hp<0) hp=0;
		remainingBossHPProgressBar.setProgress(hp);
		remainingBossHPText.setText(((Integer)(int)(hp*100)).toString());
	}

	public void setRemainingLives(int count){
		remainingHPText.setText("HP. " + count);
	}
	
	public void setScore(int count){
		scoreText.setText(""+count);
	}

	// TODO: weapon type

	@FXML
	public void initialize(){
		instance=this;
		System.out.println("initialize"); // TODO
		// TODO: set default weapon type
		GamePage.getInstance().startGame(gamePane);
	}

	


}
