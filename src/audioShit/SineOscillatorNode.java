package audioShit;

import java.awt.Graphics2D;

import com.jsyn.unitgen.SineOscillator;
import nodeSystem.Node;
import nodeSystem.Slider;

public class SineOscillatorNode extends Node {
	private SineOscillator sine;
	private Slider slide;
	private AudioOutEntry out;
	private Slider slideFreq;

	public SineOscillatorNode(int[] pos) {
		super(pos, "Sine Oscillator");
		sine = new SineOscillator();
		MidiOutputNode.getSynth().add(sine);
		sine.start();
		sine.frequency.set(3);

		addEntry(slide = new Slider(this, "Amplitude", -1, 1));
		slide.getRightPorts().output.connect(sine.amplitude);
		
		addEntry(slideFreq = new Slider(this, "Frequenzy", 0, 40000));
		slideFreq.getRightPorts().output.connect(sine.frequency);

		addEntry(out = new AudioOutEntry(this, "Output"));
		sine.output.connect(out.getRightPorts().input);

		
	}

	public void setFreq(float freq) {
		slideFreq.setValue(freq);
		sine.frequency.set(freq);
	}

//	@Override
//	protected void draw(Graphics2D g, int x, int y) {
////		System.out.println(getName() + "    " + sine.frequency.get() + "   " + sine.amplitude.getValue());
//		super.draw(g, x, y);
//	}
}
