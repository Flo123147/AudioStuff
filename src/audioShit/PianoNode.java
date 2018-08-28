package audioShit;

import nodeSystem.KeyNode;
import nodeSystem.Slider;

public class PianoNode extends KeyNode {

	private AudioOutEntry output;
	private boolean isOit = false;
	private Piano piano;
	private Slider amplitude;
	private Slider rate;

	public PianoNode(int[] pos, String name) {
		super(pos, name);
		addEntry(output = new AudioOutEntry(this, "Output"));
		piano = new Piano();
		piano.output.connect(output.getRightPorts().input);
		System.out.println(piano.output);
		OutputNode.getSynth().add(piano);
		piano.start();

		addEntry(amplitude = new Slider(this, "Amplitude", 0, 1));
		amplitude.getRightPorts().output.connect(piano.amplitude);

		addEntry(rate = new Slider(this, "Falloff", 0, 10));
		rate.getRightPorts().output.connect(piano.falloffspeed);
	}

	@Override
	public void keyOn(char key) {
		key = Character.toLowerCase(key);
		int ntoPlay = 60;
		switch (key) {
		case 'd':
			ntoPlay += 0;
			break;
		case 'r':
			ntoPlay += 1;
			break;
		case 'f':
			ntoPlay += 2;
			break;
		case 't':
			ntoPlay += 3;
			break;
		case 'g':
			ntoPlay += 4;
			break;
		case 'h':
			ntoPlay += 5;
			break;
		case 'u':
			ntoPlay += 6;
			break;
		case 'j':
			ntoPlay += 7;
			break;
		case 'i':
			ntoPlay += 8;
			break;
		case 'k':
			ntoPlay += 9;
			break;
		case 'o':
			ntoPlay += 10;
			break;
		case 'l':
			ntoPlay += 11;
			break;

		default:
			ntoPlay = 0;
			break;
		}

		piano.pressKeyMidi(ntoPlay);
	}

	@Override
	public void keyOff(char Key) {
		char key = Character.toLowerCase(Key);
		int ntoPlay = 60;
		switch (key) {
		case 'd':
			ntoPlay += 0;
			break;
		case 'r':
			ntoPlay += 1;
			break;
		case 'f':
			ntoPlay += 2;
			break;
		case 't':
			ntoPlay += 3;
			break;
		case 'g':
			ntoPlay += 4;
			break;
		case 'h':
			ntoPlay += 5;
			break;
		case 'u':
			ntoPlay += 6;
			break;
		case 'j':
			ntoPlay += 7;
			break;
		case 'i':
			ntoPlay += 8;
			break;
		case 'k':
			ntoPlay += 9;
			break;
		case 'o':
			ntoPlay += 10;
			break;
		case 'l':
			ntoPlay += 11;
			break;

		default:
			ntoPlay = 0;
			break;
		}

		piano.peleaseKeyMidi(ntoPlay);

	}

}
