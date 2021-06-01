package info3.gameObjects;

public class GameObject {
	
	//classe de base pour tout les objets de notre scene
	//La modélisation de cette partie est inspirée de celle de Unity (en simplifiée)
	
	private Transform transform; 	//Gère la position, la rotation et la taille de l'objet
	private Rigibody rigibody; 		//optionnnel : intègre la physique à notre objet
	private Collider collider; 		//optionnel : met des collision à notre objet 

	GameObject(int x, int y, float angle, float taille){
		this.transform= new Transform(x,y,angle, taille);
	}
	
	public void addRigibody(float mass) {
		this.rigibody = new Rigibody(transform, mass);
	} 
	
	public void addBoxCollider(float size) {
		this.collider = new BoxCollider(transform, size);
	}
	
}
