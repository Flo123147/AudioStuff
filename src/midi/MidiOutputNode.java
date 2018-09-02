package midi;

import nodeSystem.Node;

import java.awt.Graphics2D;

import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.midi.MidiConstants;
import com.jsyn.midi.MidiSynthesizer;
import com.jsyn.ports.UnitInputPort;
import com.jsyn.unitgen.LineOut;
import com.jsyn.util.MultiChannelSynthesizer;

import audioShit.AudioInEntry;

public class MidiOutputNode extends Node {

	private AudioInEntry left, right;

	public MidiOutputNode(int[] pos, String name) {
		super(pos, name);

		addEntry(left = new AudioInEntry(this, "Left"));
		addEntry(right = new AudioInEntry(this, "Right"));
		UnitInputPort out = wind.getmainOutput();
		left.getLeftPorts().output.connect(0, out, 0);
		right.getLeftPorts().output.connect(0, out, 1);
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		super.draw(g, x, y);
	}

}
