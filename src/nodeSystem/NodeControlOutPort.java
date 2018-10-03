package nodeSystem;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;

import display.Draggable;
import display.Window;
import graphics.Drawable;
import helper.ControlHelper;
import helper.EmptyDraggable;
import helper.ImHelping;

public class NodeControlOutPort extends NodePort {
	public boolean isTriggered = true;
	public boolean autoTrigger;
	LinkedList<Drawable> drawCurvesTo;
	private EmptyDraggable empty;
	private boolean curveToEmpty;
	private long redTime = 0;

	public LinkedList<NodeControlInPort> connectedTo;

	public NodeControlOutPort(String name) {
		super(name);
//		portNameDisplay.setText(name);
		portNameDisplay.setX(-portNameDisplay.getLocalX());
		drawCurvesTo = new LinkedList<>();
		connectedTo = new LinkedList<>();
		dotColor = Color.blue;

	}

	public void control() {
		triggerRed();
		for (NodeControlInPort ncip : connectedTo) {
			ncip.control();
		}

	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		if (isTriggered && redTime < System.currentTimeMillis()) {
			dotColor = Color.blue;
			isTriggered = false;
		} 

		g.fillRect(x - PORT_WIDTH, y, PORT_WIDTH, PORT_HEIGHT);
		g.setColor(Color.LIGHT_GRAY);
		for (Drawable draw : drawCurvesTo) {
			g.setColor(dotColor);
			ImHelping.drawConnectionBezier(g, this.getX(), getY() + CONN_DIAMETER / 2, draw.getX() - CONN_DIAMETER / 2,
					draw.getY() + CONN_DIAMETER / 2);
		}

		if (curveToEmpty) {
			g.setColor(dotColor);
			ImHelping.drawConnectionBezier(g, this.getX(), getY() + CONN_DIAMETER / 2, empty.getX(), empty.getY());
		}
		super.draw(g, x, y);
		g.setColor(Color.red);
		g.fillRect(-5, -2, 10, 4);
	}

	@Override
	protected void move(int dX, int dY, ControlHelper ch) {
		empty = new EmptyDraggable(new int[] { ch.getStartXOnPanel(), ch.getStartYOnPanel() }, "EmptyDraggable");
		curveToEmpty = true;
		startConnecting(empty);
	}

	private void startConnecting(Draggable temp) {
		Window.dragger.startControlConnecting(this, temp);
	}

	public void connectingEnd(boolean success, NodeControlInPort ncip) {
		if (success) {
			drawCurvesTo.add(ncip);
			connectedTo.add(ncip);
		}
		curveToEmpty = false;
		empty = null;

	}

	public void disconnect(NodeControlInPort toDisconnect) {
		drawCurvesTo.remove(toDisconnect);
		connectedTo.remove(toDisconnect);
	}

	public void triggerRed() {

		redTime = System.currentTimeMillis() + 100;
		dotColor = Color.RED;
		isTriggered = true;
	}
}
