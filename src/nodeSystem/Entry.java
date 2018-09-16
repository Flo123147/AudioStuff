package nodeSystem;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import com.jsyn.unitgen.PassThrough;

import graphics.Drawable;

public abstract class Entry extends Drawable {

	protected int neededHeight;
	protected int totalHeight;
	protected Node node;
	protected int nameHeight = 16;
	private int topMiddle;
	private int width;
	private int spacing = 1;
	private Connector connIn, connOut;

	protected boolean isConnected;
	PassThrough leftPorts, rightPorts;

	private boolean hasInConnector, hasOutConnector;
	private int connectorOffset = 6;

	public String[] connectedTo, connectedFrom;

	public Entry(Node node, String name, int neededHeight) {
		super(new int[] { node.getBorderWidth(), 0 }, name);
		width = node.getEntryWidth();
		this.neededHeight = neededHeight;
		color = Color.black;
		this.node = node;

		this.leftPorts = new PassThrough();
		wind.addToSynth(leftPorts);
		this.leftPorts.start();
		this.rightPorts = new PassThrough();
		wind.addToSynth(rightPorts);
		this.rightPorts.start();

	}

	public PassThrough getLeftPorts() {
		return leftPorts;
	}

	public PassThrough getRightPorts() {
		return rightPorts;
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		g.setColor(color);
		g.drawLine(x, y, x + width, y);
		g.drawLine(x, y + totalHeight, x + width, y + totalHeight);
		drawName(g, x, y);

	}

	public int getEntryHeight() {
		totalHeight = nameHeight + neededHeight;
		topMiddle = nameHeight / 2;
		if (connIn != null) {
			connIn.setY(topMiddle);

		}
		if (connOut != null) {
			connOut.setY(topMiddle);

		}
		updateMetrics();
		return totalHeight;
	}

	public int getWidth() {
		return width;
	}

	private void drawName(Graphics2D g, int x, int y) {
		FontMetrics fm = g.getFontMetrics();
		g.setColor(Color.BLACK);
		g.drawString(getName(), x + spacing * 2, y + fm.getAscent());
		nameHeight = fm.getHeight() + spacing * 2;
		totalHeight = nameHeight + neededHeight;

	}

	protected abstract void updateMetrics();

	public final void addInConnector() {
		if (hasInConnector) {
			removeChild(connIn);
		}
		hasInConnector = true;
		addChild(connIn = new Connector(new int[] { -connectorOffset, topMiddle }, getName() + "-leftConnector", true,
				this));
	}

	public final void addOutCOnnector() {
		if (hasOutConnector) {
			removeChild(connOut);
		}
		hasOutConnector = true;
		addChild(connOut = new Connector(new int[] { width + connectorOffset, topMiddle },
				getName() + "-rightConnector", false, this));
	}

	public boolean HasIn() {
		return hasInConnector;
	}

	public boolean HasOut() {
		return hasOutConnector;
	}

	public int getTopMiddle() {
		return topMiddle;
	}

	public void removeOutConnection(Entry toRemove) {
		System.out.println("Removing: " + toRemove);
		toRemove.leftPorts.input.disconnect(getRightPorts().output);
		toRemove.setDissconceted();
		toRemove.isConnected = false;

		node.removeConnection(this, toRemove);
	}

	protected abstract void setDissconceted();

	public void addOutConnection(Entry toAdd) {
		System.out.println("Adding: " + toAdd);
		rightPorts.output.connect(toAdd.getLeftPorts().input);
		toAdd.setConnected();
		toAdd.isConnected = true;

		System.out.println("now adding  " + toAdd);
		node.newConnection(this, toAdd);
	}

	protected abstract void setConnected();

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}
}
