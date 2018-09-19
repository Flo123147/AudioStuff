package nodes;

import display.Window;
import nodeComponents.ButtonComponent;
import nodeSystem.Node;
import unitGnerators.MyMainControllerUnit;

public class MainControllerNode extends Node {

	public MyMainControllerUnit mycontr;

	public MainControllerNode(int[] pos) {
		super(pos, "Main Controller");
		setUnitGenerator(mycontr = new MyMainControllerUnit());

		addNodeComponent("Start Button", new ButtonComponent("Start", 100, 20, this), 0, 0);
		addNodeComponent("Stop Button", new ButtonComponent("Stop", 100, 20, this), 5, 0);

		setAutoPorts(new String[] { "Start", "Stop" });
	}

	@Override
	public void click(String button) {
		switch (button) {
		case "Start":
			mycontr.startPlayback(Window.getSynth().createTimeStamp());
			break;
		case "Stop":
			mycontr.stopPlayback(Window.getSynth().createTimeStamp());
			break;

		default:
			break;
		}

	}

	@Override
	public void event(String event) {
		// TODO Auto-generated method stub

	}

}
