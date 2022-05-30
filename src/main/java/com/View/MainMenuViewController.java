package com.View;

import java.io.IOException;

import com.App;
import com.Model.Avatar;
import com.Model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class MainMenuViewController {
	private static MainMenuViewController instance;
	public static MainMenuViewController getInstance(){ return instance;}
	
	@FXML
	private Rectangle avatarRectangle;
	
	@FXML
	private Text welcomeText;

	@FXML
	private Button profileButton;

	@FXML
	private Button logoutButton;


	@FXML
	private void exitGame(ActionEvent event) {
		App.getInstance().stop();
	}

	@FXML
	private void logout(ActionEvent event) throws IOException {
		User.logout();
		App.setRootFromFXML("loginPage");
	}

	@FXML
	private void showProfileMenu(ActionEvent event) throws IOException {
		App.setRootFromFXML("ProfileMenu");
	}

	@FXML
	private void showScoreboard(ActionEvent event) {
		// TODO
	}

	@FXML
	private void startGame(ActionEvent event) throws IOException {
		App.setRootFromFXML("GameView");
	}

	@FXML
	private void initialize(){
		instance=this;
		User user = User.getLoggedInUser();
		Avatar avatar;
		if (user==null){
			avatar=Avatar.getGuestAvatar();
			profileButton.setDisable(true);
			logoutButton.setText("Login");
		}
		else{
			avatar=user.getAvatar();
			welcomeText.setText("Welcome " + user.getUsername());
		}
		avatarRectangle.setFill(new ImagePattern(avatar.getImage()));
	}

}
