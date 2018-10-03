package nodeSystem;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.ports.UnitPort;
import com.jsyn.unitgen.UnitGenerator;

import unitGnerators.MyBaseUnit;

public abstract class GeneratorNode extends Node {
	public GeneratorNode(int[] pos, String name) {
		super(pos, name);
	}

	/*
	 * Only set this once.
	 */
	protected void setUnitGenerator(UnitGenerator myUnit) {
		this.unitGenerator = myUnit;
		if (myUnit instanceof MyBaseUnit) {
			MyBaseUnit mbu = (MyBaseUnit) myUnit;
			mbu.node = this;
		}
		for (UnitPort up : myUnit.getPorts()) {
			if (up instanceof UnitInputPort) {
				UnitInputPort uip = (UnitInputPort) up;
				addInPort(uip);

			} else if (up instanceof UnitOutputPort) {
				UnitOutputPort uop = (UnitOutputPort) up;
				addOutPort(uop);

			}
		}
		wind.addToSynth(myUnit, myUnit.isStartRequired());

	}
}
