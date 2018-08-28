package audioShit;

import nodeSystem.Node;

public class AdderNode extends Node {

	private AudioOutEntry out;
	private AudioInEntry in1, in2;

	public AdderNode(int[] pos, String name) {
		super(pos, name);
		addEntry(out = new AudioOutEntry(this, getName() + "Out"));

		addEntry(in1 = new AudioInEntry(this, getName() + "In1"));
		addEntry(in2 = new AudioInEntry(this, getName() + "In2"));
		
		in1.getLeftPorts().output.connect(out.getRightPorts().input);
		in2.getLeftPorts().output.connect(out.getRightPorts().input);
	}

}
