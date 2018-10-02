package unitGnerators;

import java.util.LinkedList;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.ports.UnitPort;
import com.jsyn.unitgen.Circuit;
import com.jsyn.unitgen.UnitSink;
import com.jsyn.unitgen.UnitSource;

import helper.Triggerable;
import nodeSystem.Node;

public abstract class MyBaseUnit extends Circuit implements Triggerable, UnitSink {
	public static final String BASETRIGGER_NAME = "baseTrigger";
	public TriggerUnit baseTriggerUnit;
	public LinkedList<TriggerUnit> triggerList;
	public UnitInputPort startProcedure;

	public Node node;

	/**
	 * Base Class for everything with at least one Trigger input. One is added by
	 * Default (baseTrigger). To add more use addTrigger() and override triggerOn.
	 * (make sure it still calls super.triggerOn())
	 * 
	 * @param node
	 */
	public MyBaseUnit() {
		add(baseTriggerUnit = new TriggerUnit(this, BASETRIGGER_NAME));
		addPort(startProcedure = baseTriggerUnit.input);
		triggerList = new LinkedList<>();
	}

	public void addTrigger(String portName, String triggerName) {
		TriggerUnit newTrigger = new TriggerUnit(this, triggerName);
		triggerList.add(newTrigger);
		newTrigger.input.setName(portName);
		addPort(newTrigger.input);
		add(newTrigger);
	}

	/**
	 * For adding a Port to a Node after Constructor has been called. Basicly just a
	 * simple way to register it to the Node. Do not use in Constructor.(Double
	 * Ports)
	 * 
	 * Make sure to name the port correctly.
	 * 
	 * @param port
	 */
	public void addPortRuntime(UnitPort port) {
		addPort(port);
		if (port instanceof UnitInputPort) {
			UnitInputPort uip = (UnitInputPort) port;
			node.addInPort(uip);
		} else if (port instanceof UnitOutputPort) {
			UnitOutputPort uop = (UnitOutputPort) port;
			node.addOutPort(uop);
		}
	}

	protected abstract void baseTrigger();

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

	@Override
	public UnitInputPort getInput() {
		// TODO Auto-generated method stub
		return startProcedure;
	}

}
