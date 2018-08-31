package audioShit;

import com.jsyn.unitgen.WhiteNoise;

import nodeSystem.Node;
import nodeSystem.Slider;

public class WhiteNoiseNode extends Node {
	private WhiteNoise noise;
	private Slider slider;
	private AudioOutEntry out;

	public WhiteNoiseNode(int[] pos, String name) {
		super(pos, name);

		noise = new WhiteNoise();

		MidiOutputNode.getSynth().add(noise);

		addEntry(out = new AudioOutEntry(this, getName() + "-Output"));
		noise.output.connect(out.getRightPorts().input);
		
		addEntry(slider = new Slider(this, "Amplitude", 0, 1));
		slider.getRightPorts().output.connect(noise.amplitude);
		slider.setValue(0.01f);
	}
}
