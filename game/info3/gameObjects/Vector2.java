package info3.gameObjects;

public class Vector2 {
	public float x;
	public float y;
	
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2 rotate(float angle) {
		return new Vector2((float)(Math.cos(angle)*x - Math.sin(angle)*y) ,(float)( Math.sin(angle)*x + Math.cos(angle)*y));
	}
}
