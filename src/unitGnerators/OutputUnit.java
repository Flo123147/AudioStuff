package unitGnerators;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.unitgen.Circuit;
import com.jsyn.unitgen.PassThrough;

public class OutputUnit extends Circuit {

	public UnitInputPort left, right;
	private PassThrough leftPs, rightPs;

	private AmplitudeScalerUnit amplitudeScalerLeft, amplitudeScalerRight;
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

		add(amplitudeScalerLeft = new AmplitudeScalerUnit());
		add(amplitudeScalerRight = new AmplitudeScalerUnit());

		leftPs.output.connect(amplitudeScalerLeft.input);
		rightPs.output.connect(amplitudeScalerRight.input);

		amplitudeScalerLeft.output.connect(0, mainOut, 0);
		amplitudeScalerRight.output.connect(0, mainOut, 1);

		volumePs.output.connect(amplitudeScalerLeft.scale);
		volumePs.output.connect(amplitudeScalerRight.scale);
	}

}
