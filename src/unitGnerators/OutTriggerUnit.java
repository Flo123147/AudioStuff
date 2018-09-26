package unitGnerators;

import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.UnitGenerator;

public class OutTriggerUnit extends UnitGenerator {

	private boolean constant, constantOff;;
	private boolean pulse;

	public UnitOutputPort output;

	public OutTriggerUnit() {
		output = new UnitOutputPort();
	}

	@Override
	public void generate(int start, int limit) {
		double[] outs = output.getValues();
		for (int i = start; i < limit; i++) {
			if (pulse) {
				pulse = false;
				outs[i] = TRUE;
			} else if (constant) {
				if (constantOff) {
					constantOff = false;
					outs[i] = FALSE;

				} else {

					outs[i] = TRUE;
				}
			} else {
				outs[i] = FALSE;
			}
		}

	}

	public void pulse() {
		this.pulse = true;
	}

	public void constantOn() {
		this.constant = true;

	}

	public void constantOff() {
		this.constant = false;
		constantOff = true;
	}
}
