package info3.game;

import java.io.IOException;

import info3.managers.PaintManager;

public class Editeur implements Tickable{
	
	private Level level;
	private Cowboy preview;
	
	public Editeur() {
		try {
			preview = new Cowboy(0 , 0);
			PaintManager.instance.add(preview);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createLevel(String nomLevel) {
		level = new Level(nomLevel);
	}
	
	public void tick(long deltaTime) {
		mouse(Game.game.m_listener);
	}
	
	public void mouse(CanvasListener canvasListener) {
		int mouseX = canvasListener.mouseX;
		int mouseY = canvasListener.mouseY;
		if(canvasListener.key16) {
			mouseX = (mouseX / 50) * 50;
			mouseY =(mouseY / 50) * 50;
		}
		preview.transform.setPosition(mouseX, mouseY);
		if(canvasListener.mouseClicked) {
			canvasListener.mouseClicked=false;
			try {
				Cowboy nouveauCowboy = new Cowboy(mouseX, mouseY);
				PaintManager.instance.add(nouveauCowboy);
				level.addGameObject(nouveauCowboy);
				level.writeLevel();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}

}
