package nodeSupComponents;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import graphics.Drawable;

public class TextField extends Drawable {

	private boolean centered;
	private boolean timeToUpdate;
	private int offsetX, offsetY;
	private String text;

	public TextField(int[] pos, String name, int maxWidth, int maxHeight, boolean centered) {
		super(pos, name);
		timeToUpdate = true;
		this.centered = centered;
		setColor(Color.BLACK);
	}

	public void setText(String text) {
		this.text = text;
		timeToUpdate = true;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		if (timeToUpdate) {
			firstTime(g);
			timeToUpdate = false;
		}
		g.drawString(text, x + offsetX, y + offsetY);

	}

	private void firstTime(Graphics2D g) {
		if (text != null) {
			FontMetrics fm = g.getFontMetrics();
			Rectangle2D bounds = fm.getStringBounds(text, g);
			if (centered) {
				offsetX = (int) -(bounds.getWidth() / 2);
			} else {
				offsetX = 0;
			}

			offsetY = fm.getAscent();
		}
	}

}
