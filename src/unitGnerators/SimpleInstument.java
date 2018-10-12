package unitGnerators;

import com.jsyn.data.SegmentedEnvelope;
import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.Circuit;
import com.jsyn.unitgen.PassThrough;
import com.jsyn.unitgen.VariableRateMonoReader;

public abstract class SimpleInstument extends Circuit {

	public UnitInputPort amplitude;
	public UnitOutputPort output;

	private String name;
	public VariableRateMonoReader reader;
	protected SegmentedEnvelope myTestThingy;
	protected PassThrough outPs;
	public ScalerUnit amplScaler;

	public SimpleInstument(String name) {
		this.name = name;

		add(amplScaler = new ScalerUnit());

		add(reader = new VariableRateMonoReader());
		add(outPs = new PassThrough());

		outPs.output.connect(amplScaler.input);

		output = amplScaler.output;
		addPort(output);

		reader.amplitude.set(0.1);

		double[] data = { 0.02, 1, 3, 0 };
		myTestThingy = new SegmentedEnvelope(data);

		reader.rate.set(1);
	}

	public void initSimpleInstrument() {
		startYourShit();
		reader.start();
	}

	public abstract void startYourShit();

	public String getName() {
		return name;
	}

	public abstract void play();

}
