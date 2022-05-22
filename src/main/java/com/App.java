package com;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import com.Model.Avatar;


public class App extends Application {

	private static Scene scene;
	
	public static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		URL url=App.class.getResource("fxml/" + fxml + ".fxml");
		if (url==null){
			System.out.println("fxml file not found, filename:" + fxml+".fxml");
			throw new IOException();
		}
		FXMLLoader fxmlLoader = new FXMLLoader(url);
		return fxmlLoader.load();
	}

	@Override
	public void start(Stage stage) throws IOException {
		scene = new Scene(loadFXML("loginPage"), 1280, 720);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		Avatar.loadDefaultAvatars();
		launch();
	}

}