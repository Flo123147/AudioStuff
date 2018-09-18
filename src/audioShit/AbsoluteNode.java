package audioShit;

import oldEntries.AudioInOutEntry;
import oldNodeSystem.OldNode;
import unitGnerators.Absolute;

public class AbsoluteNode extends OldNode {

	private AudioInOutEntry inOut;
	Absolute absolute;
	public AbsoluteNode(int[] pos, String name) {
		super(pos, name);
		absolute = new Absolute();
		wind.addToSynth(absolute);
	//	absolute.start();
		addEntry(inOut = new AudioInOutEntry(this, "Absolute"));
		
		inOut.getLeftPorts().output.connect(absolute.input);
		absolute.output.connect(inOut.getRightPorts().input);
	}

}
