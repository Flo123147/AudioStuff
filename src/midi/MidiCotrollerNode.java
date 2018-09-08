package midi;

import java.awt.Graphics2D;

import audioShit.AudioOutEntry;
import nodeSystem.Node;
import testingInProgress.ClonableNode;
import unitGnerators.ControllerUnit;

public class MidiCotrollerNode extends ClonableNode {

	private AudioOutEntry trigger;
	private AudioOutEntry freq;
	private ControllerUnit controller;

	public MidiCotrollerNode(int[] pos, String name) {
		super(pos, name);
		addEntry(trigger = new AudioOutEntry(this, "Trigger"));
		addEntry(freq = new AudioOutEntry(this, "Frequenzy"));
		freq.getRightPorts().input.set(440);

		setUnitGen(controller = new ControllerUnit());

		connect(controller.freq, freq.getRightPorts().input);
		connect(controller.trigger, trigger.getRightPorts().input);
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		super.draw(g, x, y);
	}
}
