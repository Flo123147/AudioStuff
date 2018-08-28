package audioShit;

import nodeSystem.Entry;
import nodeSystem.Node;
import nodeSystem.ValueOutField;

public class AudioOutEntry extends Entry {

	private ValueOutField vOutField;
	private int textSpace = 100;

	public AudioOutEntry(Node node, String name) {
		super(node, name, 00);
		addOutCOnnector();

		addChild(vOutField = new ValueOutField(new int[] { getWidth() - textSpace, 0 }, "Value", textSpace,
				totalHeight));
		getRightPorts().output.connect(vOutField.valueIn.input);
		System.out.println(totalHeight);
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
