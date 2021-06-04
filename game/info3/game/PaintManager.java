package info3.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import info3.game.graphics.GameCanvas;

public class PaintManager {
	
	static PaintManager instance;
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
	
	public void add(Paintable paintable) {
		paintables.add(paintable);
	}
	
	public void remove(Paintable paintable) {
		paintables.remove(paintable);
	}
	
	public void paint(Graphics g) {
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		// erase background
		g.setColor(Color.gray);
		g.fillRect(0, 0, width, height);
		for (Paintable paintable : paintables) {
			camera.paint(g, width, height, paintable);
		}
	}
}