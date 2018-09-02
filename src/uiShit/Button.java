package uiShit;

import java.awt.Rectangle;
import java.awt.Shape;

import com.sun.javafx.geom.RoundRectangle2D;

import display.Clickable;
import nodeSystem.ValueOutField;

public class Button extends ClickableUiShit {

	private ClickReciever sendTo;
	private ValueOutField<String> tf;

	public Button(int[] pos, String name, ClickReciever sendTo) {
		super(pos, name);
		setWidth(40);
		setHeight(20);
		addChild(tf = new ValueOutField<String>(new int[] { 0, 0 }, "Text", getWidth(), getHeight()));
		tf.valueC.x = getName();
		tf.useValueVontainer(true);
		this.sendTo = sendTo;
	}

	public void clicked() {
		sendTo.click(getName());
	}

	public Shape getCollider() {
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

}
