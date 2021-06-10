package info3.gameObjects;

public class Rigibody {
	
	private boolean isKinematic; //Si le rigibody est affect� par la physique (false c'est un golem qui ne bouge pas)
	private boolean freezeRoation;
	private Transform transform;
	private float xVelocity, yVelocity;
	private float mass;
	private Vector2 force;
	private Collider collider;
	private float torque;
	private float angularVelocity;
	private float frictionForce;
	
	public Rigibody(Transform transform, float mass, Collider collider, float frictionForce) {
		isKinematic = false; 
		freezeRoation = false;
		this.transform = transform;
		xVelocity = 0; yVelocity = 0;
		this.mass = mass;
		this.force = new Vector2(0, 0);
		this.collider = collider;
		this.frictionForce = frictionForce;
	}
	
	public void setVelocity(float x, float y) {
		xVelocity = x;
		yVelocity = y;
	}
	
	public void setVelocity(Vector2 vect) {
		xVelocity = vect.x;
		yVelocity = vect.y;
	}
	
	public void setVelocityX(float x) {
		this.xVelocity=x;
	}
	
	public void setVelocityY(float y) {
		this.yVelocity = y;
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
	
	//very simple frictions managment for now
	private void frictions(float deltaTime) {
		
		if(Math.abs(xVelocity) <= 0.2)
			xVelocity = 0;
		else {
			if(xVelocity>0.2)
				xVelocity -= deltaTime/1000 * 400 * frictionForce;
			else if(xVelocity <0.2)
				xVelocity += deltaTime/1000 * 400 * frictionForce;
		}
		
		if(Math.abs(yVelocity) <= 0.2)
			yVelocity = 0;
		else {
			if(yVelocity>0.2)
				yVelocity -= deltaTime/1000 * 400 * frictionForce;
			else if(yVelocity<0.2)
				yVelocity += deltaTime/1000 * 400 * frictionForce;
		}
	}
	
	public void computeMovement(float deltaTime) {
		frictions(deltaTime);
		if(!isKinematic) {
			deltaTime = deltaTime/1000; //ms to second
			Vector2 centre = collider.centre();
			Vector2 r = new Vector2(0, 0);
			torque = r.x * force.y + r.y * force.y;
			
			Vector2 acceleration = new Vector2(force.x/mass, force.y/mass);
			xVelocity +=acceleration.x * deltaTime;
			yVelocity += acceleration.y * deltaTime;
			transform.translate(xVelocity * deltaTime, yVelocity * deltaTime);
			
			float angularAcceleration = torque/collider.momentOfInertia(mass);
			angularVelocity+= angularAcceleration * deltaTime;
			transform.addRotation(angularVelocity * deltaTime);
		}
	}

}
