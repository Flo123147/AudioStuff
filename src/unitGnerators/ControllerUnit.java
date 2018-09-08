package unitGnerators;

import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.UnitGenerator;

public class ControllerUnit extends UnitGenerator{

	public UnitOutputPort trigger,freq;

	public ControllerUnit() {
		addPort(trigger = new UnitOutputPort("Trigger"));
		addPort(freq = new UnitOutputPort("Freq"));
	}

	@Override
	public void generate(int start, int limit) {
		double[] trigs = trigger.getValues();
		double[] freqs = freq.getValues();
		for (int i = start; i < limit; i++) {
			
			
		}
	}



}
