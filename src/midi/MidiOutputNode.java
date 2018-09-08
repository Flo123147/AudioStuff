package midi;

import testingInProgress.ClonableNode;

import java.awt.Graphics2D;

import com.jsyn.ports.UnitInputPort;

import audioShit.AudioInEntry;

public class MidiOutputNode extends ClonableNode {

	private AudioInEntry left, right;
	private UnitInputPort out;

	public MidiOutputNode(int[] pos, String name) {
		super(pos, name);

		addEntry(left = new AudioInEntry(this, "Left"));
		addEntry(right = new AudioInEntry(this, "Right"));
		out = wind.getmainOutput();
		connect(left.getLeftPorts().output,0, out,0);
		connect(right.getLeftPorts().output,0, out,1);
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
