package nodeComponents;

import java.awt.Color;
import java.awt.Graphics2D;

import graphics.Drawable;

public class NodeComponent extends Drawable implements Comparable<NodeComponent> {

	private int width, height;

	public NodeComponent(String name, int maxWidth, int maxHeight) {
		super(new int[] { 0, 0 }, name);
		this.width = maxWidth;
		this.height = maxHeight;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);
	}

	public int getArea() {
		return width * height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	@Override
	public int compareTo(NodeComponent o) {
		// TODO Auto-generated method stub
		return o.getArea() - this.getArea();
	}
}
