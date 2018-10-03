package nodeSystem;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.LinkedList;

import com.jsyn.ports.UnitPort;

import display.Draggable;
import helper.ControlHelper;
import helper.EmptyDraggable;
import nodeSupComponents.TextField;

public abstract class NodePort extends Draggable {
	public static final int PORT_WIDTH = 100;
	public static final int PORT_HEIGHT = 20;
	public static final int CONN_DIAMETER = 20;

	public TextField portNameDisplay;
	protected Color dotColor;

	public boolean isConnected;

	public NodePort(String name) {
		super(new int[] { 0, 0 }, name);
		setColor(Color.white);

		addChild(portNameDisplay = new TextField(new int[] { PORT_WIDTH / 2, 0 }, "NodePortName", PORT_WIDTH,
				PORT_HEIGHT, true));

		dotColor = Color.lightGray;
	}

	@Override
	public void init() {
		super.init();
	}

	protected void move(int dX, int dY, ControlHelper ch) {
		// TODO Auto-generated method stub
		super.move(dX, dY, ch);
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		g.setColor(dotColor);
		g.fillOval(x - CONN_DIAMETER / 2, y + (PORT_HEIGHT / 2) - CONN_DIAMETER / 2, CONN_DIAMETER, CONN_DIAMETER);
	}

	@Override
	protected Shape getCollider() {
		return new Ellipse2D.Float(getX() - CONN_DIAMETER / 2, getY() + (PORT_HEIGHT / 2) - CONN_DIAMETER / 2,
				CONN_DIAMETER, CONN_DIAMETER);
	}

}
