package nodes;

import helper.ControlHelper;
import nodeComponents.ButtonComponent;
import nodeSystem.Node;

public class MainControllerNode extends Node {


	public MainControllerNode(int[] pos) {
		super(pos, "Main Controller");

		addNodeComponent("Start Button", new ButtonComponent("Start", 100, 20, this), 0, 0);
		addNodeComponent("Stop Button", new ButtonComponent("Stop", 100, 20, this), 5, 0);

		
		addControlOutPort("Start");
		addControlOutPort("Stop");
		
		setAutoPorts(new String[] { "Start", "Stop" });
	}

	@Override
	public void click(String button,ControlHelper ch) {
		switch (button) {
		case "Start":
			controlPort("Start");
			break;
		case "Stop":
			controlPort("Stop");
			break;

		default:
			break;
		}

	}
	
	@Override
	protected void control(String name) {
		// TODO Auto-generated method stub
		
	}


}
