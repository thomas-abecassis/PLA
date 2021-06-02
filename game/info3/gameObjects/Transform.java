package info3.gameObjects;

public class Transform {

	private float x, y; 
	private float rotation;
	private float taille;
	
	public Transform(float x, float y, float rotation, float taille) {
		this.x = x;
		this.y = y;
		this.rotation = rotation;
		this.taille = taille;
	}

	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getTaille() {
		return taille;
	}
	
	public float getRotation() {
		return rotation;
	}

	//angle en radians
	public void addRotation(float angle) {
		rotation+=angle;
	}
	
	//angle en radians
	public void setRotation(float angle) {
		rotation = angle;
	}
	
	public void translate(float x, float y) {
		this.x+=x;
		this.y+=y;
	}
	
}
