package audioShit;

import com.jsyn.data.SegmentedEnvelope;
import com.jsyn.ports.UnitInputPort;
import com.jsyn.unitgen.VariableRateMonoReader;

public class MyVarRateReader extends VariableRateMonoReader {
	public UnitInputPort trigger;
	private boolean triggered;
	private SegmentedEnvelope myTestThingy;

	public MyVarRateReader() {
		super();
		addPort(trigger = new UnitInputPort("Trigger"));

		double[] data = { 0.02, 1, 0.3, 0.8, 0.5, 0.7, 0.7, 0.55, 0.9, 0.4, 1.1, 0.3, 1.3, 0.2, 1.5, 0.1, 1.9, 0 };
		myTestThingy = new SegmentedEnvelope(data);
		amplitude.set(0.1);
		rate.set(1);
	}

	@Override
	public void generate(int start, int limit) {
		double[] ins = trigger.getValues();
		for (int i = start; i < limit; i++) {
			if (ins[i] >= 0.1) {
				if (!triggered) {
					triggerOn();
					triggered = true;
				} else {
					triggerHold();
				}
			} else {
				if (triggered) {
					triggerOff();
					triggered = false;
				}
			}
		}
		super.generate(start, limit);
	}

	public void triggerOn() {
		rate.set(1);
		dataQueue.clear();
		dataQueue.queue(myTestThingy, 0, 9);
	}

	public void triggerHold() {

	}

	public void triggerOff() {
		if (dataQueue.hasMore()) {
			dataQueue.clear();
			rate.set(8);
			dataQueue.queue(myTestThingy, 7, 2);
		}
	}
}
