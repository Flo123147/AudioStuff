package nodeComponents;

import java.awt.Rectangle;
import java.awt.Shape;

import helper.Clickable;
import nodeSupComponents.TextField;
import uiShit.ClickReciever;

public class ButtonComponent extends NodeComponent implements Clickable {

	ClickReciever cr;
	TextField nameDisplay;

	public ButtonComponent(String name, int maxWidth, int maxHeight, ClickReciever cr) {
		super(name, maxWidth, maxHeight);
		this.cr = cr;
		addChild(nameDisplay = new TextField(new int[] { maxWidth / 2, 0}, "Button Name Display", maxWidth, maxHeight,
				true));
		nameDisplay.setText(name);
	}

	@Override
	public void init() {
		correspondingView.addClickable(this);
		super.init();
	}

	@Override
	public void clicked() {
		cr.click(getName());
	}

	@Override
	public Shape getCollider() {
		// TODO Auto-generated method stub
		return new Rectangle.Float(getX(), getY(), getWidth(), getHeight());
	}

}
