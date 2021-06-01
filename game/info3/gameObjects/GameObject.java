package info3.gameObjects;

public class GameObject {
	
	//classe de base pour tout les objets de notre scene
	//La mod�lisation de cette partie est inspir�e de celle de Unity (en simplifi�e)
	
	private Transform transform; 	//G�re la position, la rotation et la taille de l'objet
	private Rigibody rigibody; 		//optionnnel : int�gre la physique � notre objet
	private Collider collider; 		//optionnel : met des collision � notre objet 

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
