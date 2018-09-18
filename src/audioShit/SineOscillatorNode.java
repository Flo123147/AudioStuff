package audioShit;

import com.jsyn.midi.MidiConstants;
import com.jsyn.unitgen.SineOscillator;

import oldEntries.AudioOutEntry;
import oldNodeSystem.ClonableNode;
import oldNodeSystem.Slider;
import unitGnerators.MyOscillator;

public class SineOscillatorNode extends ClonableNode {
	private SineOscillator sine;

	public Slider slideAmplitude;
	public AudioOutEntry out;
	public Slider slideFreq;

	public SineOscillatorNode(int[] pos) {
		super(pos, "Sine Oscillator");
		setUnitGen(sine = new MyOscillator());
		sine.setEnabled(false);
		wind.addToSynth(sine);

		sine.frequency.set(MidiConstants.convertPitchToFrequency(60));

		addEntry(slideAmplitude = new Slider(this, "Amplitude", 0, 1));
		connectInwards(slideAmplitude, sine.amplitude);

		addEntry(slideFreq = new Slider(this, "Frequenzy", 0, 40000));
		connectInwards(slideFreq, sine.frequency);

		addEntry(out = new AudioOutEntry(this, "Output"));
		connectOutwards(sine.output, out);
	}

	public void setFreq(float freq) {
		slideFreq.setValue(freq);
		sine.frequency.set(freq);
	}

}
