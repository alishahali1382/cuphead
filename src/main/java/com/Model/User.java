package com.Model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import com.Controller.DataBaseController;
import com.google.gson.annotations.Expose;

public class User{
	private static HashMap<String, User> allUsers = new HashMap<>();
	private static User loggedInUser;

	@Expose
	private String username, password;
	@Expose
	private Avatar avatar;
	@Expose
	private int highScore;



	public User(String username, String password, Avatar avatar){
		this.username=username;
		this.password=password;
		this.avatar=avatar;
		this.highScore=0;
		allUsers.put(username, this);
	}


	
	public static boolean login(String username, String password){
		User user=allUsers.get(username);
		if (user==null) return false;
		if (!user.isPasswordEqualTo(password)) return false;
		loggedInUser=user;
		return true;
	}
	public static User getLoggedInUser(){ return loggedInUser;}
	public static void logout(){ loggedInUser=null;}
	
	public static boolean doesUsernameExist(String username){ return allUsers.get(username)!=null;}

	public static void saveUsersToFile(){
		String json = DataBaseController.getGson().toJson(allUsers.values());
		try {
			DataBaseController.saveToFile("users.json", json);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void loadUsersFromFile(){
		String json;
		try {
			json = DataBaseController.loadFromFile("users.json");
		} catch (IOException e1) {
			e1.printStackTrace();
			return ;
		}
		User[] users = DataBaseController.getGson().fromJson(json, User[].class);
		allUsers.clear();
		for (User user : users) {
			allUsers.put(user.username, user);
			user.setAvatar(Avatar.getAvatarByName(user.avatar));
		}
	}




	public String getUsername(){ return this.username;}
	public void setUsername(String username) {
		this.username = username;
	}

	public Avatar getAvatar(){return this.avatar;}
	public void setAvatar(Avatar avatar){ this.avatar=avatar;}

	public boolean isPasswordEqualTo(String password){
		return this.password.equals(password);
	}
	public boolean changePassword(String oldPassword, String newPassword){
		if (!isPasswordEqualTo(oldPassword)) return false;
		this.password=newPassword;
		saveUsersToFile();
		return true;
	}
	public boolean changeUsername(String oldPassword, String newUsername){
		if (!isPasswordEqualTo(oldPassword)) return false;
		allUsers.remove(this.username);
		this.username=newUsername;
		allUsers.put(this.username, this);
		saveUsersToFile();
		return true;
	}
	public void removeAccount(){
		User user = loggedInUser;
		loggedInUser=null;
		allUsers.remove(user.username);
		saveUsersToFile();
	}
}
