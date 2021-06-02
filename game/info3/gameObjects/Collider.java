package info3.gameObjects;

import info3.game.Paintable;

public abstract class Collider implements Paintable{
	
	private boolean isTrigger; //Si le collider est un trigger il ne va pas empecher les objets à collision de rentrer dedans mais toujours lever une collision
	private boolean enabled;
	
	public abstract Vector2 centre();
	
	public abstract float momentOfInertia(float mass);
	
	public abstract boolean collision(BoxCollider boxCollider);
}
