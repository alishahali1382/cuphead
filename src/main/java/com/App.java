package com;


import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import com.Model.Avatar;
import com.Model.Plane;
import com.View.GamePage;


public class App extends Application {
	private static Scene scene;
	private static HashMap<String, Boolean> currentlyActiveKeys = new HashMap<>();



	public static final int WIDTH=1080, HEIGHT=680;

	
	public static void setRootFromFXML(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	public static void setRoot(Parent parent){
		scene.setRoot(parent);
	}

	public static URL getURL(String fileName){
		URL url=App.class.getResource(fileName);
		if (url==null){
			System.out.println("Error: could not find resource: " + fileName);
		}
		return url;
	}

	private static Parent loadFXML(String fxml) throws IOException {
		URL url=getURL("fxml/" + fxml + ".fxml");
		if (url==null){
			System.out.println("fxml file not found, filename:" + fxml+".fxml");
			throw new IOException();
		}
		FXMLLoader fxmlLoader = new FXMLLoader(url);
		return fxmlLoader.load();
	}

	public static boolean isKeyActive(String keyCode){
		Boolean res=currentlyActiveKeys.get(keyCode);
		if (res==null) return false;
		return res;
	}

	@Override
	public void start(Stage stage) throws IOException {
		scene = new Scene(loadFXML("loginPage"), WIDTH, HEIGHT);
		
		scene.setOnKeyPressed(event -> {
			String codeString = event.getCode().toString();
			if (!currentlyActiveKeys.containsKey(codeString)) {
				currentlyActiveKeys.put(codeString, true);
			}
		});
		scene.setOnKeyReleased(event -> 
			currentlyActiveKeys.remove(event.getCode().toString())
		);
		
		GamePage.getInstance().startGame();
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		Avatar.loadDefaultAvatars();
		launch();
	}

}