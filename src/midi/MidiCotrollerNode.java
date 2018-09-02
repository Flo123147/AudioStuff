package midi;

import java.awt.Graphics2D;

import audioShit.AudioOutEntry;
import nodeSystem.Node;

public class MidiCotrollerNode extends Node {

	private AudioOutEntry trigger;
	private AudioOutEntry freq;

	public MidiCotrollerNode(int[] pos, String name) {
		super(pos, name);
		addEntry(trigger = new AudioOutEntry(this, "Trigger"));
		addEntry(freq = new AudioOutEntry(this, "Frequenzy"));
		freq.getRightPorts().input.set(440);
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		super.draw(g, x, y);
	}
}
