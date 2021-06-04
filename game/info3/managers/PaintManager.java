package info3.managers;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import info3.game.Camera;
import info3.game.Paintable;
import info3.game.graphics.GameCanvas;

public class PaintManager {
	
	public static PaintManager instance;
	private ArrayList<Paintable> paintables;
	private Camera camera;
	private GameCanvas canvas;
	
	public PaintManager(Camera camera, GameCanvas gameCanvas) {
		if(instance !=null)
			return;
		paintables = new ArrayList<Paintable>();
		instance = this;
		this.camera = camera;
		this.canvas = gameCanvas;
	}
	
	public void reset() {
		paintables = new ArrayList<Paintable>();
	}
	
	public void setPaintables(ArrayList<Paintable> paintables) {
		this.paintables = paintables;
	}
	
	public void add(Paintable paintable) {
		paintables.add(paintable);
	}
	
	public void remove(Paintable paintable) {
		paintables.remove(paintable);
	}
	
	public synchronized void paint(Graphics g) {
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		// erase background
		g.setColor(Color.gray);
		g.fillRect(0, 0, width, height);
		for (int i=0; i < paintables.size(); i++) {
			camera.paint(g, width, height, paintables.get(i));
		}
	}
}
