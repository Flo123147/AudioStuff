package nodes;

import helper.ControlHelper;
import nodeComponents.KnobComponent;
import nodeSystem.Node;
import unitGnerators.OutputUnit;

public class OutputNode extends Node {

	private KnobComponent volumeKnob;
	private OutputUnit outUnit;

	public OutputNode(int[] pos) {
		super(pos, "OutputNode");
		setUnitGenerator(outUnit = new OutputUnit(wind.getMainOutput()));
		
		addNodeComponent("Volume",volumeKnob = new KnobComponent("Volume",0,1,0.002), 0, 0);
		volumeKnob.input.setName("Volume");
		addInPort(volumeKnob.input);
		
		volumeKnob.output.connect(outUnit.volume);
	}

	@Override
	public void click(String button,ControlHelper ch) {
		// TODO Auto-generated method stub

	}

	@Override
	public void event(String event) {
		// TODO Auto-generated method stub
		
	}

}
