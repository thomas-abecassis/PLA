package info3.gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import info3.game.Paintable;

public class BoxCollider extends Collider {

	private Transform transform;
	private float sizeX, sizeY;
	private boolean debug = false;
	
	public BoxCollider(Transform transform, float sizeX, float sizeY) {
		this.transform = transform;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}
	
	public boolean collision(BoxCollider externe) {
		Vector2 vectExtMin = new Vector2(externe.transform.getX(), externe.transform.getY());
		Vector2 vectExtMax = new Vector2(externe.transform.getX()+externe.transform.getTaille(), externe.transform.getY()+externe.transform.getTaille());
		Vector2 vectIntMin = new Vector2(transform.getX(), transform.getY());
		Vector2 vectIntMax = new Vector2(transform.getX() + transform.getTaille(), transform.getY() + transform.getTaille());
		
	    float d1x = vectExtMin.x - vectIntMax.x;
	    float d1y = vectExtMin.y - vectIntMax.y;

	    if (d1x > 0.0f || d1y > 0.0f)
	        return false;
	    
	    float d2x = vectIntMin.x - vectExtMax.x;
	    float d2y = vectIntMin.y - vectExtMax.y;

	    if (d2x > 0.0f || d2y > 0.0f)
	        return false;

	    return true;		
	}

	@Override
	public void paint(Graphics g, int width, int height, int cameraPositionX, int cameraPositionY) {
		if(debug) {
			if(transform.getRotation()!=0) {
				Graphics2D g2 = (Graphics2D)g;
				AffineTransform old = g2.getTransform();
				g2.rotate(transform.getRotation(),transform.getX() - cameraPositionX + sizeX/2, transform.getY() - cameraPositionY + sizeY/2);
				g2.setColor(Color.green);
				g2.drawRect((int)(transform.getX() - cameraPositionX), (int)(transform.getY() - cameraPositionY), (int)sizeX, (int)sizeY);
				g2.setTransform(old);
			}
			else {
				int scale = 2;
				g.setColor(Color.green);
				g.drawRect((int)(transform.getX() - cameraPositionX), (int)(transform.getY() - cameraPositionY), (int)sizeX, (int)sizeY);
			}
		}
	}
	
	public Vector2 centre() {
		return new Vector2(transform.getX() + (sizeX/2) * (float)Math.cos(transform.getRotation()), transform.getY() + (sizeY/2) * (float)Math.sin(transform.getRotation()));
	}
	
	//Pas le meilleur endroit pour le mettre
	public float momentOfInertia(float mass) {
		return mass * ((sizeX * sizeX) + (sizeY *sizeY))/12;
	}
	
}
