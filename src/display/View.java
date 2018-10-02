package display;

import java.awt.Graphics2D;
import java.util.LinkedList;

import graphics.Drawable;
import helper.Clickable;
import nodeSystem.Node;
import oldNodeSystem.Root;
import uiShit.ClickableUiShit;

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

	public void addNode(Node node) {
		addComponent(node);
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
		return dragos;
	}

	public LinkedList<Clickable> getClickos() {
		return clickos;
	}

	/**
	 * Set weather or not a your View can be dragged around;
	 * @return true if draggable
	 */
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

	public void migrateClicksAndDragos(Drawable partToMove) {
		View from = partToMove.getView();

		LinkedList<ClickableUiShit> partsClicks = new LinkedList<>();
		LinkedList<Draggable> partsDrags = new LinkedList<>();

		if (partToMove instanceof ClickableUiShit) {
			partsClicks.add((ClickableUiShit) partToMove);
		}
		if (partToMove instanceof Draggable) {
			partsDrags.add((Draggable) partToMove);
		}
		for (Drawable childPart : partToMove.getChildren()) {
			migrateClicksAndDragos(childPart);
		}

		from.removeClicks(partsClicks);
		from.removeDrags(partsDrags);

		this.clickos.addAll(partsClicks);
		this.dragos.addAll(partsDrags);
	}

	private void removeClicks(LinkedList<ClickableUiShit> partsClicks) {
		clickos.removeAll(partsClicks);
	}

	private void removeDrags(LinkedList<Draggable> partsDrags) {
		dragos.removeAll(partsDrags);
	}

	public void switchTo() {

		if (!isInitializend()) {
			init();
		}
		wind.switchToView(getName());
	}
}
