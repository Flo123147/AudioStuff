package audioShit;

import helper.ValueContainer;
import nodeSystem.KeyNode;
import nodeSystem.Slider;

public class PianoNode extends KeyNode {

	private AudioOutEntry output;
	private boolean isOit = false;
	private Piano piano;
	private Slider amplitude;
	private Slider rate;
	private ValueContainer octaveOffsetC;
	private PlusMinusEntry plusMinus;

	public PianoNode(int[] pos, String name) {
		super(pos, name);
		addEntry(output = new AudioOutEntry(this, "Output"));
		piano = new Piano();
		piano.output.connect(output.getRightPorts().input);
		System.out.println(piano.output);
		OutputNode.getSynth().add(piano);
		piano.start();

		
		octaveOffsetC = new ValueContainer();
		octaveOffsetC.value = 4;
		
		addEntry(amplitude = new Slider(this, "Amplitude", 0, 0.2));
		amplitude.getRightPorts().output.connect(piano.amplitude);

		addEntry(rate = new Slider(this, "Falloff", 0, 10));
		rate.getRightPorts().output.connect(piano.falloffspeed);
		
		addEntry(plusMinus = new PlusMinusEntry(this, "Octave",1,octaveOffsetC));
	}

	@Override
	public void keyOn(char key) {

		int ntoPlay = getNoteToPlay(key);

		if (ntoPlay != -1)
			piano.pressKeyMidi(ntoPlay);
	}
	
	//0 = 4
	public void setOctaveOffset(int octaveOffset) {
		this.octaveOffsetC.value = octaveOffset;
	}

	private int getNoteToPlay(char key) {
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
			ntoPlay = -1;
			break;
		}

		return (int) (ntoPlay + (octaveOffsetC.value-4) * 12);
	}

	@Override
	public void keyOff(char Key) {
		int ntoPlay = getNoteToPlay(Key);

		piano.peleaseKeyMidi(ntoPlay);

	}

}
