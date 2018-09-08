package audioShit;

import java.util.LinkedList;

import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.Circuit;
import com.jsyn.unitgen.UnitGenerator;
import com.jsyn.unitgen.UnitVoice;
import com.jsyn.util.VoiceDescription;
import com.softsynth.shared.time.TimeStamp;
import display.NodeView;
import display.Window;
import graphics.Drawable;
import nodeSystem.Entry;
import nodeSystem.Node;
import testingInProgress.ClonableNode;

public class GeneralCircit extends Circuit implements UnitVoice {

	@SuppressWarnings("unused")
	private boolean underCostruction = true;

	NodeView nodeView;

	private Window window;

	private UnitOutputPort circuitOut;

	public GeneralCircit(NodeView nodeView) {
		window = Window.widow;
		this.nodeView = nodeView;
		this.circuitOut = new UnitOutputPort();
	}

	public void addNode(Drawable comp) {
		if(comp instanceof Node) {
			System.out.println("yaaay");
		}
	}

	
	public void gatherUnits() {
		underCostruction = true;
		setEnabled(false);
		System.out.println(nodeView.getNodes().size());
		for (ClonableNode n : nodeView.getNodes()) {

			UnitGenerator newGen = n.cloneUnitGen();
			if (newGen != null) {
				add(newGen);
				window.addToSynth(newGen);
			}
//			System.out.println("-----------------------------------");
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

		return circuitOut;
	}

	@Override
	public void noteOn(double frequency, double amplitude, TimeStamp timeStamp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void noteOff(TimeStamp timeStamp) {
		// TODO Auto-generated method stub

	}

	public VoiceDescription voiceDesc() {
		// TODO Auto-generated method stub
		return new MyVoiceDescription();
	}

	private class MyVoiceDescription extends VoiceDescription{

		public MyVoiceDescription() {
			super("Genaeral Temp Name", null);
			// TODO Auto-generated constructor stub
		}

		@Override
		public String[] getTags(int presetIndex) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public UnitVoice createUnitVoice() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getVoiceClassName() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
