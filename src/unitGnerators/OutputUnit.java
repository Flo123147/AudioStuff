package unitGnerators;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.unitgen.Circuit;
import com.jsyn.unitgen.PassThrough;
import com.jsyn.unitgen.SineOscillator;

import display.Window;

public class OutputUnit extends Circuit {

	public UnitInputPort left, right;
	private PassThrough leftPs, rightPs;

	private ScalerUnit amplitudeScalerLeft, amplitudeScalerRight;
	public UnitInputPort volume;
	private PassThrough volumePs;

	public OutputUnit(UnitInputPort mainOut) {
		add(leftPs = new PassThrough());
		add(rightPs = new PassThrough());

		left = leftPs.input;
		right = rightPs.input;

		left.setName("Left");
		right.setName("Right");

		addPort(left);
		addPort(right);

		add(volumePs = new PassThrough());
		volume = new UnitInputPort("Volume");
		volumePs.input = volume;

		add(amplitudeScalerLeft = new ScalerUnit());
		add(amplitudeScalerRight = new ScalerUnit());

		leftPs.output.connect(amplitudeScalerLeft.input);
		rightPs.output.connect(amplitudeScalerRight.input);

		amplitudeScalerLeft.output.connect(0, mainOut, 0);
		amplitudeScalerRight.output.connect(0, mainOut, 1);

		volumePs.output.connect(amplitudeScalerLeft.scale);
		volumePs.output.connect(amplitudeScalerRight.scale);

	}
	
	@Override
	public boolean isStartRequired() {
		return true;
	}

}
