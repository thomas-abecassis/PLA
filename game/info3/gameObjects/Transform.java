package info3.gameObjects;

public class Transform {

	private float x, y; 
	private float angle;
	private float taille;
	
	public Transform(float x, float y, float angle, float taille) {
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.taille = taille;
	}

	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void translate(float x, float y) {
		this.x+=x;
		this.y+=y;
	}
	
}
