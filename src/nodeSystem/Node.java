package nodeSystem;

import java.util.LinkedList;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.UnitGenerator;

import graphics.Drawable;
import helper.Empty;
import uiShit.ClickReciever;

public abstract class Node extends BaseNode implements ClickReciever {

	private LinkedList<NodeAudioInPort> inPorts;
	private int nrInPorts = 0;
	private LinkedList<NodeAudioOutPort> outPorts;
	private int nrOutPorts = 0;

	private Drawable inOutRoot;

	public UnitGenerator unitGenerator;

	public Node(int[] pos, String name) {
		super(pos, name);
		inPorts = new LinkedList<>();
		outPorts = new LinkedList<>();

		addChild(inOutRoot = new Empty(new int[] { 0, 1 * BORDER_THICKNESS + NAME_HEIGHT }, "InOutRoot"));

	}

	

	public void setAutoPorts(String[] names) {
		for (String name : names) {
			for (NodeAudioOutPort port : outPorts) {
				if (port.getName().equals(name))
					port.autoTrigger = true;
			}
		}
	}

	public void addInPort(UnitInputPort in) {
		NodeAudioInPort port;
		inOutRoot.addChild(port = new NodeAudioInPort(in));
		port.setPos(new int[] { 0, nrInPorts * NodePort.PORT_HEIGHT });
		inPorts.add(port);

		nrInPorts++;

		if (nrInPorts > nrOutPorts) {
			inOutHeight += NodePort.PORT_HEIGHT;
			updateMetrics();
		}
	}

	public void addOutPort(UnitOutputPort out) {
		NodeAudioOutPort port;
		inOutRoot.addChild(port = new NodeAudioOutPort(out));
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
			for (NodeAudioOutPort nop : outPorts) {
				nop.setX(getWidth());
			}
	}

	public NodeAudioInPort getInPort(String name) {
		for (NodeAudioInPort in : inPorts) {
			if (in.getName() == name)
				return in;
		}
		return null;
	}

	public NodeAudioOutPort getOutPort(String name) {
		for (NodeAudioOutPort out : outPorts) {
			System.out.println(out.getName());
			if (out.getName() == name)
				return out;
		}
		return null;
	}
}
