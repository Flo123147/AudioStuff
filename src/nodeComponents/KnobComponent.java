package nodeComponents;

public class KnobComponent extends PropertyComponent {

	private static final int MIDDLE_Y = 60;
	

	private ActualKnob knob;
	

	public KnobComponent(String name, double min, double max, double valueChangePerPixel) {
		super(name, 100, 120, min, max, valueChangePerPixel);


		addChild(knob = new ActualKnob(new int[] { 50, MIDDLE_Y }, "KnobBase", this));
		knob.setRadius(35);

		
		
	}

}
