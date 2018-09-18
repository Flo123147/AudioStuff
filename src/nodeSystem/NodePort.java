package nodeSystem;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import com.jsyn.ports.UnitPort;

import display.Draggable;
import helper.ControlHelper;

public abstract class NodePort extends Draggable {
	public static final int PORT_WIDTH = 100;
	public static final int PORT_HEIGHT = 20;
	public static final int CONN_DIAMETER = 20;
	
	protected UnitPort port;
	
	public TextField portNameDisplay;
	
	
	public boolean isConnected;

	public NodePort(String name) {
		super(new int[] { 0, 0 }, name + "Port");
		setColor(Color.white);

		addChild(portNameDisplay = new TextField(new int[] { PORT_WIDTH / 2, 0 }, "NodePortName", PORT_WIDTH,
				PORT_HEIGHT, true));
		
	}

	@Override
	public void init() {
		super.init();
	}

	@Override
	protected void move(int dX, int dY, ControlHelper ch) {
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		g.setColor(Color.GRAY);
		g.fillOval(x - CONN_DIAMETER / 2, y + (PORT_HEIGHT / 2) - CONN_DIAMETER / 2, CONN_DIAMETER, CONN_DIAMETER);
	}

	@Override
	protected Shape getCollider() {
		return new Ellipse2D.Float(getX() - CONN_DIAMETER / 2, getY() + (PORT_HEIGHT / 2) - CONN_DIAMETER / 2,
				CONN_DIAMETER, CONN_DIAMETER);
	}

}
