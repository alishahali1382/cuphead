package com.View;

import java.io.IOException;

import com.App;

import javafx.fxml.FXML;

public class PrimaryController {

	@FXML
	private void switchToSecondary() throws IOException {
		App.setRoot("loginPage.fxml");
	}
}
