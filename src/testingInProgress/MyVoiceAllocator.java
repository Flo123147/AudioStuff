package testingInProgress;

import java.util.HashMap;

import com.jsyn.Synthesizer;
import com.jsyn.midi.MidiConstants;
import com.jsyn.unitgen.UnitVoice;

public class MyVoiceAllocator {

	private int nrOfVoices;
	UnitVoice[] voices;
	private HashMap<Integer, UnitVoice> ons;
	private HashMap<Integer, UnitVoice> offs;
	private int pointer;
	private Synthesizer synth;

	public MyVoiceAllocator(UnitVoice[] voices) {
		this.voices = voices;
		nrOfVoices = voices.length;
		pointer = 0;
		ons = new HashMap<>();
		offs = new HashMap<>();
	}

	public void noteOff(int pitch) {
		UnitVoice voice = ons.get(pitch);
		if (voice != null)
			ons.get(pitch).noteOff(getSynth().createTimeStamp());
	}

	public void noteOn(int pitch, double amplitude) {
		if (getVoiceWithPitch(pitch) == null) {
			UnitVoice voice = getBestVoice();
			System.out.println(getSynth());
			voice.noteOn(MidiConstants.convertPitchToFrequency(pitch), amplitude, getSynth().createTimeStamp());
			ons.put(pitch, voice);
		}
	}

	private UnitVoice getVoiceWithPitch(int pitch) {
		return ons.get(pitch);
	}


	private UnitVoice getBestVoice() {
		UnitVoice bestVoice = voices[pointer];
		pointer++;

		if (pointer == nrOfVoices) {
			pointer = 0;
		}

		return bestVoice;
	}

	private Synthesizer getSynth() {
		if (synth != null) {
		} else {
			synth = voices[0].getUnitGenerator().getSynthesizer();
		}
		return synth;

	}

}