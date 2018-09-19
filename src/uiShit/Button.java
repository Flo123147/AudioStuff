package uiShit;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

import com.sun.javafx.geom.RoundRectangle2D;

import display.MainControl;
import helper.Clickable;
import oldNodeSystem.ClickableNodeTree;
import oldNodeSystem.ValueOutField;

public class Button extends ClickableNodeTree {

	private ClickReciever sendTo;
	private ValueOutField<String> tf;
	private int spacing = 2;
	private int height;
	private int width;

	public Button(int[] pos, String name, ClickReciever sendTo) {
		super(pos, name);

		addChild(tf = new ValueOutField<String>(new int[] { 0, 0 }, "Text"));
		tf.valueC.x = getName();
		tf.useValueVontainer(true);
		this.sendTo = sendTo;
	}

	public void clicked() {
		sendTo.click(getName());
	}

	public Shape getCollider() {
		return new Rectangle(getX(), getY(), tf.getWidth() + 2 * spacing, tf.getHeight() + 2 * spacing);
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		height = tf.getHeight() + 2 * spacing;
		width = tf.getWidth() + 2 * spacing;

		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
	}

	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

}
