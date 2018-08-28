package nodeSystem;

import java.awt.Color;
import java.awt.Graphics2D;

import graphics.Drawable;

public class Root extends Drawable {

	private int leftMiddle, topMiddle;;
	private int width, height;

	public Root(int[] pos, String name) {
		super(pos, name);
		// TODO Auto-generated constructor stub
	}

	public void moveRoot(int dX, int dY) {
		setPos(new int[] { getLocalX() + dX, getLocalY() + dY });
	}

	public void setSizes(int leftMiddle,int topMiddle,int width,int height) {
		this.leftMiddle = leftMiddle;
		this.topMiddle = topMiddle;
		this.width = width;
		this.height = height;
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(0, leftMiddle+ y, width, leftMiddle + y);
		g.drawLine(topMiddle + x, 0, topMiddle + x, height);
	}

}
