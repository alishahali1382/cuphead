package com.View;

import java.io.IOException;

import com.App;
import com.Model.User;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginPageController {

	@FXML
    private Text errorText;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField usernameTextField;

	@FXML
	private synchronized void loginAction() throws IOException{
		String username=usernameTextField.getText();
		String password=passwordTextField.getText();
		if (!User.login(username, password)){
			errorText.setText("username and password don't match");
		}
		else{
			App.setRootFromFXML("MainMenu");
		}
	}

	@FXML
	private void registerAction() throws IOException{
		App.setRootFromFXML("registerPage");
	}
	
	@FXML
	private void loginAsGuestAction() throws IOException{
		App.setRootFromFXML("MainMenu");
		
	}

	@FXML
	public void initialize(){
		
	}
	

}
