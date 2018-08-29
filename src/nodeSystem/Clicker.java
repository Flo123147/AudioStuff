package nodeSystem;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import audioShit.PlusMinusEntry;
import display.Clickable;

public class Clicker extends Clickable {

	private boolean isMinus;
	private PlusMinusEntry pluMin;
	private int diameter = 20;

	public Clicker(int[] pos, String name, boolean isMinus, PlusMinusEntry pluMin) {
		super(pos, name);
		
		this.pluMin = pluMin;
		
		this.isMinus = isMinus;
		if (isMinus) {
			setColor(Color.red);
		} else {
			setColor(Color.green);

		}
	}

	@Override
	public void clicked() {
		if (isMinus) {
			pluMin.minus();
		} else {
			pluMin.plus();
		}
	}

	@Override
	public Shape getCollider() {
		return new Ellipse2D.Float(getX() - getRadius(), getY() - getRadius(), diameter, diameter);
	}

	private int getRadius() {
		// TODO Auto-generated method stub
		return diameter / 2;
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		g.setColor(getColor());
		g.fillOval(x - getRadius(), y - getRadius(), diameter, diameter);
		g.setColor(Color.BLACK);
		g.drawOval(x - getRadius(), y - getRadius(), diameter, diameter);
	}

}
