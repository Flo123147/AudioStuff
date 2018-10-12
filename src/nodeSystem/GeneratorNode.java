package nodeSystem;

import com.jsyn.Synthesizer;
import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.ports.UnitPort;
import com.jsyn.unitgen.UnitGenerator;

import display.Window;


public abstract class GeneratorNode extends Node {
	protected Synthesizer synth;

	public GeneratorNode(int[] pos, String name) {
		super(pos, name);
		this.synth = Window.getSynth();
	}

	/*
	 * Only set this once.
	 */
	protected void setUnitGenerator(UnitGenerator myUnit) {
		this.unitGenerator = myUnit;
//		if (myUnit instanceof MyBaseUnit) {
//			MyBaseUnit mbu = (MyBaseUnit) myUnit;
//			mbu.node = this;
//		}
		for (UnitPort up : myUnit.getPorts()) {
			if (up instanceof UnitInputPort) {
				UnitInputPort uip = (UnitInputPort) up;
				addAudioInPort(uip);

			} else if (up instanceof UnitOutputPort) {
				UnitOutputPort uop = (UnitOutputPort) up;
				addAudioOutPort(uop);

			}
		}
		wind.addToSynth(myUnit, myUnit.isStartRequired());

	}
}
