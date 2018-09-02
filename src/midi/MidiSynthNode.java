package midi;

import com.jsyn.instruments.WaveShapingVoice;
import com.jsyn.midi.MidiSynthesizer;
import com.jsyn.util.MultiChannelSynthesizer;

import audioShit.AudioOutEntry;
import display.Window;
import nodeSystem.Node;

public class MidiSynthNode extends Node {
	private MidiSynthesizer midisynth;
	private MultiChannelSynthesizer multiSynth;
	private AudioOutEntry left, right;

	public MidiSynthNode(int[] pos, String name) {
		super(pos, name);

		multiSynth = new MultiChannelSynthesizer();
		multiSynth.setup(Window.getSynth(), 0, 1, 6, WaveShapingVoice.getVoiceDescription());
		midisynth = new MidiSynthesizer(multiSynth);

		addEntry(left = new AudioOutEntry(this, "Left"));
		addEntry(right = new AudioOutEntry(this, "Right"));

		multiSynth.getOutput().connect(0, left.getRightPorts().input, 0);
		multiSynth.getOutput().connect(1, right.getRightPorts().input, 0);

	}

	public MidiSynthesizer getMidisynth() {
		return midisynth;
	}

	public void noteOn(int pitch) {
		midisynth.noteOn(0, pitch, 100);
	}

	public void noteOff(int pitch) {
		midisynth.noteOff(0, pitch, 100);
	}
}
