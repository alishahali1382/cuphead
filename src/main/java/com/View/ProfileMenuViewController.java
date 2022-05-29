package com.View;

import java.io.IOException;

import com.App;
import com.Controller.UserController;
import com.Model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ProfileMenuViewController {

	@FXML
	private Text errorText;

	@FXML
	private TextField newPasswordTextField;

	@FXML
	private TextField newUsernameTextField;

	@FXML
	private TextField oldPasswordTextField;

	@FXML
	private void backtoMainMenuAction(ActionEvent event) throws IOException {
		App.setRootFromFXML("MainMenu");
	}

	@FXML
	private void changeInfoAction(ActionEvent event) {
		User user = User.getLoggedInUser();
		String oldPassword = oldPasswordTextField.getText();
		if (!user.isPasswordEqualTo(oldPassword)){
			setErrorText("wrong password");
			return ;
		}

		String username = newUsernameTextField.getText();
		String password = newPasswordTextField.getText();
		
		if (username.isEmpty() && password.isEmpty()){
			setErrorText("nothing to change!");
			return ;
		}
		UserController.Message message = UserController.getInstance().changeInfo(user, oldPassword, username, password);
		setErrorText(message.toString());
	}

	@FXML
	private void removeAccountAction(ActionEvent event) throws IOException {
		User user = User.getLoggedInUser();
		String oldPassword = oldPasswordTextField.getText();
		if (!user.isPasswordEqualTo(oldPassword)){
			setErrorText("wrong password");
			return ;
		}
		user.removeAccount();
		App.setRootFromFXML("loginPage");
	}

	private void setErrorText(String text){
		errorText.setText(text);
	}

}
