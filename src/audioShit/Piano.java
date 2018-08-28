package audioShit;

import java.util.HashMap;
import java.util.Map;

import com.jsyn.data.SegmentedEnvelope;
import com.jsyn.midi.MidiConstants;
import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.Circuit;
import com.jsyn.unitgen.PassThrough;
import com.jsyn.unitgen.SawtoothOscillator;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.TriangleOscillator;
import com.jsyn.unitgen.UnitOscillator;
import com.jsyn.unitgen.VariableRateMonoReader;

public class Piano extends Circuit {
	private Map<Integer, UnitOscillatorReaderPair> keyboardKeys;
	private SegmentedEnvelope falloff;
	public UnitOutputPort output;

	private PassThrough psToOut, psAmpli, psFallofRate;
//	private float baseFreq = 440f;
	public UnitInputPort amplitude, falloffspeed;

	public Piano() {
		addPort(output = new UnitOutputPort("Piano Out"));
		addPort(amplitude = new UnitInputPort("Amplitude"));
		addPort(falloffspeed = new UnitInputPort("Fallof Rate"));

		keyboardKeys = new HashMap<>();

		add(psToOut = new PassThrough());
		OutputNode.getSynth().add(psToOut);
		psToOut.output = output;

		add(psAmpli = new PassThrough());
		OutputNode.getSynth().add(psAmpli);
		psAmpli.input = amplitude;

		add(psFallofRate = new PassThrough());
		OutputNode.getSynth().add(psFallofRate);
		psFallofRate.input = falloffspeed;

		double[] data = { 0.02, 1.0, // 0
				0.2, 0.9, // 1
				0.4, 0.8, // 2
				0.6, 0.6, // 3
				0.8, 0.2, // 4
				1.0, 0.0,// 5
		};

		falloff = new SegmentedEnvelope(data);
	}

	/*
	 * 69 = A4 60 = C4
	 **/
	public void pressKeyMidi(int midiKey) {
		UnitOscillator ossie;
		VariableRateMonoReader reader;
		System.out.println("play Pitch: " + midiKey);
		if (!keyboardKeys.containsKey(midiKey)) {
			keyboardKeys.put(midiKey,
					new UnitOscillatorReaderPair(ossie = new TriangleOscillator(), reader = new VariableRateMonoReader()));

			OutputNode.getSynth().add(reader);
			OutputNode.getSynth().add(ossie);
			add(ossie);
			add(reader);

			ossie.frequency.set(MidiConstants.convertPitchToFrequency(midiKey));

			psAmpli.output.connect(reader.amplitude);
			reader.output.connect(ossie.amplitude);

			psFallofRate.output.connect(reader.rate);

			ossie.output.connect(psToOut.input);

			ossie.start();
			reader.start();

		} else {
			ossie = keyboardKeys.get(midiKey).ossie;
			reader = keyboardKeys.get(midiKey).reader;
		}

		reader.dataQueue.clear();
		reader.dataQueue.queue(falloff, 0, 2);
		reader.dataQueue.queueLoop(falloff, 0, 2);
	}

	/*
	 * 69 = A4 60 = C4
	 **/
	public void peleaseKeyMidi(int midiKey) {
		System.out.println("stop Pitch: " + midiKey);
		if (keyboardKeys.containsKey(midiKey)) {

			VariableRateMonoReader reader = keyboardKeys.get(midiKey).reader;
			reader.dataQueue.queue(falloff, 2, 4);
		}

	}
}

class UnitOscillatorReaderPair {
	public UnitOscillatorReaderPair(UnitOscillator ossie, VariableRateMonoReader reader) {
		this.ossie = ossie;
		this.reader = reader;
	}

	public UnitOscillator ossie;
	public VariableRateMonoReader reader;
}
