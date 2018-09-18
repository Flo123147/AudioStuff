package oldNodeSystem;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import display.Draggable;
import helper.ControlHelper;

public class SliderKnob extends Draggable {

	private int diameter = 15;
	private Slider slider;

	private int[] stops = new int[] {};

	public SliderKnob(int[] pos, String name, Slider slider) {
		super(pos, name + "-Handle");
		color = Color.LIGHT_GRAY;
		this.slider = slider;

	}

	@Override
	protected void dragStart() {
		setColor(Color.GREEN);
	}

	@Override
	protected void dragEnd() {
		setColor(Color.LIGHT_GRAY);
	}

	@Override
	protected void move(int dX, int dY, ControlHelper ch) {
		slider.changeValue(dX, ch);

	}

	@Override
	protected Shape getCollider() {
		return new Ellipse2D.Float(getX() - getRadius(), getY() - getRadius(), diameter, diameter);
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
//		System.out.println(slider.isConnected);
		if (slider.isConnected) {
			g.setColor(new Color(100, 100, 100, 100));

		} else {
			g.setColor(getColor());
		}
		g.fillOval(x - getRadius(), y - getRadius(), diameter, diameter);
	}

	int getRadius() {
		// TODO Auto-generated method stub
		return diameter / 2;
	}

}
