package unitGnerators;

import java.util.LinkedList;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.unitgen.Circuit;

import helper.Triggerable;
import nodeSystem.Node;

public abstract class MyBaseUnit extends Circuit implements Triggerable {
	public static final String BASETRIGGER_NAME = "baseTrigger";
	public TriggerUnit baseTriggerUnit;
	public LinkedList<TriggerUnit> triggerList;
	public UnitInputPort startProcedure;

	public Node node;
	
	/**
	 * Base Class for everything with at least one Trigger input. One is added by
	 * Default (baseTrigger). To add more use addTrigger() and override triggerOn.
	 * (make sure it still calls super.triggerOn())
	 * @param node 
	 */
	public MyBaseUnit(Node node) {
		add(baseTriggerUnit = new TriggerUnit(this, BASETRIGGER_NAME));
		addPort(startProcedure = baseTriggerUnit.input);
		triggerList = new LinkedList<>();
	
		this.node = node;
	}

	public void addTrigger(String portName, String triggerName) {
		TriggerUnit newTrigger = new TriggerUnit(this, triggerName);
		triggerList.add(newTrigger);
		newTrigger.input.setName(portName);
		addPort(newTrigger.input);
		add(newTrigger);
	}

	abstract void baseTrigger();

	@Override
	public void triggerOn(String triggerName) {
		if (triggerName == BASETRIGGER_NAME) {
			
			baseTrigger();
		}

	}

	@Override
	public void triggerHold(String triggerName) {
		if (triggerName == BASETRIGGER_NAME) {
			baseTiggerHold();
		}
	}

	void baseTiggerHold() {
		// TODO Auto-generated method stub

	}

	@Override
	public void triggerOff(String triggerName) {
		if (triggerName == BASETRIGGER_NAME) {
			baseTriggerOff();
		}
	}

	void baseTriggerOff() {
		// TODO Auto-generated method stub

	}

}
