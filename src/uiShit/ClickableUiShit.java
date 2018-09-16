package uiShit;

import display.Clickable;

public abstract class ClickableUiShit extends UiSubElement implements Clickable {

	public ClickableUiShit(int[] pos, String name) {
		super(pos, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		correspondingView.addClickable(this);
	}

}
