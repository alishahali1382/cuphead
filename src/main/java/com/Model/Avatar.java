package com.Model;

import java.util.HashMap;
import java.util.Random;

import com.App;
import com.google.gson.annotations.Expose;

import javafx.scene.image.Image;

public class Avatar {
	private static Random random = new Random(System.currentTimeMillis());
	private static HashMap<String, Avatar> defaultAvatars = new HashMap<>();
	private static int numberOfDefaultAvatars;

	@Expose
	private String filename;
	private Image image;

	private static void addDefaultAvatarByFilename(String filename){
		defaultAvatars.put(filename, new Avatar(filename));
		numberOfDefaultAvatars++;
	}
	public static void loadDefaultAvatars(){
		addDefaultAvatarByFilename("SPN.jpg");
		for (int i=1; i<=8; i++)
			addDefaultAvatarByFilename(String.format("avatar%d.png", i));
	}
	public static Avatar getRandomDefaultAvatar(){
		return (Avatar) defaultAvatars.values().toArray()[random.nextInt(numberOfDefaultAvatars)];
	}
	public static Avatar getGuestAvatar(){
		return defaultAvatars.get("SPN.jpg");
	}
	public static Avatar getAvatarByName(Avatar avatar){
		return defaultAvatars.get(avatar.filename);
	}

	public Avatar(String filename){
		this.filename=filename;
		this.image = App.loadImage("avatar/"+filename);
	}

	public Image getImage(){ return image;}

}
