package unitGnerators;

import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.PassThrough;
import com.jsyn.unitgen.UnitGenerator;
import com.softsynth.shared.time.TimeStamp;

public class ControllerUnit extends UnitGenerator {

	public UnitOutputPort trigger, freq;
	private PassThrough psTrigger, psFreq;

	public ControllerUnit() {
		addPort(trigger = new UnitOutputPort("Trigger"));
		addPort(freq = new UnitOutputPort(UnitGenerator.PORT_NAME_FREQUENCY));

		psTrigger = new PassThrough();
		psFreq = new PassThrough();

//		Window.getSynth().add(psFreq);
//		Window.getSynth().add(psTrigger);

		psTrigger.output = trigger;
		psFreq.output = freq;
	}

	@Override
	public void generate(int start, int limit) {
		psFreq.generate(start, limit);
		psTrigger.generate(start, limit);
	}

	public void trigger(double frequency, double amplitude, TimeStamp timeStamp) {
		psFreq.input.set(frequency, timeStamp);
		psTrigger.input.set(1, timeStamp);
	}

	public void off(TimeStamp timeStamp) {
		psTrigger.input.set(0, timeStamp);
	}

}
