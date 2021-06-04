package info3.game;

import java.awt.Color;
import java.awt.Graphics;

import info3.gameObjects.GameObject;

public class Bullet extends GameObject implements Paintable, Tickable{
	
	private float vitesse = 300;

	public Bullet(int x, int y, float rotation) {
		super(x, y, rotation, 1);
		this.addBoxCollider(10, 10);
		this.addRigibody(1, 0);
		//yes it's hardcode and it's sucks but i'm tired
		if(rotation==0)
			rigibody.setVelocityY(vitesse);
		if(rotation==(float)Math.PI*0.5F)
			rigibody.setVelocityX(-vitesse);
		if(rotation==(float)Math.PI*1.5F)
			rigibody.setVelocityX(vitesse);
		if(rotation==(float)Math.PI)
			rigibody.setVelocityY(-vitesse);
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
