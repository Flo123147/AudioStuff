package midi;

import unitGnerators.MyVoiceOut;

import java.awt.Graphics2D;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.unitgen.UnitGenerator;

import audioShit.AudioInEntry;
import audioShit.MyUnitVoice;
import oldNodeSystem.ClonableNode;

public class MidiOutputNode extends ClonableNode {

	public AudioInEntry left, right;
	private UnitInputPort out;

	public MidiOutputNode(int[] pos, String name) {
		super(pos, name);

		UnitGenerator u;
		setUnitGen(u = new MyVoiceOut());
		addEntry(left = new AudioInEntry(this, "Left"));
		addEntry(right = new AudioInEntry(this, "Right"));
		out = wind.getMainOutput();

		connectInwards(0, left, out, 0);
		connectInwards(0, right, out, 1);

		u.setEnabled(false);
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

}
