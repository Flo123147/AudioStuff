package audioShit;

import java.util.LinkedList;

import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.Circuit;
import com.jsyn.unitgen.UnitVoice;
import com.softsynth.shared.time.TimeStamp;
import display.NodeView;
import nodeSystem.Entry;
import testingInProgress.ClonableNode;

public class GeneralCircit extends Circuit implements UnitVoice {

	private boolean underCostruction = true;

	NodeView nodeView;

	public GeneralCircit(NodeView nodeView) {
		this.nodeView = nodeView;
	}

	public void gatherUnits() {
		underCostruction = true;

		System.out.println(nodeView.getNodes().size());
		for (ClonableNode n : nodeView.getNodes()) {
			System.out.println("-----------------------------------");
			System.out.println(n.cloneUnitGen());
			LinkedList<String[]> r = n.getInnerConnections();

			LinkedList<Entry[]> waa = n.getOuterConnections();
			for (String[] u : r) {
				System.out.println(u[0] + "   -to-  " + u[1]);
			}
			for (Entry[] conns : waa) {
				System.out.println(conns[0] + "  -outConTo-   " + conns[1]);
			}

			Entry[] entries = n.getEntries();
			for (Entry e : entries) {
				if (e != null) {
					System.out.print(e.getLeftPorts().getTag() + "    ");
					System.out.println(e.getRightPorts().getTag());
				}
			}
		}
	}

	@Override
	public UnitOutputPort getOutput() {

		return null;
	}

	@Override
	public void noteOn(double frequency, double amplitude, TimeStamp timeStamp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void noteOff(TimeStamp timeStamp) {
		// TODO Auto-generated method stub

	}

}
