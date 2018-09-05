package audioShit;

import com.jsyn.midi.MidiConstants;
import com.jsyn.unitgen.SineOscillator;
import nodeSystem.Slider;
import testingInProgress.ClonableNode;

public class SineOscillatorNode extends ClonableNode {
	private SineOscillator sine;
	private Slider slide;
	private AudioOutEntry out;
	private Slider slideFreq;

	public SineOscillatorNode(int[] pos) {
		super(pos, "Sine Oscillator");
		setUnitGen(sine = new SineOscillator());
		wind.addToSynth(sine);
		sine.start();
		sine.frequency.set(MidiConstants.convertPitchToFrequency(60));

		addEntry(slide = new Slider(this, "Amplitude", 0, 1));
		connect(slide.getRightPorts().output, sine.amplitude);

		addEntry(slideFreq = new Slider(this, "Frequenzy", 0, 40000));
		connect(slideFreq.getRightPorts().output, sine.frequency);

		addEntry(out = new AudioOutEntry(this, "Output"));
		connect(sine.output, out.getRightPorts().input);

	}

	public void setFreq(float freq) {
		slideFreq.setValue(freq);
		sine.frequency.set(freq);
	}

}
