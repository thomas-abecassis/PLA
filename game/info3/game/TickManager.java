package info3.game;

import java.util.ArrayList;

public class TickManager {
	
	static TickManager instance;
	private ArrayList<Tickable> tickables;
	
	public TickManager() {
		if(instance !=null)
			return;
		tickables = new ArrayList<Tickable>();
		instance = this;
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
