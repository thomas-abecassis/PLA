package info3.game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import info3.gameObjects.GameObject;
import info3.managers.PaintManager;
import info3.managers.TickManager;

public class Level {

	private ArrayList<GameObject> gameObjects;
	private String nom;

	public Level(String nom) {
		gameObjects = new ArrayList<GameObject>();
		this.nom = nom;
	}

	public void addGameObject(GameObject gameObject) {
		gameObjects.add(gameObject);
	}

	public void readLevel() {
		String[] levelStrings = new String[0];
		try {
			String levelString = new String(Files.readAllBytes(Paths.get("resources/niveaux/" + nom + ".csv")));
			levelStrings = levelString.split(";");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < levelStrings.length; i++) {
			if (levelStrings[i].equals("C")) {
				Cowboy cowboy;
				try {
					cowboy = readCowboy(levelStrings, i);
					gameObjects.add(cowboy);
					i += 2;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	public void writeLevel() {
		Charset utf8 = StandardCharsets.UTF_8;
		String tempString = "";
		ArrayList<String> strings = new ArrayList<String>();
		for (GameObject gameObject : gameObjects) {
			tempString = tempString + gameObject.sauvegardeString();
		}
		strings.add(tempString);
		try {
			Files.write(Paths.get("resources/niveaux/" + nom + ".csv"), strings, utf8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Cowboy readCowboy(String[] cowboyString, int index) throws IOException{
		int x = Integer.parseInt(cowboyString[index + 1]);
		int y = Integer.parseInt(cowboyString[index + 2]);
		return new Cowboy(x, y);
	}

	public void loadLevel() {
		try {
			Game.game = new Game(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PaintManager.instance.reset();
		TickManager.instance.reset();
		for (GameObject gameObject : gameObjects) {
			TickManager.instance.add(gameObject);
			PaintManager.instance.add(gameObject);
		}
	}

}
