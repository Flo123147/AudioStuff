package nodeSystem;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.LinkedList;

import display.Clickable;
import graphics.Drawable;

public abstract class ClickableNodeTree extends Drawable implements Clickable {

	public ClickableNodeTree(int[] pos, String name) {
		super(pos, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		System.out.println("meeAddo");
		correspondingView.addClickable(this);
	}
}
