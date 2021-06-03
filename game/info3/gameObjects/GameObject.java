package info3.gameObjects;

import java.awt.Graphics;

import info3.game.Paintable;
import info3.game.Tickable;

public class GameObject implements Tickable, Paintable, Sauvable {
	
	//classe de base pour tout les objets de notre scene
	//La mod�lisation de cette partie est inspir�e de celle de Unity (en simplifi�e)
	
	public Transform transform; 	//G�re la position, la rotation et la taille de l'objet
	public Rigibody rigibody; 		//optionnnel : int�gre la physique � notre objet
	public Collider collider; 		//optionnel : met des collision � notre objet 

	public GameObject(int x, int y, float angle, float taille){
		this.transform= new Transform(x,y,angle, taille);
	}
	
	public void addRigibody(float mass, float frictionForce) {
		this.rigibody = new Rigibody(transform, mass, collider, frictionForce);
	} 
	
	public void addBoxCollider(float sizeX, float sizeY) {
		this.collider = new BoxCollider(transform, sizeX,sizeY);
	}

	@Override
	public void paint(Graphics g, int width, int height, int cameraPositionX, int cameraPositionY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String sauvegardeString() {
		return "G";
	}
	
}
