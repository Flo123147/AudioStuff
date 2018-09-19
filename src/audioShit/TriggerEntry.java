package audioShit;

import helper.Triggerable;
import oldNodeSystem.Entry;
import oldNodeSystem.OldNode;
import unitGnerators.TriggerUnit;

public class TriggerEntry extends Entry {

	TriggerUnit triggerUnit;
	Triggerable tr;

	public TriggerEntry(OldNode node, String name, Triggerable tr) {
		super(node, name, 0);
		this.tr = tr;
		triggerUnit = new TriggerUnit(this);
		wind.addToSynth(triggerUnit);
		triggerUnit.start();

		getLeftPorts().output.connect(triggerUnit.input);
		addInConnector();

	}

	@Override
	protected void updateMetrics() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setDissconceted() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setConnected() {
		// TODO Auto-generated method stub

	}

	public void triggerOn() {
		tr.triggerOn();

	}

	public void triggerOff() {
		tr.triggerOff();
	}

	public void triggerHold() {
		tr.triggerHold();

	}

}
