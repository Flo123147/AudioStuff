package audioShit;

import nodeSystem.Node;

import java.awt.Graphics2D;

import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.midi.MidiConstants;
import com.jsyn.midi.MidiSynthesizer;
import com.jsyn.unitgen.LineOut;
import com.jsyn.util.MultiChannelSynthesizer;

public class OutputNode extends Node {

	private static Synthesizer synth;
	private LineOut out;
	private AudioInEntry left, right;


	public OutputNode(int[] pos, String name) {
		super(pos, name);

		synth = JSyn.createSynthesizer();
		synth.add(out = new LineOut());

		addEntry(left = new AudioInEntry(this, "Left"));
		addEntry(right = new AudioInEntry(this, "Right"));

		left.getLeftPorts().output.connect(0, out.input, 0);
		right.getLeftPorts().output.connect(0, out.input, 1);
		
		
		synth.start();
		out.start();
	}

	public void stop() {
		synth.stop();

	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		if (!synth.isRunning()) {
			wind.jSynthStopped();
			setName("JSYNTH HAS STOPPED");
		}
		super.draw(g, x, y);
	}

	public static Synthesizer getSynth() {
		// TODO Auto-generated method stub
		return OutputNode.synth;
	}



}
