package nodeSystem;

import java.util.LinkedList;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.ports.UnitPort;
import com.jsyn.unitgen.UnitGenerator;

import graphics.Drawable;
import helper.Empty;
import nodeComponents.NodeComponent;
import uiShit.ClickReciever;

public abstract class Node extends BaseNode implements ClickReciever{

	private LinkedList<NodeInPort> inPorts;
	private int nrInPorts = 0;
	private LinkedList<NodeOutPort> outPorts;
	private int nrOutPorts = 0;

	private Drawable inOutRoot;

	public UnitGenerator unitGenerator;

	public Node(int[] pos, String name) {
		super(pos, name);
		inPorts = new LinkedList<>();
		outPorts = new LinkedList<>();

		addChild(inOutRoot = new Empty(new int[] { 0, 1 * BORDER_THICKNESS + NAME_HEIGHT }, "InOutRoot"));

//		test();
	}

	@SuppressWarnings("unused")
	private void test() {
		addNodeComponent("TEST", new NodeComponent("TEST", 100, 100), 0, 0);
		addNodeComponent("TEST2", new NodeComponent("TEST2", 100, 100), 5, 0);
		addNodeComponent("TEST2", new NodeComponent("TEST3", 200, 100), 0, 5);
		addNodeComponent("TEST3", new NodeComponent("TEST3", 100, 100), 15, 0);

	}

	/*
	 * Only set this once.
	 */
	protected void setUnitGenerator(UnitGenerator unitGenerator) {
		this.unitGenerator = unitGenerator;
		wind.addToSynth(unitGenerator);
		for (UnitPort up : unitGenerator.getPorts()) {
			if (up instanceof UnitInputPort) {
				UnitInputPort uip = (UnitInputPort) up;
				addInPort(uip);

			} else if (up instanceof UnitOutputPort) {
				UnitOutputPort uop = (UnitOutputPort) up;
				addOutPort(uop);

			}
		}
	}

	public void addInPort(UnitInputPort in) {
		NodeInPort port;
		inOutRoot.addChild(port = new NodeInPort(in));
		port.setPos(new int[] { 0, nrInPorts * NodePort.PORT_HEIGHT });
		inPorts.add(port);

		nrInPorts++;

		if (nrInPorts > nrOutPorts) {
			inOutHeight += NodePort.PORT_HEIGHT;
			updateMetrics();
		}
	}

	public void addOutPort(UnitOutputPort out) {
		NodeOutPort port;
		inOutRoot.addChild(port = new NodeOutPort(out));
		port.setPos(new int[] { getWidth(), nrOutPorts * NodePort.PORT_HEIGHT });
		outPorts.add(port);

		nrOutPorts++;
		if (nrInPorts < nrOutPorts) {
			inOutHeight += NodePort.PORT_HEIGHT;
			updateMetrics();
		}
	}

	@Override
	public void updateMetrics() {
		super.updateMetrics();
		if (outPorts != null)
			for (NodeOutPort nop : outPorts) {
				nop.setX(getWidth());
			}
	}

	

}
