package audioShit;

import com.jsyn.midi.MidiConstants;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.SineOscillator;

import nodeSystem.ClonableNode;
import nodeSystem.Slider;

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
		connectInwards(slide, sine.amplitude);

		addEntry(slideFreq = new Slider(this, "Frequenzy", 0, 40000));
		connectInwards(slideFreq, sine.frequency);

		addEntry(out = new AudioOutEntry(this, "Output"));
		connectOutwards(sine.output,out);
	}



	public void setFreq(float freq) {
		slideFreq.setValue(freq);
		sine.frequency.set(freq);
	}

}
