package nodeSystem;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.util.HashMap;

import display.Draggable;
import graphics.Drawable;
import helper.Empty;
import nodeComponents.NodeComponent;
import nodeSupComponents.TextField;

public abstract class BaseNode extends Draggable {

	protected static final int ARC_WIDTH = 5, ARC_HEIGHT = 5;
	protected static final int BASE_WIDTH = 200;
	protected static int BORDER_THICKNESS = 5;

	protected static int GRID_SPACING = 20;

	protected static int NAME_HEIGHT = 20;
	protected int inOutHeight, componentsHeight;
	private int height, totalHeight;
	protected int componentWidth;

	protected Color borderColor;

	protected HashMap<String, NodeComponent> components;
	private Drawable compRoot;

	private TextField nameDisplay;

	public BaseNode(int[] pos, String name) {
		super(pos, name);
		borderColor = Color.BLACK;

		components = new HashMap<>();
		addChild(compRoot = new Empty(new int[] { 0, 0 }, "CompRoot"));

		addChild(nameDisplay = new TextField(new int[] { BASE_WIDTH / 2, 0 }, "NodeName", BASE_WIDTH, NAME_HEIGHT,
				true));
		nameDisplay.setText(getName());

		updateMetrics();
	}
	
	public void addNodeComponent(String compName, NodeComponent comp, int xGridPos, int yGridPos) {
		comp.setX(GRID_SPACING * xGridPos);
		comp.setY(GRID_SPACING * yGridPos);
		components.put(compName, comp);
		compRoot.addChild(comp);

		int rightEx = comp.getLocalX() + comp.getWidth();
		int botEx = comp.getLocalY() + comp.getHeight();

		componentWidth = Math.max(rightEx, componentWidth);
		componentsHeight = Math.max(botEx, componentsHeight);

		updateMetrics();
	}

	/**
	 * TODO Change Height/Width to fit
	 * 
	 * @param compName
	 */
	public void removeComponent(String compName) {
		NodeComponent toRemove = components.remove(compName);
		compRoot.removeChild(toRemove);

		updateMetrics();
	}

	@Override
	protected Shape getCollider() {
		return new RoundRectangle2D.Float(getX() - BORDER_THICKNESS, getY() - BORDER_THICKNESS, getTotalWidth(),
				getTotalHeight(), ARC_HEIGHT, ARC_HEIGHT);
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		g.setColor(borderColor);
		g.fillRoundRect(x - BORDER_THICKNESS, y - BORDER_THICKNESS, getTotalWidth(), getTotalHeight(), ARC_WIDTH,
				ARC_HEIGHT);

		g.setColor(getBaseColor());
		g.fillRect(x, y, getWidth(), NAME_HEIGHT);

		g.setColor(Color.lightGray);
		y += NAME_HEIGHT + BORDER_THICKNESS;
		g.fillRect(x, y, getWidth(), inOutHeight);

		y += inOutHeight + BORDER_THICKNESS;
		g.fillRect(x, y, getWidth(), componentsHeight);

	}

	public void updateMetrics() {

		nameDisplay.setX(getWidth() / 2);

		compRoot.setY(2 * BORDER_THICKNESS + NAME_HEIGHT + inOutHeight);
		height = NAME_HEIGHT + inOutHeight + componentsHeight + 2 * BORDER_THICKNESS;
		totalHeight = height + 2 * BORDER_THICKNESS;
	}

	public int getTotalHeight() {
		return totalHeight;
	}

	public int getHeight() {
		return height;
	}

	public int getTotalWidth() {
		return getWidth() + 2 * BORDER_THICKNESS;
	}

	public int getWidth() {
		return Math.max(BASE_WIDTH, componentWidth);
	}

}
