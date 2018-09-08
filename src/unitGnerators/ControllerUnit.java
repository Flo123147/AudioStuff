package unitGnerators;

import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.PassThrough;
import com.jsyn.unitgen.UnitGenerator;
import com.softsynth.shared.time.TimeStamp;

public class ControllerUnit extends UnitGenerator{

	public UnitOutputPort trigger,freq;
	private PassThrough psTrigger,psFreq;
	public ControllerUnit() {
		addPort(trigger = new UnitOutputPort("Trigger"));
		addPort(freq = new UnitOutputPort("Freq"));
		
		psTrigger = new PassThrough();
		psFreq = new PassThrough();
		
		psTrigger.output = trigger;
		psFreq.output = freq;
	}

	@Override
	public void generate(int start, int limit) {
//		double[] trigs = trigger.getValues();
//		double[] freqs = freq.getValues();
//		for (int i = start; i < limit; i++) {
//			
//			
//		}
	}

	public void on(double frequency, double amplitude, TimeStamp timeStamp) {
		psTrigger.input.set(1, timeStamp);
	}

	public void off(TimeStamp timeStamp) {
		psTrigger.input.set(0, timeStamp);
	}



}
