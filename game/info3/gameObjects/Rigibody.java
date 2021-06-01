package info3.gameObjects;

public class Rigibody {
	
	private boolean isKinematic; //Si le rigibody est affecté par la physique (false c'est un golem qui ne bouge pas)
	private boolean freezeRoation;
	private Transform transform;
	private float xVelocity, yVelocity;
	private float mass;
	private Vector2 force;
	
	public Rigibody(Transform transform, float mass) {
		isKinematic = false; 
		freezeRoation = false;
		this.transform = transform;
		xVelocity = 0; yVelocity = 0;
		this.mass = mass;
		this.force = new Vector2(0, 9.81F);
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
	
	public void computeMovement(float deltaTime) {
		if(!isKinematic) {
			deltaTime = deltaTime/1000; //ms to second
			Vector2 acceleration = new Vector2(force.x/mass, force.y/mass);
			xVelocity +=acceleration.x * deltaTime;
			yVelocity += acceleration.y * deltaTime;
			transform.translate(xVelocity, yVelocity);
		}
	}

}
