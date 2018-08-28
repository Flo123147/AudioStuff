package nodeSystem;

import java.awt.Color;
import java.awt.Graphics2D;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.ports.UnitPort;

import helper.ControlHelper;

public class Slider extends Entry {

	private double value;
	private SliderKnob slideKreis;
	private ValueOutField textField;

	private int textSpace = 100;
	private int divider;

	private int baseLine;

	double minValue;
	private double maxValue;
	double valueRange;
	private double speed;

	public Slider(Node node, String name, double start, double end) {
		super(node, name, 20);

		addInConnector();

		value = 0f;
		slideKreis = new SliderKnob(new int[] { (getWidth() - textSpace) / 2, 0 }, getName(), this);

		speed = 500;
		
		divider = getWidth() - textSpace;

		textField = new ValueOutField(new int[] { divider, nameHeight }, name, textSpace, neededHeight);
		rightPorts.output.connect(textField.valueIn.input);
		
		addChild(slideKreis);
		addChild(textField);

		minValue = start;
		maxValue = end;
		valueRange = Math.abs(start - end);
		setValue(0.1f);
	
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	@Override
	protected void draw(Graphics2D g, int x, int y) {
		super.draw(g, x, y);
//		System.out.println(isConnected);
		if (isConnected) {
			g.setColor(Color.lightGray);
		} else {
			g.setColor(Color.DARK_GRAY);
		}
	}

	@Override
	protected void updateMetrics() {

		baseLine = nameHeight + neededHeight / 2;
		slideKreis.setY(baseLine);
		textField.setY(nameHeight);
	}

	// WIP
	public void setValue(double value) {
		double newValue = 0;
		if (value < minValue) {
			newValue = minValue;
		} else if (value > maxValue) {
			newValue = maxValue;
		} else {
			newValue = value;
		}

		this.value = newValue;
		rightPorts.input.set(value);
	}

	@Override
	protected void setConnected() {
		leftPorts.output.connect(rightPorts.input);
		
	}

	@Override
	protected void setDissconceted() {
		leftPorts.output.disconnect(rightPorts.input);

	}

	public void changeValue(int dX, ControlHelper ch) {
		int slowness = (int) (speed /valueRange) +1;
		if (ch.isShift()) {
			slowness *= 10;
		}
		value += (float) dX / slowness;
		value = clamp();
		if (!isConnected) {
			rightPorts.input.set(value);			
			System.out.println(value);
		}
	}

	private double clamp() {
		if (value < minValue) {
			return minValue;
		} else if (value > maxValue) {
			return maxValue;
		} else
			return value;
	}
}
