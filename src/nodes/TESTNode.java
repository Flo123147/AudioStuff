package nodes;

import com.jsyn.unitgen.WhiteNoise;

import helper.ControlHelper;
import nodeSystem.Node;

public class TESTNode extends Node {

	public TESTNode(int[] pos) {
		super(pos, "TestNode");
		setUnitGenerator(new WhiteNoise());
	}

	
	
	@Override
	public void click(String button,ControlHelper ch) {
		// TODO Auto-generated method stub

	}



	@Override
	public void event(String event) {
		// TODO Auto-generated method stub
		
	}

}
