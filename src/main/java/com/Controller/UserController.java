package com.Controller;

import com.Model.Avatar;
import com.Model.User;

public class UserController {
	private static UserController instance = new UserController();
	public static UserController getInstance(){ return instance;}

	
	private boolean validatePassword(String password){
		if (password.length()<8) return false;
		return true;
	}

	public Message register(String username, String password){
		if (User.doesUsernameExist(username)) return Message.USERNAME_ALREADY_EXIST;
		if (!validatePassword(password)) return Message.WEAK_PASSWORD;
		new User(username, password, Avatar.getRandomDefaultAvatar());
		User.saveUsersToFile();
		return Message.REGISTER_SUCCESS;
	}

	public Message changeInfo(User user, String oldPassword, String username, String password){
		if (!password.isEmpty()){
			if (!validatePassword(password)) return Message.WEAK_PASSWORD;
			user.changePassword(oldPassword, password);
		}
		else password=oldPassword;
		if (!username.isEmpty()){
			if (User.doesUsernameExist(username)) return Message.USERNAME_ALREADY_EXIST;
			user.changeUsername(oldPassword, username);
		}
		return Message.CHANGE_INFO_SUCCESS;
	}

	
	



	public enum Message{
		REGISTER_SUCCESS("register successful"),
		USERNAME_ALREADY_EXIST("a user with this username already exist"),
		WEAK_PASSWORD("password is weak"),
		
		CHANGE_INFO_SUCCESS("changed successfully"),
		;

		private final String message;
		private Message(String message){
			this.message=message;
		}
		@Override
		public String toString(){ return this.message;}
	}
}
