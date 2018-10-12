package nodes;

import com.jsyn.unitgen.Add;

import helper.ControlHelper;
import nodeSystem.GeneratorNode;
import nodeSystem.Node;

public class AdderNode extends GeneratorNode {

	public AdderNode(int[] pos) {
		super(pos, "Adder");
		setUnitGenerator(new Add());
	}

	@Override
	public void click(String button, ControlHelper ch) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void control(String name) {
		// TODO Auto-generated method stub

	}

}
