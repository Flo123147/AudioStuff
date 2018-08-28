package display;

import java.awt.Shape;
import graphics.Drawable;
import helper.ControlHelper;

public abstract class Draggable extends Drawable {

	public Draggable(int[] pos, String name) {
		super(pos, name);
		Window.dragger.addDraggable(this);
		root.addChild(this);
	}

	protected abstract Shape getCollider();

	protected void dragStart() {

	}

	protected void dragEnd() {

	}

	protected void move(int dX, int dY, ControlHelper ch) {
		setPos(new int[] { getLocalX() + dX, getLocalY() + dY });
	}
}
