package audioShit;

import com.jsyn.unitgen.WhiteNoise;

import display.Window;
import oldNodeSystem.OldNode;
import oldNodeSystem.Slider;

public class WhiteNoiseNode extends OldNode {
	private WhiteNoise noise;
	private Slider slider;
	private AudioOutEntry out;

	public WhiteNoiseNode(int[] pos, String name) {
		super(pos, name);

		noise = new WhiteNoise();

		Window.getSynth().add(noise);

		addEntry(out = new AudioOutEntry(this, getName() + "-Output"));
		noise.output.connect(out.getRightPorts().input);
		
		addEntry(slider = new Slider(this, "Amplitude", 0, 1));
		slider.getRightPorts().output.connect(noise.amplitude);
		slider.setValue(0.01f);
	}
}
