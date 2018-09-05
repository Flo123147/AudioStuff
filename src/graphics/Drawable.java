package graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import display.View;
import display.Window;

public abstract class Drawable {
	private int[] pos;
	private String name;

	protected Color color;
	protected Window wind;
//	protected Root root;

	Drawable parent;
	LinkedList<Drawable> children;

	protected View correspondingView;
	private boolean initialized;

	public Drawable(int[] pos, String name) {
		this.pos = pos;
		this.name = name;
		wind = Window.widow;
//		root = Window.root;
		color = Color.WHITE;
		children = new LinkedList<Drawable>();

	}

	// Called once the correspondingView has been set
	public abstract void init();

	protected abstract void draw(Graphics2D g, int x, int y);

	public void preDraw(Graphics2D g, int xOffset, int yOffset) {
		if (initialized) {
			draw(g, getLocalX() + xOffset, getLocalY() + yOffset);
			try {
				for (Drawable d : children) {
					d.preDraw(g, getX(), getY());

				}
			} catch (ConcurrentModificationException e) {
				System.out.println("lel");
			}
		}
	}

	public final boolean addChild(Drawable child) {
		if (children.contains(child))
			return false;

		children.addFirst(child);
		child.setParent(this);

		if (this.initialized && !child.initialized) {
			child.setView(correspondingView);
		}

		return true;
	}

	public final void removeChild(Drawable child) {
		children.remove(child);
	}

	public LinkedList<Drawable> getChildren() {
		return children;
	}

	public Drawable getChild(String name) {
		for (Drawable d : children) {
			if (d.name == name) {
				return d;
			}
		}
		return null;
	}

	private void setParent(Drawable newParent) {
		if (this.parent != null) {
			this.parent.removeChild(this);
		}

		this.parent = newParent;
	}

	public Drawable getParent() {
		return this.parent;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public final int getX() {

		if (parent != null) {
			return parent.getX() + pos[0];
		}
		return pos[0];
	}

	public final int getY() {
		if (parent != null) {
			return parent.getY() + pos[1];
		}
		return pos[1];
	}

	public final void setPos(int[] pos) {
		this.pos = pos;
	}

	public final void setX(int x) {
		this.pos[0] = x;
	}

	public final void setY(int y) {
		this.pos[1] = y;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public int getLocalX() {
		// TODO Auto-generated method stub
		return pos[0];
	}

	public int getLocalY() {
		// TODO Auto-generated method stub
		return pos[1];
	}

	public void printPosX() {
		if (parent != null) {
			parent.printPosX();
		}
		System.out.print(pos[0] + " ");
	}

	public void printPosY() {
		if (parent != null) {
			parent.printPosY();
		}
		System.out.print(pos[1] + " ");
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setView(View view) {
		correspondingView = view;
		if (!initialized)
			init();
		for (Drawable c : children) {
			c.setView(correspondingView);
		}
		initialized = true;
	}

	public View getView() {
		// TODO Auto-generated method stub
		return correspondingView;
	}
}
