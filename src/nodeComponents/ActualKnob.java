package nodeComponents;

import java.awt.Color;
import java.awt.Graphics2D;
import helper.ControlHelper;
import helper.EmptyDraggable;

public class ActualKnob extends EmptyDraggable {

	private PropertyComponent comp;

	public ActualKnob(int[] pos, String name, PropertyComponent comp) {
		super(pos, name);
		this.comp = comp;
		setColor(Color.lightGray);
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		
		g.setColor(Color.BLACK);
		g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
		g.setColor(baseColor);
		g.fillOval(x - (radius-5), y - (radius-5), (radius-5) * 2, (radius-5) * 2);
	}

	@Override
	protected void move(int dX, int dY, ControlHelper ch) {
		comp.contolMoved(dX, dY);
	}

}
