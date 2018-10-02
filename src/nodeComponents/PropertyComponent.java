package nodeComponents;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.Circuit;

import nodeSupComponents.TextField;
import nodeSupComponents.ValueField;
import unitGnerators.PropertiyUnit;

public abstract class PropertyComponent extends NodeComponent {

	protected double value;
	protected double min, max, valueChangePerPixel;

	private PropertiyUnit propertyUnit;
	public UnitInputPort input;
	public UnitOutputPort output;

	public TextField nameField;
	public ValueField valueField;

	/*
	 * Top and Bottom 20 Pixel already used
	 */
	public PropertyComponent(Circuit circuit, String name, int maxWidth, int maxHeight, double min, double max,
			double valueChangePerPixel) {
		super(name, maxWidth, maxHeight);

		circuit.add(propertyUnit = new PropertiyUnit(this));

		value = (max - min) / 2;
		this.min = min;
		this.max = max;
		this.valueChangePerPixel = valueChangePerPixel;

		output = propertyUnit.output;
		input = propertyUnit.input;

		addChild(nameField = new TextField(new int[] { getWidth() / 2, 0 }, getName(), getWidth(), 20, true));
		nameField.setText(getName());

		addChild(valueField = new ValueField(new int[] { getWidth() / 2, maxHeight - 20 }, getName(), getWidth(), 20,
				true, output));

	}

	public void contolMoved(int dX, int dY) {
		value += (dX + -dY) * valueChangePerPixel;
		value = clamp(value);
	}

	private double clamp(double value) {
		if (value <= min) {
			return min;
		} else if (value >= max) {
			return max;
		} else {
			return value;
		}
	}

	public double getValue() {
		return value;
	}
}
