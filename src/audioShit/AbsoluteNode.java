package audioShit;

import nodeSystem.Node;
import unitGnerators.Absolute;

public class AbsoluteNode extends Node {

	private AudioInOutEntry inOut;
	Absolute absolute;
	public AbsoluteNode(int[] pos, String name) {
		super(pos, name);
		absolute = new Absolute();
		OutputNode.getSynth().add(absolute);
	//	absolute.start();
		addEntry(inOut = new AudioInOutEntry(this, "Absolute"));
		
		inOut.getLeftPorts().output.connect(absolute.input);
		absolute.output.connect(inOut.getRightPorts().input);
	}

}
