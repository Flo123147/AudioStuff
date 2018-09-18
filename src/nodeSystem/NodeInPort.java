package nodeSystem;

import java.awt.Color;
import java.awt.Graphics2D;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.unitgen.PassThrough;

public class NodeInPort extends NodePort {

	
	
	
	public NodeInPort(UnitInputPort in) {
		super("In");
		port = in;
		portNameDisplay.setText(port.getName());
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		g.fillRect(x, y, PORT_WIDTH, PORT_HEIGHT);
		g.setColor(Color.LIGHT_GRAY);
		super.draw(g, x, y);
	}

}
