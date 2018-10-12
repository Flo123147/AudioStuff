package nodes;

import helper.ControlHelper;
import nodeComponents.KnobComponent;
import nodeSystem.GeneratorNode;
import nodeSystem.Node;
import testingInProgress.Testinstrument;
import unitGnerators.SimpleInstument;

public class SimpleSoundNode extends GeneratorNode {

	public SimpleInstument simpleInstument;
	private KnobComponent volumeKnob;

	public SimpleSoundNode(int[] pos, SimpleInstument simpleInstrument) {
		super(pos, simpleInstrument.getName());
		setUnitGenerator(simpleInstrument);
		this.simpleInstument = simpleInstrument;

		addNodeComponent("Volume", volumeKnob = new KnobComponent(simpleInstument, "Volume", 0, 1, 0.001), 0, 0);

		volumeKnob.output.connect(simpleInstrument.amplScaler.scale);
		addControlInPort("Play");
	}

	@Override
	public void click(String button, ControlHelper ch) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void control(String name) {
		if(name.equals("Play"))
			simpleInstument.play();
		
	}


}
