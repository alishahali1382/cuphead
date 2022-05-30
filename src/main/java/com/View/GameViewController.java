package com.View;

import com.Model.Plane;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
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

	@FXML
	private Text timerText;


	
	public Pane getGamePane(){ return gamePane;}

	public void setBossHP(double hp){
		if (hp<0) hp=0;
		remainingBossHPProgressBar.setProgress(hp);
		remainingBossHPText.setText(((Integer)(int)(hp*100)).toString());
	}

	public void setRemainingLives(int count){
		remainingHPText.setText("HP. " + count);
		if (count<=2){
			remainingHPText.setFill(Paint.valueOf("0xff0000"));;
		}
	}
	
	public void setScore(int count){
		scoreText.setText(""+count);
	}

	public void setWeaponTypeRectangle(){
		weaponTypeRectangle.setFill(Plane.getInstance().getWeaponType().getImagePattern());
	}

	@FXML
	public void initialize(){
		instance=this;
		GamePage.getInstance().startGame(gamePane);
		System.out.println("initialize"); // TODO
		setWeaponTypeRectangle();
	}

	public static void setTimerText(String text){
		instance.timerText.setText(text);
	}

	


}
