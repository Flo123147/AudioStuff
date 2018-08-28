package display;

import java.awt.Shape;

import graphics.Drawable;

public abstract class Clickable extends Drawable {

	public Clickable(int[] pos, String name) {
		super(pos, name);
		Window.dragger.addClickable(this);
	}

	public abstract void clicked();

	public abstract Shape getCollider();

}
