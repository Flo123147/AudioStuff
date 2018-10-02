package unitGnerators;

import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.Circuit;
import com.jsyn.unitgen.PassThrough;
import com.jsyn.unitgen.UnitSource;
import com.softsynth.shared.time.TimeStamp;

public class MyMainControllerUnit extends Circuit implements UnitSource{

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

	@Override
	public boolean isStartRequired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void startPlayback(TimeStamp time) {
		psStart.input.set(TRUE, time);
		psStart.input.set(FALSE, time.makeRelative(SIGNAL_LENGTH));
//		System.out.println("Starting Playback");
	}

	public void stopPlayback(TimeStamp time) {
		psStart.input.set(FALSE, time);
		psStop.input.set(TRUE, time);
		psStop.input.set(FALSE, time.makeRelative(SIGNAL_LENGTH));
//		System.out.println("Stoping Playback");
	}

	@Override
	public UnitOutputPort getOutput() {
		// TODO Auto-generated method stub
		return start;
	}
}
