package audioShit;

import com.jsyn.instruments.WaveShapingVoice;
import com.jsyn.midi.MidiSynthesizer;
import com.jsyn.util.MultiChannelSynthesizer;
import nodeSystem.Node;

public class MidiSynthNode extends Node {
	private MidiSynthesizer midisynth;
	private MultiChannelSynthesizer multiSynth;
	private AudioOutEntry left, right;

	public MidiSynthNode(int[] pos, String name) {
		super(pos, name);

		multiSynth = new MultiChannelSynthesizer();
		System.out.println(OutputNode.getSynth());
		multiSynth.setup(OutputNode.getSynth(), 0, 16, 6, WaveShapingVoice.getVoiceDescription());
		midisynth = new MidiSynthesizer(multiSynth);

		addEntry(left = new AudioOutEntry(this, "Left"));
		addEntry(right = new AudioOutEntry(this, "Right"));

		multiSynth.getOutput().connect(0, left.getRightPorts().input, 0);
		multiSynth.getOutput().connect(1, right.getRightPorts().input, 0);

	

	}

	

	public MidiSynthesizer getMidisynth() {
		return midisynth;
	}
}
