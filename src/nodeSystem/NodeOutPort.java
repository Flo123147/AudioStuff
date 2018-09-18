package nodeSystem;

import java.awt.Color;
import java.awt.Graphics2D;

import com.jsyn.ports.UnitOutputPort;

public class NodeOutPort extends NodePort {

	public NodeOutPort(UnitOutputPort out) {
		super("Out");
		port = out;
		portNameDisplay.setText(port.getName());
		portNameDisplay.setX(-portNameDisplay.getLocalX());
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		g.fillRect(x - PORT_WIDTH, y, PORT_WIDTH, PORT_HEIGHT);
		g.setColor(Color.LIGHT_GRAY);
		super.draw(g, x, y);
	}

}
