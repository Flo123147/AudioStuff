package nodeSystem;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;

import com.jsyn.ports.UnitOutputPort;

import display.Draggable;
import display.Window;
import graphics.Drawable;
import helper.ControlHelper;
import helper.EmptyDraggable;
import helper.ImHelping;

public class NodeOutPort extends NodePort {
	public boolean isTriggered = true;
	public boolean autoTrigger;
	LinkedList<Drawable> drawCurvesTo;
	private EmptyDraggable empty;
	private boolean curveToEmpty;
	private long redTime = 0;

	public NodeOutPort(UnitOutputPort out) {
		super(out.getName());
		port = out;
		portNameDisplay.setText(port.getName());
		portNameDisplay.setX(-portNameDisplay.getLocalX());
		drawCurvesTo = new LinkedList<>();
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {

		if (isTriggered && redTime < System.currentTimeMillis()) {
			dotColor = Color.LIGHT_GRAY;
			isTriggered = false;
		} else if (autoTrigger) {
			UnitOutputPort uop = (UnitOutputPort) port;
			if (uop.get() == 1) {
				triggerRed();
			}
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
	}

	@Override
	protected void move(int dX, int dY, ControlHelper ch) {
		empty = new EmptyDraggable(new int[] { ch.getStartXOnPanel(), ch.getStartYOnPanel() }, "EmptyDraggable");
		curveToEmpty = true;
		startConnecting(empty);
	}

	private void startConnecting(Draggable temp) {
		Window.dragger.startConnecting(this, temp);
	}

	public void connectingEnd(boolean success, NodeInPort nip, Drawable temp) {
		if (success) {
			drawCurvesTo.add(nip);
		}
		curveToEmpty = false;
		empty = null;

	}

	public void disconnect(NodeInPort toDisconnect) {
		drawCurvesTo.remove(toDisconnect);
	}

	public void triggerRed() {
		redTime = System.currentTimeMillis() + 100;
		dotColor = Color.RED;
		isTriggered = true;
	}

}
