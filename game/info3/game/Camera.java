package info3.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Camera {
	
	float x, y;
	private float oldX, oldY;
	private boolean shake =false;
	private int nbShake;
	ArrayList<Paintable> gameObjects;
	float shakePower = 20;
	
	
	Camera(int x, int y){
		this.x = x;
		this.y = y;
		gameObjects = new ArrayList<Paintable>();
	}
	
	public void addGameObject(Paintable p) {
		gameObjects.add(p);
	}
	
	public void startShake() {
		shake = true;
		oldX=x;
		oldY=y;
		nbShake = 0;
	}
	
	public void endShake() {
		nbShake = 0;
		x = oldX;
		y = oldY;
		shake = false;
	}
	
	public void shake() {
		//System.out.println("CAMERA POSITION : " + x + ", " + y);
		if(shake) {
			double randomX = Math.random();
			double randomY = Math.random();
			this.x+=(randomX*shakePower-shakePower/2)*2;
			this.y+=randomY*shakePower-shakePower/2;
			if(nbShake++ > 20) {
				endShake();
			}
		}
	}
	
	public void paint(Graphics g,  int width, int height) {


		// erase background
		g.setColor(Color.gray);
		g.fillRect(0, 0, width, height);
		for(Paintable gameObject : gameObjects) {
			gameObject.paint(g, width, height, (int)x, (int)y);
		}
	}

}
