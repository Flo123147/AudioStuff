package audioShit;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.plaf.synth.SynthButtonUI;

import com.jsyn.data.SegmentedEnvelope;
import com.jsyn.data.SequentialData;
import com.jsyn.midi.MidiConstants;
import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.Circuit;
import com.jsyn.unitgen.Maximum;
import com.jsyn.unitgen.Minimum;
import com.jsyn.unitgen.PassThrough;
import com.jsyn.unitgen.PulseOscillator;
import com.jsyn.unitgen.SawtoothOscillator;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.SquareOscillator;
import com.jsyn.unitgen.TriangleOscillator;
import com.jsyn.unitgen.UnitGenerator;
import com.jsyn.unitgen.UnitOscillator;
import com.jsyn.unitgen.UnitVoice;
import com.jsyn.unitgen.VariableRateMonoReader;
import com.softsynth.shared.time.TimeStamp;

public class Piano extends Circuit implements UnitVoice{
	private Map<Integer, UnitOscillatorReaderPair> keyboardKeys;
	private SegmentedEnvelope falloff, end;
	public UnitOutputPort output;

	private Maximum max;
	private Minimum min;

	private PassThrough psToOut, psAmpli, psFallofRate;
	public UnitInputPort amplitude, falloffspeed;

	private enum EnumUnitTemp {
		Saw, Sine, Tri, Squ, Pulse

	}

	private EnumUnitTemp currentUnit = EnumUnitTemp.Sine;
	private boolean pause;

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
		data = new double[] { 0.02, 0.5, // 0
				0.2, 0.1, // 1
				0.3, 0 };

		end = new SegmentedEnvelope(data);

		initUnitGens();
	}

	/*
	 * Manually set a UnitOscillator for specified Keys.
	 * Only works if Piano is Paused.
	 */
	public void setKeys(Map<Integer, UnitOscillator> newKeys) {
		if (pause) {
			for (Integer key : newKeys.keySet()) {
				keyboardKeys.get(key).ossie = newKeys.get(key);
			}
		}else {
			
		}
	}

	private void initUnitGens() {
		for (int midiKey = 12; midiKey <= 119; midiKey++) {
			UnitOscillator ossie;
			VariableRateMonoReader reader;
			reader = new VariableRateMonoReader();
			keyboardKeys.put(midiKey,
					new UnitOscillatorReaderPair(ossie = getCurrentUnitGen(), reader = new VariableRateMonoReader()));

			OutputNode.getSynth().add(reader);
			OutputNode.getSynth().add(ossie);
			add(ossie);
			add(reader);

			ossie.frequency.set(MidiConstants.convertPitchToFrequency(midiKey));

			psAmpli.output.connect(reader.amplitude);
			reader.output.connect(ossie.amplitude);

			psFallofRate.output.connect(reader.rate);

			ossie.output.connect(psToOut.input);
		}
	}

	private UnitOscillator getCurrentUnitGen() {
		switch (currentUnit) {
		case Saw:
			return new SawtoothOscillator();

		case Sine:
			return new SineOscillator();
		case Squ:
			return new SquareOscillator();
		case Tri:
			return new TriangleOscillator();
		case Pulse:
			return new PulseOscillator();
		}
		return null;
	}

	/*
	 * 69 = A4 60 = C4
	 **/
	public void pressKeyMidi(int midiKey) {

		UnitOscillator ossie;
		VariableRateMonoReader reader;
		System.out.println("play Pitch: " + midiKey + "   " + MidiConstants.convertPitchToFrequency(midiKey));
		if (keyboardKeys.containsKey(midiKey)) {
			ossie = keyboardKeys.get(midiKey).ossie;
			reader = keyboardKeys.get(midiKey).reader;
			reader.dataQueue.clear();
			reader.dataQueue.queue(falloff, 0, falloff.getNumFrames());
		}

	}

	/*
	 * 69 = A4 60 = C4
	 **/
	public void peleaseKeyMidi(int midiKey) {

		System.out.println("stop Pitch: " + midiKey);
		if (keyboardKeys.containsKey(midiKey)) {

			VariableRateMonoReader reader = keyboardKeys.get(midiKey).reader;
			reader.dataQueue.clear();

			if (reader.output.get() >= 0.5 * reader.amplitude.get()) {
				reader.dataQueue.queue(end, 0, 3);
			} else {
				reader.dataQueue.queue(end, 2, 1);
			}
		}

	}

	@Override
	public UnitOutputPort getOutput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void noteOn(double frequency, double amplitude, TimeStamp timeStamp) {
		UnitOscillator ossie;
		VariableRateMonoReader reader;
		System.out.println("play Pitch: " + MidiConstants.convertFrequencyToPitch(frequency) + "   " + frequency);
		int midiKey = (int) MidiConstants.convertFrequencyToPitch(frequency);
		if (keyboardKeys.containsKey(midiKey)) {
			ossie = keyboardKeys.get(midiKey).ossie;
			reader = keyboardKeys.get(midiKey).reader;
			reader.dataQueue.clear();
			reader.dataQueue.queue(falloff, 0, falloff.getNumFrames());
		}
		
	}

	@Override
	public void noteOff(TimeStamp timeStamp) {
//		// TODO Auto-generated method stub
//		
//		int midiKey = (int) MidiConstants.convertFrequencyToPitch(frequency);
//		System.out.println("stop Pitch: " + midiKey);
//		if (keyboardKeys.containsKey(midiKey)) {
//
//			VariableRateMonoReader reader = keyboardKeys.get(midiKey).reader;
//			reader.dataQueue.clear();
//
//			if (reader.output.get() >= 0.5 * reader.amplitude.get()) {
//				reader.dataQueue.queue(end, 0, 3);
//			} else {
//				reader.dataQueue.queue(end, 2, 1);
//			}
//		}
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
