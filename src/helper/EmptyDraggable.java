package helper;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import display.Draggable;

public class EmptyDraggable extends Draggable {

	protected int radius;

	public EmptyDraggable(int[] pos, String name) {
		super(pos, name);
		radius = 0;
	}

	@Override
	protected Shape getCollider() {
		if (radius <= 0) {
			return null;
		} else {
			return new Ellipse2D.Float(getX() - radius, getY() - radius, radius*2, radius*2);
		}
	}

	@Override
	protected void move(int dX, int dY, ControlHelper ch) {

		super.move(dX, dY, ch);
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		// TODO Auto-generated method stub

	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

}
