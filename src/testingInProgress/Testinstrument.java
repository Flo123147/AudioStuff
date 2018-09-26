package testingInProgress;

import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.TriangleOscillator;
import com.jsyn.unitgen.UnitOscillator;

import nodes.SimpleInstument;

public class Testinstrument extends SimpleInstument {

	public UnitOscillator sine2, sine1;
//	private SineOscillator sine1;

	public Testinstrument() {
		super("Test");
		add(sine2 = new SineOscillator());
//		add(sine1 = new SineOscillator());
//		sine1.frequency.set(20);
		sine2.frequency.set(440);

		sine2.output.connect(outPs.input);
//		sine1.output.connect(sine2.amplitude);

		reader.output.connect(sine2.amplitude);

	}

	@Override
	public void startYourShit() {

	}

}
