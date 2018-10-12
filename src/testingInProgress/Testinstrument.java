package testingInProgress;

import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.UnitOscillator;

import unitGnerators.SimpleInstument;

public class Testinstrument extends SimpleInstument {

	public UnitOscillator sine;

	public Testinstrument(double freq) {
		super("Test " + freq);
		add(sine = new SineOscillator());
		sine.frequency.set(freq);

		sine.output.connect(outPs.input);

		reader.output.connect(sine.amplitude);
 
	}

	@Override
	public void startYourShit() {

	}

	@Override
	public void play() {
		reader.dataQueue.clear();
		reader.dataQueue.queue(myTestThingy, 0,2);
	}
}
