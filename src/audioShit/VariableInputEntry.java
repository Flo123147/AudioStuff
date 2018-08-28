package audioShit;

import nodeSystem.Entry;
import nodeSystem.Node;
import nodeSystem.ValueOutField;

public class VariableInputEntry extends Entry{
	private double value;
	private ValueOutField textField;

	private int textSpace = 100;
	private int divider;
	public VariableInputEntry(Node node, String name) {
		super(node, name, 20);
		textField = new ValueOutField(new int[] { divider, 0 }, name, textSpace, neededHeight);
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
