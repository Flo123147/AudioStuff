package nodeComponents;

import java.awt.Graphics2D;

import com.jsyn.unitgen.Circuit;

public class KnobComponent extends PropertyComponent {

	private static final int MIDDLE_Y = 60;

	private ActualKnob knob;

	public KnobComponent(Circuit circuit, String name, double min, double max, double valueChangePerPixel) {
		super(circuit, name, 100, 120, min, max, valueChangePerPixel);

		addChild(knob = new ActualKnob(new int[] { 50, MIDDLE_Y }, "KnobBase", this));
		knob.setRadius(35); 
	}
}
