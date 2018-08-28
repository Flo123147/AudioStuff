package nodeSystem;

import display.Window;

public abstract class KeyNode extends Node {

	public KeyNode(int[] pos, String name) {
		super(pos, name);
		
		Window.keyMngr.addKekNode(this);
		
		
	}

	abstract public void keyOn(char key);
	abstract public void keyOff(char Key);
}
