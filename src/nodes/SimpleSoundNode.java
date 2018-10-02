package nodes;

import helper.ControlHelper;
import nodeComponents.KnobComponent;
import nodeSystem.Node;
import testingInProgress.Testinstrument;
import unitGnerators.SimpleInstument;

public class SimpleSoundNode extends Node {

	public SimpleInstument simpleInstument;
	private KnobComponent volumeKnob;

	public SimpleSoundNode(int[] pos, SimpleInstument simpleInstrument) {
		super(pos, simpleInstrument.getName());
		setUnitGenerator(simpleInstrument);
		this.simpleInstument = simpleInstrument;
		simpleInstrument.node = this;

		addNodeComponent("Volume", volumeKnob = new KnobComponent(simpleInstument, "Volume", 0, 1, 0.001), 0, 0);

		volumeKnob.output.connect(simpleInstrument.amplScaler.scale);

	}

	@Override
	public void click(String button, ControlHelper ch) {
		// TODO Auto-generated method stub

	}

	@Override
	public void event(String event) {
		// TODO Auto-generated method stub

	}

}
