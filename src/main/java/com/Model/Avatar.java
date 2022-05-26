package com.Model;

import java.util.ArrayList;
import java.util.Random;

public class Avatar {
	private static Random random = new Random(System.currentTimeMillis());
	private static ArrayList<Avatar> defaultAvatars = new ArrayList<>();

	public static void loadDefaultAvatars(){
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
