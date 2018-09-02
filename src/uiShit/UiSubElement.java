package uiShit;

import java.awt.Color;
import java.awt.Graphics2D;

import graphics.Drawable;

public class UiSubElement extends Drawable {
	private int width, height;

	public UiSubElement(int[] pos, String name) {
		super(pos, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);

	}

}
