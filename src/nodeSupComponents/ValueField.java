package nodeSupComponents;

import java.awt.Graphics2D;

import com.jsyn.ports.UnitOutputPort;

public class ValueField extends TextField {

	public UnitOutputPort valueToDisplay;

	public ValueField(int[] pos, String name, int maxWidth, int maxHeight, boolean centered, UnitOutputPort output) {
		super(pos, name, maxWidth, maxHeight, centered);
		valueToDisplay = output;
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		setText("" + valueToDisplay.get());
		super.draw(g, x, y);
	}

}
