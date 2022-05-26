package com;


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import com.Controller.GameController;
import com.Model.Avatar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;


public class App extends Application {
	private static Scene scene;
	private static HashMap<KeyCode, Boolean> currentlyActiveKeys = new HashMap<>();



	public static final int WIDTH=1080, HEIGHT=710;

	
	public static void setRootFromFXML(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	public static void setRoot(Parent parent){
		scene.setRoot(parent);
	}

	public static URL getURL(String filename){
		URL url=App.class.getResource(filename);
		if (url==null){
			System.out.println("Error: could not find resource: " + filename);
		}
		return url;
	}

	public static Image loadImage(String filename){
		return new Image(getURL(filename).toExternalForm());
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

	public static boolean isKeyActive(KeyCode keyCode){
		Boolean res=currentlyActiveKeys.get(keyCode);
		if (res==null) return false;
		return res;
	}

	public void initScene(){
		scene.setOnKeyPressed(event -> {
			KeyCode keyCode = event.getCode();
			if (!currentlyActiveKeys.containsKey(keyCode)) {
				currentlyActiveKeys.put(keyCode, true);
			}
		});
		scene.setOnKeyReleased(event -> 
			currentlyActiveKeys.remove(event.getCode())
		);
	}

	@Override
	public void start(Stage stage) throws IOException {
		scene = new Scene(loadFXML("loginPage"), WIDTH, HEIGHT);
		
		initScene();
		
		
		setRootFromFXML("GameView");
		
		
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void stop(){
		GameController.getInstance().endGame();
		System.out.println("app is closing");

	}

	public static void main(String[] args) {
		Avatar.loadDefaultAvatars();
		launch();
	}

}