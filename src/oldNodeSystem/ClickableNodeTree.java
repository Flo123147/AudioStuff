package oldNodeSystem;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.LinkedList;

import graphics.Drawable;
import helper.Clickable;

public abstract class ClickableNodeTree extends Drawable implements Clickable {

	public ClickableNodeTree(int[] pos, String name) {
		super(pos, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		correspondingView.addClickable(this);
	}
}
