package com.View;

import java.io.IOException;
import java.util.ArrayList;

import com.App;
import com.Model.User;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ScoreboardViewController {

	@FXML
	private VBox vBox;

	@FXML
	private void initialize(){
		User.loadUsersFromFile();
		ArrayList<User> users = User.getTopScoreUsers();
		for (int i=0; i<10; i++){
			vBox.getChildren().add(makeHBox(i, users.get(i)));
		}
	}

	private HBox makeHBox(int row, User user){
		HBox hBox = new HBox();
		hBox.setSpacing(500);
		Text usernameText = new Text(String.format("%02d: %s", row+1, user.getUsername()));
		Text highScoreText = new Text(user.getHighScore()+"");
		hBox.getChildren().addAll(usernameText, highScoreText);
		hBox.setAlignment(Pos.CENTER);
		usernameText.setFont(new Font(30));
		highScoreText.setFont(new Font(30));

		return hBox;
	}

	@FXML
	private void backToMainMenu() throws IOException{
		App.setRootFromFXML("MainMenu");
	}

}
