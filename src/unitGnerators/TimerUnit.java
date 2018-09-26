package unitGnerators;

import com.jsyn.ports.UnitOutputPort;
import display.Window;
import nodeSystem.Node;

public class TimerUnit extends MyBaseUnit {

	public static final String RESET = "reset";
	public static final String STOP = "stop";
	public static final String BEAT_EVENT = "beatEvent";
	public static final String PORT_NAME_BEAT = "Beat";
	public UnitOutputPort[] triggers;
	public UnitOutputPort beat;

	private int samplesPerSecond;
	private int samplesPerBeat;
	private int counter;

	private boolean playing;

	public TimerUnit(int howMany, Node node) {

		samplesPerSecond = Window.getSynth().getFrameRate();
		int samplesPerMin = samplesPerSecond * 60;
		samplesPerBeat = samplesPerMin / 120;

		triggers = new UnitOutputPort[howMany];
		addPort(beat = new UnitOutputPort(PORT_NAME_BEAT));

		for (int i = 0; i < howMany; i++) {
			triggers[i] = new UnitOutputPort("Every " + (i + 1) + " Measures");
			addPort(triggers[i]);
		}

		addTrigger("Reset", RESET);
		addTrigger("Stop", STOP);
	}

	@Override
	public void generate(int start, int limit) {
		super.generate(start, limit);
		double[] beats = beat.getValues();

		for (int i = start; i < limit; i++) {
			if (playing) {
				counter++;
				if (counter >= samplesPerBeat) {
					counter = 0;

					beats[i] = TRUE;
					node.event(BEAT_EVENT);
				} else {
					beats[i] = FALSE;
				}
			}

		}
	}

	@Override
	public void triggerOn(String triggerName) {
		switch (triggerName) {
		case STOP:
			playing = false;

		case RESET:
			counter = samplesPerBeat;
			break;
		default:
			break;
		}
		super.triggerOn(triggerName);
	}

	@Override
	protected void baseTrigger() {
		counter = samplesPerBeat;
		playing = true;
	}

}
