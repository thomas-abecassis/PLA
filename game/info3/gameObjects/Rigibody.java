package info3.gameObjects;

public class Rigibody {
	
	private boolean isKinematic; //Si le rigibody est affecté par la physique (false c'est un golem qui ne bouge pas)
	private boolean freezeRoation;
	private Transform transform;
	private float xVelocity, yVelocity;
	private float mass;
	
	public Rigibody(Transform transform, float mass) {
		isKinematic = false; 
		freezeRoation = false;
		this.transform = transform;
		xVelocity = 0; yVelocity = 0;
		this.mass = mass;
	}
	
	public void setKinematic(boolean b) {
		isKinematic = b;
	}
	
	public void setFreezeRotation(boolean b) {
		freezeRoation = b;
	}
	
	public void setMass(float mass) {
		this.mass = mass;
	}

}
