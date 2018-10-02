package testingInProgress;

import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.Circuit;
import com.jsyn.unitgen.SineOscillator;

public class TESTCIRCUIT extends Circuit{

	public UnitOutputPort out;
	
	public TESTCIRCUIT() {
		addPort(out = new UnitOutputPort("Output"));
		
		
		SineOscillator sine;
		add(sine = new SineOscillator());
		sine.amplitude.set(0.2);
		sine.frequency.set(500);
		out = sine.output;
	}
	
}
