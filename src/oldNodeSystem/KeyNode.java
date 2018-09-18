package oldNodeSystem;

import display.View;
import display.Window;

public abstract class KeyNode extends OldNode {

	public KeyNode(int[] pos, String name) {
		super(pos, name);

		Window.keyMngr.addKekNode(this);

	}

	abstract public void keyOn(char key);

	abstract public void keyOff(char Key);
}
