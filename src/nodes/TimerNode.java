package nodes;

import helper.ControlHelper;
import nodeSystem.Node;
import nodeSystem.NodeOutPort;
import unitGnerators.TimerUnit;

public class TimerNode extends Node {

	public TimerUnit time;

	NodeOutPort beatPort;

	public TimerNode(int[] pos) {
		super(pos, "Timer");
		setUnitGenerator(time = new TimerUnit(0, this));
	}

	@Override
	public void click(String button,ControlHelper ch) {
		// TODO Auto-generated method stub

	}

	@Override
	public void event(String event) {
		if (event == TimerUnit.BEAT_EVENT) {
			if (beatPort == null)
				beatPort = getOutPort(TimerUnit.PORT_NAME_BEAT);

			beatPort.triggerRed();
		}
	}

}
