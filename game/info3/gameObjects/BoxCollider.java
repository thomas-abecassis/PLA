package info3.gameObjects;

import java.awt.Color;
import java.awt.Graphics;

import info3.game.Paintable;

public class BoxCollider extends Collider {

	private Transform transform;
	private float sizeX, sizeY;
	private boolean debug = true;
	
	public BoxCollider(Transform transform, float sizeX, float sizeY) {
		this.transform = transform;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}

	@Override
	public void paint(Graphics g, int width, int height, int cameraPositionX, int cameraPositionY) {
		if(debug) {
			int scale = 2;
			g.setColor(Color.green);
			g.drawRect((int)(transform.getX() - cameraPositionX), (int)(transform.getY() - cameraPositionY), (int)sizeX, (int)sizeY);
		}
	}
	
}
