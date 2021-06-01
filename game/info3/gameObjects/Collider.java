package info3.gameObjects;


public abstract class Collider {
	
	private boolean isTrigger; //Si le collider est un trigger il ne va pas empecher les objets à collision de rentrer dedans mais toujours lever une collision
	private boolean enabled;
}
