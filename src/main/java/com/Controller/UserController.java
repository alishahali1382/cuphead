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
		return Message.SUCCESS;
	}

	



	public enum Message{
		SUCCESS("register successful"),
		USERNAME_ALREADY_EXIST("a user with this username already exist"),
		WEAK_PASSWORD("password is weak"),

		;
		private final String message;
		private Message(String message){
			this.message=message;
		}
		@Override
		public String toString(){ return this.message;}
	}
}
