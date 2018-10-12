package nodes;

import helper.ControlHelper;
import nodeComponents.KnobComponent;
import nodeSystem.GeneratorNode;
import unitGnerators.OutputUnit;

public class OutputNode extends GeneratorNode {

	private KnobComponent volumeKnob;
	private OutputUnit outUnit;

	public OutputNode(int[] pos) {
		super(pos, "OutputNode");
		setUnitGenerator(outUnit = new OutputUnit(wind.getMainOutput()));

		addNodeComponent("Volume", volumeKnob = new KnobComponent(outUnit, "Volume", 0, 1, 0.002), 0, 0);
		volumeKnob.input.setName("Volume");
		volumeKnob.setValue(1);
		addAudioInPort(volumeKnob.input);

		volumeKnob.output.connect(outUnit.volume);

	}

	
	
	@Override
	public void click(String button, ControlHelper ch) {
		// TODO Auto-generated method stub

	}


	@Override
	protected void control(String name) {
		// TODO Auto-generated method stub
		
	}


}
