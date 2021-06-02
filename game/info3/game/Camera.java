package info3.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Camera {
	
	float x, y;
	private float oldX, oldY;
	private boolean shake =false;
	private int nbShake;
	float shakePower = 20;
	
	
	Camera(int x, int y){
		this.x = x;
		this.y = y;
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
			System.out.println("CAMERA POSITION : " + x + ", " + y);
			double randomX = Math.random();
			double randomY = Math.random();
			this.x+=(randomX*shakePower-shakePower/2)*2;
			this.y+=randomY*shakePower-shakePower/2;
			if(nbShake++ > 20) {
				endShake();
			}
		}
	}
	
	public void paint(Graphics g,  int width, int height, Paintable paintable) {
		paintable.paint(g, width, height, (int)x, (int)y);
	}

}
