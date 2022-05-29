package com;


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import com.Controller.GameController;
import com.Model.Avatar;
import com.Model.Game;
import com.Model.User;
import com.View.GamePage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.Stage;


public class App extends Application {
	private static App instance;
	public static App getInstance(){ return instance;}


	public static final int WIDTH=1080, HEIGHT=710;
	private static Scene scene;
	private static Stage stage;
	private static HashMap<KeyCode, Boolean> currentlyActiveKeys = new HashMap<>();
	private static MediaPlayer menuMediaPlayer;

	public static void playMenuTheme(){ menuMediaPlayer.play();}
	public static void stopMenuTheme(){ menuMediaPlayer.stop();}

	public static void fitImageViewToStage(ImageView imageView){
		imageView.fitWidthProperty().bind(stage.widthProperty());
	}
	
	public static void setRootFromFXML(String fxml) throws IOException {
		if (menuMediaPlayer.getStatus() == Status.STOPPED){
			String menuFXMLs[] = new String[]{"loginPage", "registerPage"}; // TODO
			for (String string : menuFXMLs)
				if (fxml.equals(string))
					playMenuTheme();
		}
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

	private void initScene(){
		scene.setOnKeyPressed(event -> {
			KeyCode keyCode = event.getCode();
			if (!currentlyActiveKeys.containsKey(keyCode)) {
				currentlyActiveKeys.put(keyCode, true);
			}
			if (keyCode==KeyCode.M){
				if (Game.getInstance().isGameRunning())
					GamePage.getInstance().muteUnmuteThemeMusic();
			}
			if (keyCode==KeyCode.P){
				if (Game.getInstance().isGameRunning())
					GamePage.getInstance().pauseResumeThemeMusic();
			}
		});
		scene.setOnKeyReleased(event -> 
			currentlyActiveKeys.remove(event.getCode())
		);
	}

	public void initMenuTheme(){
		menuMediaPlayer = new MediaPlayer(new Media(getURL("sounds/menu.wav").toExternalForm()));
		menuMediaPlayer.setCycleCount(-1);
		menuMediaPlayer.play();
	}

	@Override
	public void start(Stage stage2) throws IOException {
		instance=this;
		initMenuTheme();
		Avatar.loadDefaultAvatars();
		User.loadUsersFromFile();
		

		stage = stage2;
		scene = new Scene(loadFXML("loginPage"), WIDTH, HEIGHT);
		initScene();
		
		// setRootFromFXML("GameView");
		
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void stop(){
		if (Game.getInstance().isGameRunning()) GameController.getInstance().endGame(false);
		stage.close();
	}

	public static void main(String[] args) {
		launch();
	}

}