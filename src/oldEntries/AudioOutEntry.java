package oldEntries;

import display.View;
import oldNodeSystem.Entry;
import oldNodeSystem.OldNode;
import oldNodeSystem.ValueOutField;

public class AudioOutEntry extends Entry {

	private ValueOutField vOutField;
	private int textSpace = 100;

	public AudioOutEntry(OldNode node, String name) {
		super(node, name, 00);
		addOutCOnnector();

		addChild(vOutField = new ValueOutField(new int[] { getWidth() - textSpace, 0 }, "Value"));
		getRightPorts().output.connect(vOutField.valueIn.input);
	}

	@Override
	protected void updateMetrics() {
		vOutField.setHeight(totalHeight);

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
