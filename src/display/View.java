package display;

import java.awt.Graphics2D;
import java.util.LinkedList;

import graphics.Drawable;
import nodeSystem.Root;

public abstract class View {
	private LinkedList<Draggable> dragos;
	private LinkedList<Clickable> clickos;

	Drawable root;
	private String name;
	Window wind;

	private boolean isInitializend;

	public View(String name, Window wind) {
		this.name = name;
		this.wind = wind;
		root = new Root(name);
		root.setView(this);
		dragos = new LinkedList<>();
		clickos = new LinkedList<>();
		wind.addView(this);
	}

	void init() {
		isInitializend = true;
	}

	public void addComponent(Drawable comp) {
		comp.setView(this);
		root.addChild(comp);
	}

	public Drawable getRoot() {
		return root;
	}

	public void addDraggable(Draggable drago) {
		dragos.add(drago);
	}

	public void addClickable(Clickable clickable) {
		clickos.add(clickable);

	}

	public LinkedList<Draggable> getDragos() {
		// TODO Auto-generated method stub
		return dragos;
	}

	public LinkedList<Clickable> getClickos() {
		// TODO Auto-generated method stub
		return clickos;
	}

	public abstract boolean isDraggable();

	public void moveRoot(int deltaX, int deltaY) {
		root.setX(root.getLocalX() + deltaX);
		root.setY(root.getLocalY() + deltaY);
	}

	public String getName() {
		return name;
	}

	public void drawView(Graphics2D g, int x, int y) {
		root.preDraw(g, x, y);
	}

	public abstract void drawBackG(Graphics2D g, int x, int y);

	public boolean isInitializend() {
		return isInitializend;
	}
}
