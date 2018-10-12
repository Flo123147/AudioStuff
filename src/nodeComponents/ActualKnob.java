package nodeComponents;

import java.awt.Color;
import java.awt.Graphics2D;
import helper.ControlHelper;
import helper.EmptyDraggable;

public class ActualKnob extends EmptyDraggable {

	private PropertyComponent comp;
	private int x2;
	private int y2;
	private double lastPercentage;

	public ActualKnob(int[] pos, String name, PropertyComponent comp) {
		super(pos, name);
		this.comp = comp;
		setColor(Color.lightGray);
		lastPercentage = 0;
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {

		g.setColor(Color.BLACK);
		g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
		g.setColor(baseColor);
		g.fillOval(x - (radius - 5), y - (radius - 5), (radius - 5) * 2, (radius - 5) * 2);

		g.setColor(Color.black);

		if (Double.compare(lastPercentage, comp.percentage) != 0) {
			lastPercentage = comp.percentage;
			double perc = (comp.percentage - 0.5) * 2;
			x2 = (int) ((Math.sin(perc)) * radius);
			y2 = (int) -((Math.cos(perc)) * radius);
		}
		g.drawLine(x, y, x + x2, y + y2);
	}

	@Override
	protected void move(int dX, int dY, ControlHelper ch) {
		comp.contolMoved(dX, dY);
	}

}
