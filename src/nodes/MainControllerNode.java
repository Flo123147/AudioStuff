package nodes;

import nodeComponents.ButtonComponent;
import nodeSystem.Node;
import unitGnerators.MyMainControllerUnit;

public class MainControllerNode extends Node{
	
	public MainControllerNode(int[] pos) {
		super(pos, "Main Controller");
		setUnitGenerator(new MyMainControllerUnit());
		
		addNodeComponent("Start Button", new ButtonComponent("Start", 100, 20, this), 0, 0);
	}

	
	
	
	
	@Override
	public void click(String button) {
		switch (button) {
		case "Start":
			
			break;

		default:
			break;
		}
		
	}

}
