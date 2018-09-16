package midi;

import unitGnerators.MyVoiceOut;

import java.awt.Graphics2D;

import com.jsyn.ports.UnitInputPort;

import audioShit.AudioInEntry;
import audioShit.MyUnitVoice;
import nodeSystem.ClonableNode;

public class MidiOutputNode extends ClonableNode {

	private AudioInEntry left, right;
	private UnitInputPort out;

	public MidiOutputNode(int[] pos, String name) {
		super(pos, name);

		setUnitGen(new MyVoiceOut());
		addEntry(left = new AudioInEntry(this, "Left"));
		addEntry(right = new AudioInEntry(this, "Right"));
		out = wind.getMainOutput();

		connectInwards(0, left, out, 0);
		connectInwards(0, right, out, 1);

//		connect(left.getLeftPorts().output,0, out,0);
//		connect(right.getLeftPorts().output,0, out,1);
	}

	@Override
	public String getUnikeName() {
		// TODO Auto-generated method stub
		return MyUnitVoice.ConnectToOut;
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		super.draw(g, x, y);
	}

	public UnitInputPort getConnectors() {
		return out;
		// TODO Auto-generated method stub

	}
//	public UnitInputPort[] getConnectors() {
//		return new UnitInputPort[] {left.getLeftPorts().input,right.};
//		// TODO Auto-generated method stub
//		
//	}
}
