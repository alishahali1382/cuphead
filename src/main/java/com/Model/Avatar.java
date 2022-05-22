package com.Model;

import java.util.ArrayList;
import java.util.Random;

public class Avatar {
	private static Random random = new Random();
	private static ArrayList<Avatar> defaultAvatars;

	public static void loadDefaultAvatars(){
		defaultAvatars=new ArrayList<>();
		// TODO
		defaultAvatars.add(new Avatar());

	}
	public static Avatar getRandomDefaultAvatar(){
		return defaultAvatars.get(random.nextInt(defaultAvatars.size()));
	}

	public Avatar(){
		// TODO
	}

}
