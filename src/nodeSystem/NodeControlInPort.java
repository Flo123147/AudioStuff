package nodeSystem;

import java.awt.Color;
import java.awt.Graphics2D;

import helper.ControlHelper;

public class NodeControlInPort extends NodePort {

	public Node toControl;
	private NodeControlOutPort connectionFrom;
	private boolean isTriggered;
	private long redTime = 0;

	public NodeControlInPort(String name, Node toControl) {
		super(name);
		this.toControl = toControl;
		dotColor = Color.blue;
	}

	public void control() {
		triggerRed();

		toControl.control(this.getName());
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		if (isTriggered && redTime < System.currentTimeMillis()) {
			dotColor = Color.blue;
			isTriggered = false;
		}

		g.fillRect(x, y, PORT_WIDTH, PORT_HEIGHT);
		super.draw(g, x, y);
	}

	@Override
	protected void move(int dX, int dY, ControlHelper ch) {
		if (this.connectionFrom != null)
			removeInConnection();
	}

	public void addInConnection(NodeControlOutPort connectControlFrom) {
		if (this.connectionFrom != null) {
			removeInConnection();
		}
		this.connectionFrom = connectControlFrom;
	}

	private void removeInConnection() {
		connectionFrom.disconnect(this);
		connectionFrom = null;

	}

	public void triggerRed() {

		redTime = System.currentTimeMillis() + 100;
		dotColor = Color.RED;
		isTriggered = true;
	}
}
