package unitGnerators;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.unitgen.UnitGenerator;
import helper.Triggerable;

public class TriggerUnit extends UnitGenerator {

	public UnitInputPort input;
	private Triggerable toTrigger;
	private boolean triggered;

	public String triggerName;

	public TriggerUnit(Triggerable toTrigger, String triggerName) {
		addPort(input = new UnitInputPort("Input"), "Input");
		this.toTrigger = toTrigger;
		this.triggerName = triggerName;
	}

	@Override
	public void generate(int start, int limit) {
		double[] ins = input.getValues();
		for (int i = start; i < limit; i++) {

			if (ins[i] >= 0.1) {
				if (!triggered) {
					toTrigger.triggerOn(triggerName);
					triggered = true;
				} else {
					toTrigger.triggerHold(triggerName);
				}
			} else {
				if (triggered) {
					toTrigger.triggerOff(triggerName);
					triggered = false;
				}
			}
		}
	}

}
