package audioShit;

import helper.ValueContainer;
import nodeSystem.Clicker;
import nodeSystem.Entry;
import nodeSystem.Node;
import nodeSystem.ValueOutField;

public class PlusMinusEntry extends Entry {

	private float stepSize;
	private ValueContainer valueC;
	private ValueOutField tf;
	private Clicker minus, plus;

	public PlusMinusEntry(Node node, String name, float stepSize, ValueContainer valueC) {
		super(node, name, 20);
		this.valueC = valueC;
		this.stepSize = stepSize;

		int middle = getWidth() / 2;

		addChild(tf = new ValueOutField(new int[] { middle - 15, nameHeight }, "OctaveDisp", 30, 20));
		tf.valueC = this.valueC;
		tf.useValueVontainer(true);
		addChild(minus = new Clicker(new int[] { middle - 50, nameHeight + 10 }, "Minus", true, this));
		addChild(plus = new Clicker(new int[] { middle + 50, nameHeight + 10 }, "Plus", false, this));
	}

	public void plus() {
		valueC.value += stepSize;
	}

	public void minus() {
		valueC.value -= stepSize;
	}

	@Override
	protected void updateMetrics() {

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
