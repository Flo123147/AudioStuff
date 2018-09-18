package oldNodeSystem;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import javafx.geometry.Bounds;
import uiShit.Button;
import uiShit.ClickReciever;

public class ButtonEntry extends Entry {

	LinkedList<Button> buttons;

	private boolean isSetup = false;

	public ButtonEntry(OldNode node, ClickReciever cRec, LinkedList<Button> buttons, String name) {
		super(node, name, 20);
		this.buttons = buttons;

	}

	public void setButtons(LinkedList<Button> buttons) {
		for (Button butt : buttons) {
			removeChild(butt);
		}
		this.buttons = buttons;
		isSetup = false;
	}

	public void setupButts(Graphics2D g) {
		int buttonsHeight = 0;
		int maxWidth = getWidth();
		int freeWidth = maxWidth;
		for (Button butt : buttons) {
			addChild(butt);
			Rectangle2D bounds = g.getFontMetrics().getStringBounds(butt.getName(), g);

			freeWidth -= bounds.getWidth();
			butt.setX(buttonsHeight);
			butt.setY(freeWidth / 2);

			freeWidth = maxWidth;
			buttonsHeight += 20;
		}

		neededHeight = buttonsHeight;
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {

		if (!isSetup)
			setupButts(g);
		super.draw(g, x, y);
	}

	@Override
	protected void updateMetrics() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setDissconceted() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setConnected() {
		// TODO Auto-generated method stub

	}

}
