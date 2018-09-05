package nodeSystem;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import display.Draggable;

public abstract class Node extends Draggable {

	private int width = 200, arcRadi = 10;

	private int entryIndex;
	private Entry[] entries;
	private int borderWidth = 3;

	private int spacing = 3;

	private int nameHeight = 0;

	public Node(int[] pos, String name) {
		super(pos, name);
		entryIndex = 0;
		entries = new Entry[20];
	}

	@SuppressWarnings("unused")
	private void test() {

		Slider s = (Slider) getChild("c");
		s.addInConnector();
		s.addOutCOnnector();
	}

	public Entry[] getEntries() {
		return entries;
	}

	public void addEntry(Entry entry) {
		if (addChild(entry)) {
			entries[entryIndex] = entry;
			entryIndex++;
		}

	}

	// untested
	public void removeEntry(String name) {
		for (int i = 0; i > entries.length; i++) {
			if (entries[i].getName() == name) {
				removeEntry(i);

			}
		}
	}

	private int getEntriesHeight() {
		int height = 0;
		for (int i = 0; i < entries.length; i++) {
			if (entries[i] != null) {
				entries[i].setY(height + nameHeight);
				height += entries[i].getEntryHeight();
			} else {
				break;
			}
		}
		return height;
	}

	// untested
	public void removeEntry(int nr) {
		removeChild(entries[nr]);

		entries[nr] = null;
		for (int i = nr + 1; i < entries.length; i++) {
			if (entries != null) {
				entries[i - 1] = entries[i];
				entries[i] = null;
			} else {
				break;
			}
		}
	}

	@Override
	protected Shape getCollider() {
		return new RoundRectangle2D.Float(getX(), getY(), width, getEntriesHeight() + arcRadi * 2 + nameHeight, arcRadi,
				arcRadi);
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {

		g.setColor(Color.black);
		g.fillRoundRect(x, y, width, getEntriesHeight() + arcRadi + nameHeight, arcRadi, arcRadi);
		g.setColor(color);
		g.fillRoundRect(x + borderWidth, y + borderWidth, width - 2 * borderWidth,
				getEntriesHeight() - 2 * borderWidth + arcRadi + nameHeight, arcRadi, arcRadi);

		drawName(g, x, y);
	}

	private void drawName(Graphics2D g, int x, int y) {
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D bounds = fm.getStringBounds(getName(), g);
		g.setColor(Color.BLACK);
		g.drawString(getName(), x + (int) (getMiddle() - (bounds.getWidth() / 2)), y + fm.getAscent() + spacing);
		nameHeight = fm.getHeight() + spacing * 2;
	}

	private int getMiddle() {
		return width / 2;
	}

	public int getEntryWidth() {
		return width - borderWidth * 2;
	}

	public int getBorderWidth() {
		return borderWidth;
	}

	public void newConnection(Entry entry, Entry toAdd) {
				
	}
	
	public void removeConnection(Entry entry, Entry toRemove) {
		
	}

}
