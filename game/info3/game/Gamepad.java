package info3.game;

import info3.gameObjects.Vector2;
import net.java.games.input.*;
import sun.security.jca.GetInstance.Instance;

public class Gamepad implements Tickable {

	private Controller gamepad;
	private boolean a = false;
	private float leftStickX = 0;
	private float leftStickY = 0;
	private float rightStickY = 0;
	private float rightStickX = 0;
	private float deadZone;
	public static Gamepad instance;

	public Gamepad(float deadZone) {

		instance = this;
		this.deadZone = deadZone;

		/* Get the available controllers */
		Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
		for (int i = 0; i < controllers.length; i++) {
			/* Remember to poll each one */
			controllers[i].poll();

			if (controllers[i].getType() == Controller.Type.GAMEPAD) {
				gamepad = controllers[i];
			}
		}
	}

	public Vector2 getLeftStickPosition() {
		float x = leftStickX;
		float y = leftStickY;
		if ((x < deadZone && x > -deadZone) && (y < deadZone && y > -deadZone)) {
			x = 0;
			y = 0;
		}
		return new Vector2(x, y);
	}

	public Vector2 getRightStickPosition() {
		float x = rightStickX;
		float y = rightStickY;
		if ((x < deadZone && x > -deadZone) && (y < deadZone && y > -deadZone)) {
			x = 0;
			y = 0;	
		}
		return new Vector2(x, y);
	}

	public boolean getInputA() {
		return a;
	}

	public void setDeadZone(float deadZone) {
		this.deadZone = deadZone;
	}

	@Override
	public void tick(long elapsed) {
		if (!gamepad.poll()) {
			System.out.println("perte de suivi de la manette");
		}

		EventQueue eq = gamepad.getEventQueue();
		Event event = new Event();
		Component component;

		while (eq.getNextEvent(event)) {
			component = event.getComponent();
			float value = event.getValue();
			System.out.println(value);

			// clear temporarily stored position if analog stick is in neutral position

			if (component.isAnalog()) {
				// input from analog-sticks and back triggers
				// positive direction
				switch (component.getIdentifier().getName()) {
				case "x":
					leftStickX = value;
					break;
				case "y":
					leftStickY = value;
					break;
				case "rx":
					rightStickX = value;
					break;
				case "ry":
					rightStickY = value;
					break;

				}
			} else {
				// input from buttons
				if (value == 1.0f) {
					switch (component.getIdentifier().getName()) {
					case "A":
						a = true;
						break;
					default:
						break;
					}
				} else if (value == 0f) {
					switch (component.getIdentifier().getName()) {
					case "A":
						a = false;
						break;
					default:
						break;
					}
				}
			}
		}
	}
}
