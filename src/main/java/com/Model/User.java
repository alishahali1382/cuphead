package com.Model;

import java.util.HashMap;

public class User{
	private static HashMap<String, User> allUsers = new HashMap<>();
	private static User loggedInUser;

	private String username, password;
	private Avatar avatar;

	public User(String username, String password, Avatar avatar){
		this.username=username;
		this.password=password;
		this.avatar=avatar;
		allUsers.put(username, this);
	}

	public static boolean Login(String username, String password){
		User user=allUsers.get(username);
		if (user==null) return false;
		if (!user.isPasswordEqualTo(password)) return false;
		loggedInUser=user;
		return true;
	}
	public static User getLoggedInUser(){ return loggedInUser;}
	
	public static boolean doesUsernameExist(String username){ return allUsers.get(username)!=null;}

	public String getUsername(){ return this.username;}
	public void setUsername(String username) {
		this.username = username;
	}

	public Avatar getAvatar(){return this.avatar;}
	public void setAvatar(Avatar avatar){ this.avatar=avatar;}

	private boolean isPasswordEqualTo(String password){
		return this.password.equals(password);
	}
	public boolean changePassword(String oldPassword, String newPassword){
		if (!isPasswordEqualTo(oldPassword)) return false;
		this.password=newPassword;
		return true;
	}
}
