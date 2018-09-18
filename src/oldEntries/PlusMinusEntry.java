package oldEntries;

import helper.ValueContainer;
import oldNodeSystem.Entry;
import oldNodeSystem.OldNode;
import oldNodeSystem.RoundButton;
import oldNodeSystem.ValueOutField;
import uiShit.ClickReciever;

public class PlusMinusEntry extends Entry implements ClickReciever{

	private float stepSize;
	private ValueContainer<Integer> valueC;
	private ValueOutField tf;
	private RoundButton roundButtMinus, roundButtPlus;

	public PlusMinusEntry(OldNode node, String name, float stepSize, ValueContainer<Integer> valueC) {
		super(node, name, 20);
		this.valueC = valueC;
		this.stepSize = stepSize;

		int middle = getWidth() / 2;

		addChild(tf = new ValueOutField(new int[] { middle - 15, nameHeight }, "OctaveDisp"));
		tf.valueC = this.valueC;
		tf.useValueVontainer(true);
		addChild(roundButtMinus = new RoundButton(new int[] { middle - 50, nameHeight + 10 }, "Minus", true, this));
		addChild(roundButtPlus = new RoundButton(new int[] { middle + 50, nameHeight + 10 }, "Plus", false, this));
	}

	public void plus() {
		valueC.x += (int) stepSize;
	}

	public void minus() {
		valueC.x -= (int)stepSize;
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

	@Override
	public void click(String button) {
		// TODO Auto-generated method stub
		
	}

}
