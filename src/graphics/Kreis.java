package graphics;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import display.Draggable;

public class Kreis extends Draggable {
	protected int diameter;

	public Kreis(int[] pos, int diameter, String name) {
		super(pos, name);
		this.diameter = diameter;
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		g.setColor(baseColor);
		g.fillOval(x - getRadius(), y - getRadius(), diameter, diameter);
	}

	@Override
	protected Shape getCollider() {
		// TODO Auto-generated method stub
		return new Ellipse2D.Float(getX() - getRadius(), getY() - getRadius(), diameter, diameter);
	}

	private int getRadius() {
		return diameter / 2;
	}

}
