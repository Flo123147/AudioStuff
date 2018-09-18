package helper;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import oldNodeSystem.KeyNode;

public class KeyMngr implements KeyListener {

	private LinkedList<KeyNode> keyrecievers;

	private boolean[][] keys;

	public KeyMngr() {
		keyrecievers = new LinkedList<>();
		keys = new boolean[255][2];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()][0] = true;
		send((char) e.getKeyCode());

	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()][0] = false;
		send((char) e.getKeyCode());

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	private void send(char key) {

		if (keys[key][0]) {
			if (!keys[key][1]) {
				keys[key][1] = true;
				for (KeyNode kn : keyrecievers) {
					kn.keyOn(key);
				}
			}
		} else {
			if (keys[key][1]) {
				keys[key][1] = false;
				for (KeyNode kn : keyrecievers) {
					kn.keyOff(key);
				}
			}
		}
	}

	public void addKekNode(KeyNode keyNode) {
		keyrecievers.add(keyNode);
	}

}
