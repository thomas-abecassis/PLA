package info3.game;

import java.awt.Color;
import java.awt.Graphics;

import info3.gameObjects.GameObject;
import info3.gameObjects.Vector2;

public class Bullet extends GameObject implements Paintable, Tickable{
	
	private float vitesse = 300;

	public Bullet(int x, int y, float rotation) {
		super(x, y, rotation, 1);
		this.addBoxCollider(10, 10);
		this.addRigibody(1, 0);
		Vector2 tempVector2 = new Vector2(35, 25);
		tempVector2.rotate(rotation + (float)Math.PI/2);
		Vector2 position = new Vector2(x+tempVector2.x, y+tempVector2.y);
		transform.setPosition(position.x, position.y);

		//yes it's hardcode and it's sucks but i'm tired
		tempVector2 = new Vector2(x, y);
		rigibody.setVelocity(tempVector2.rotate(rotation + (float)Math.PI/2));
	}

	@Override
	public void paint(Graphics g, int width, int height, int cameraPositionX, int cameraPositionY) {
		g.setColor(Color.red);
		g.fillOval((int)(transform.getX() - cameraPositionX), (int)(transform.getY() - cameraPositionY), 10, 10);
	}
	
	@Override
	public void tick(long ellapsed) {
		rigibody.computeMovement(ellapsed);
	}

	
}
