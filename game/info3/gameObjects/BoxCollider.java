package info3.gameObjects;

public class BoxCollider extends Collider{

	private Transform transform;
	private float size;
	
	public BoxCollider(Transform transform, float size) {
		this.transform = transform;
		this.size = size;
	}
	
}
