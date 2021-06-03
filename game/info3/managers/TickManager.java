package info3.managers;

import java.util.ArrayList;

import info3.game.Tickable;

public class TickManager {
	
	public static TickManager instance;
	private ArrayList<Tickable> tickables;
	
	public TickManager() {
		if(instance !=null)
			return;
		tickables = new ArrayList<Tickable>();
		instance = this;
	}
	
	public void setTickables(ArrayList<Tickable> tickables) {
		this.tickables = tickables;
	}
	
	public void reset() {
		tickables = new ArrayList<Tickable>();
	}
	
	public void add(Tickable tickable) {
		tickables.add(tickable);
	}
	
	public void remove(Tickable tickable) {
		tickables.remove(tickable);
	}
	
	public void tick(long elapsed) {
		for (Tickable tickable : tickables) {
			tickable.tick(elapsed);
		}
	}
	
}
