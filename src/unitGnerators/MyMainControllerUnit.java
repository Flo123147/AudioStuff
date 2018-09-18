package unitGnerators;

import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.Circuit;
import com.jsyn.unitgen.PassThrough;
import com.softsynth.shared.time.TimeStamp;

public class MyMainControllerUnit extends Circuit {

	public static double SIGNAL_LENGTH = 0.1;

	public UnitOutputPort start, stop;

	private PassThrough psStart, psStop;

	public MyMainControllerUnit() {
		addPort(start = new UnitOutputPort("Start"));
		addPort(stop = new UnitOutputPort("Stop"));
		add(psStart = new PassThrough());
		add(psStop = new PassThrough());

		psStart.output = start;
		psStop.output = stop;

	}

	public void start(TimeStamp time) {
		psStart.input.set(1, time);
		psStart.input.set(0, time.makeRelative(SIGNAL_LENGTH));
	}

	public void stop(TimeStamp time) {
		psStart.input.set(0, time);
		psStop.input.set(1, time);
		psStop.input.set(1, time.makeRelative(SIGNAL_LENGTH));
	}
}
