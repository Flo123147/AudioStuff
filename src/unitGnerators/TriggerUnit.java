package unitGnerators;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.unitgen.UnitGenerator;
import com.jsyn.unitgen.UnitSink;

import audioShit.TriggerEntry;

public class TriggerUnit extends UnitGenerator implements UnitSink {

	public UnitInputPort input;
	private TriggerEntry te;
	private boolean triggered;

	public TriggerUnit(TriggerEntry triggerEntry) {
		addPort(input = new UnitInputPort("Input"), "Input");
		te = triggerEntry;

	}

	@Override
	public void generate(int start, int limit) {
		double[] ins = input.getValues();
		for (int i = start; i < limit; i++) {
			if (ins[i] >= 0.1) {
				if (!triggered) {
					te.triggerOn();
					triggered = true;
				}else {
					te.triggerHold();
				}
			} else {
				if (triggered) {
					te.triggerOff();
					triggered = false;
				}
			}
		}
	}

	@Override
	public UnitInputPort getInput() {
		// TODO Auto-generated method stub
		return getInput();
	}

}
