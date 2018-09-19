package helper;
import java.awt.Shape;
import java.util.LinkedList;

import graphics.Drawable;

public abstract interface Clickable {
	public abstract void clicked();

	public abstract Shape getCollider();

	public abstract String getName();

	public abstract LinkedList<Drawable> getChildren();

}
