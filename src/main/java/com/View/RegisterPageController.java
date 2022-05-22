package com.View;

import java.io.IOException;

import com.App;
import com.Controller.UserController;
import com.Controller.UserController.Message;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RegisterPageController {
	
	@FXML
	private TextField usernameTextField;
	
	@FXML
	private TextField passwordTextField1;
	
	@FXML
	private TextField passwordTextField2;

	@FXML
	private Text errorText;

	private void error(String text){
		errorText.setText(text);
	}

	@FXML
	private void registerAction(ActionEvent event) throws IOException{
		System.out.println(event);

		String username=usernameTextField.getText();
		String password=passwordTextField1.getText();
		String password2=passwordTextField2.getText();
		if (!password.equals(password2)){
			error("passwords are not same");
			return ;
		}
		UserController.Message message = UserController.getInstance().register(username, password);
		if (message==Message.SUCCESS){
			App.setRoot("loginPage");
			return ;
		}
		error(message.toString());
	}

}
