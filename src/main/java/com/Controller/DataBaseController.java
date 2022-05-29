package com.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import com.App;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DataBaseController {
	private static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

	public static void saveToFile(String filename, String text) throws FileNotFoundException {
		filename = App.getURL("database/"+filename).getFile();
		filename=filename.substring(1).replace("%20", " "); // F*** java
		File file = new File(filename);
		PrintWriter printWriter = new PrintWriter(file);
		printWriter.write(text);
		printWriter.close();
	}

	public static String loadFromFile(String filename) throws IOException {
		filename = App.getURL("database/"+filename).getFile();
		filename=filename.substring(1).replace("%20", " "); // F*** java
		File file = new File(filename);
		FileInputStream inputStream = new FileInputStream(file);
		String text = new String(inputStream.readAllBytes());
		inputStream.close();
		return text;
	}

	public static Gson getGson(){ return gson;}

}
