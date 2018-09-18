package nodeComponents;

import java.awt.Shape;

import display.Clickable;
import uiShit.ClickReciever;

public class ButtonComponent extends NodeComponent implements Clickable {

	ClickReciever cr;

	public ButtonComponent(String name, int maxWidth, int maxHeight, ClickReciever cr) {
		super(name, maxWidth, maxHeight);
		this.cr = cr;
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
		return null;
	}

}
