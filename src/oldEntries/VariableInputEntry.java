package oldEntries;

import oldNodeSystem.Entry;
import oldNodeSystem.OldNode;
import oldNodeSystem.ValueOutField;

public class VariableInputEntry extends Entry{
	private double value;
	private ValueOutField textField;

	private int textSpace = 100;
	private int divider;
	public VariableInputEntry(OldNode node, String name) {
		super(node, name, 20);
		textField = new ValueOutField(new int[] { divider, 0 }, name);
		getRightPorts().output.connect(textField.valueIn.input);
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

}
