package nodeSystem;

import java.awt.Color;
import java.awt.Graphics2D;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.PassThrough;

import helper.ControlHelper;

public class NodeInPort extends NodePort {

	public NodeOutPort connectionFrom;

	public NodeInPort(UnitInputPort in) {
		super(in.getName());
		port = in;
		portNameDisplay.setText(port.getName());
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		g.fillRect(x, y, PORT_WIDTH, PORT_HEIGHT);
		g.setColor(Color.LIGHT_GRAY);
		super.draw(g, x, y);
	}

	@Override
	protected void move(int dX, int dY, ControlHelper ch) {
		if(this.connectionFrom != null)
		removeInConnection();
	}

	public void addInConnection(NodeOutPort connectionFrom) {
		if (this.connectionFrom != null) {
			removeInConnection();
		}
		this.connectionFrom = connectionFrom;
		((UnitOutputPort) connectionFrom.port).connect((UnitInputPort) this.port);
	}

	public void removeInConnection() {
		((UnitOutputPort) connectionFrom.port).disconnect((UnitInputPort) this.port);
		connectionFrom.disconnect(this);
		connectionFrom = null;
	}
}
