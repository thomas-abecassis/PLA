package info3.gameObjects;

public class GameObject {
	
	//classe de base pour tout les objets de notre scene
	//La modélisation de cette partie est inspirée de celle de Unity (en simplifiée)
	
	public Transform transform; 	//Gère la position, la rotation et la taille de l'objet
	public Rigibody rigibody; 		//optionnnel : intègre la physique à notre objet
	public Collider collider; 		//optionnel : met des collision à notre objet 

	public GameObject(int x, int y, float angle, float taille){
		this.transform= new Transform(x,y,angle, taille);
	}
	
	public void addRigibody(float mass) {
		this.rigibody = new Rigibody(transform, mass);
	} 
	
	public void addBoxCollider(float sizeX, float sizeY) {
		this.collider = new BoxCollider(transform, sizeX,sizeY);
	}
	
}
