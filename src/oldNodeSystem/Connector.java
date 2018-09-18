package oldNodeSystem;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.LinkedList;

import display.Draggable;
import display.Dragger;
import display.Window;
import graphics.Kreis;
import helper.ControlHelper;
import helper.ImHelping;

public class Connector extends Draggable {
	private enum ConnStatus {
		noConnect, connecting, connected
	};

	private int diameter = 10;
	private int borderExtr = 2;
	private boolean isInput;
	private Dragger dragger;
	private LinkedList<Connector> connectedTo;
	private Draggable tempTarget;
	private ConnStatus cs;
	private Connector connectFrom;
	private Entry entry;

	public Connector(int[] pos, String name, boolean isIn, Entry entry) {
		super(pos, name);
		color = Color.gray;
		this.isInput = isIn;
		this.dragger = Window.dragger;
		cs = ConnStatus.noConnect;
		this.entry = entry;
		connectedTo = new LinkedList<Connector>();
	}

	@Override
	protected void move(int dX, int dY, ControlHelper ch) {
		if (!isInput) {
			if (cs != ConnStatus.connecting) {
				System.out.println("connecting");
				dragger.makeConnection(tempTarget = new Kreis(new int[] { 0, 0 }, 0, "Temp"), this);
				addChild(tempTarget);
			}
		}else if(connectFrom != null){
			dissconnectInput();
		}
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		g.setColor(Color.BLACK);
		g.fillOval(x - getRadius() - borderExtr, y - getRadius() - borderExtr, diameter + 2 * borderExtr,
				diameter + 2 * borderExtr);
		g.setColor(color);
		g.fillOval(x - getRadius(), y - getRadius(), diameter, diameter);
		if (tempTarget != null) {
			ImHelping.drawConnectionBezier(g, this, tempTarget);
		}
		
		for(Connector c : connectedTo) {
			ImHelping.drawConnectionBezier(g, this, c);
		}
	}

	public boolean isInput() {
		return isInput;
	}

	private int getRadius() {
		// TODO Auto-generated method stub
		return diameter / 2;
	}

	@Override
	protected Shape getCollider() {
		// TODO Auto-generated method stub
		return new Ellipse2D.Float(getX() - getRadius(), getY() - getRadius(), diameter, diameter);
	}

	public void connentionFailed() {
		System.out.println("failed");
		removeChild(tempTarget);
		tempTarget = null;
		cs = ConnStatus.noConnect;

	}

	public void connentionSucc(Connector newConnection) {
		System.out.println(getName() + " connected");
		removeChild(tempTarget);
		tempTarget = null;
		connectedTo.add(newConnection);
		
		entry.addOutConnection(newConnection.entry);
		
		cs = ConnStatus.connected;
	}

	public void newInConnection(Connector neConnIn) {
		System.out.println(getName() + " connected");
		if(this.connectFrom != null) {//TODO perhaps multiple inputs
			dissconnectInput();
		}
		this.connectFrom = neConnIn;
		
		cs = ConnStatus.connected;
	}
	
	public void dissconnectInput() {
		connectFrom.connectionOutBroken(this);
		connectFrom = null;
	}

	private void connectionOutBroken(Connector broken) {
		connectedTo.remove(broken);
		entry.removeOutConnection(broken.entry);
	}

	public boolean isSameNode(Connector connectTo) {
		if (this.GetEntry().node == connectTo.GetEntry().node)
			return true;
		return false;
	}

	private Entry GetEntry() {
		return this.entry;
	}
}
