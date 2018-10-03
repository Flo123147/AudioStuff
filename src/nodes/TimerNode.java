package nodes;

import helper.ControlHelper;
import nodeSystem.Node;
import nodeSystem.NodeAudioOutPort;

public class TimerNode extends Node {


	NodeAudioOutPort beatPort;

	public TimerNode(int[] pos) {
		super(pos, "Timer");
	}

	@Override
	public void click(String button,ControlHelper ch) {
		// TODO Auto-generated method stub

	}

}
