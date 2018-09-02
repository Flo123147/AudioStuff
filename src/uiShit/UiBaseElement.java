package uiShit;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

import com.sun.javafx.geom.RoundRectangle2D;

import graphics.Drawable;
import helper.ValueContainer;
import nodeSystem.Root;

public abstract class UiBaseElement extends Drawable {

	private static int[] test;
	public UiBorder borderLeft, borderRight, borderTop, borderBot;
	int roundCorner = 10;
	int borderWidth = 3;
	ValueContainer<Integer> spacing = new ValueContainer<>(2);
	private boolean drawBG = true;

	private int minWidth,minHeight;
	
	// LEFT = 0,TOP = 1,RIGHT = 2,BOT = 3
	public UiBaseElement(UiBorder[] borders) {

		super(test = new int[] { 0, 0 }, "UiTest");
		borderLeft = borders[0];
		borderTop = borders[1];
		borderRight = borders[2];
		borderBot = borders[3];
		setColor(Color.LIGHT_GRAY);
	}

	public void setBorderWidth(int borderWidth) {
		this.borderWidth = borderWidth;
	}

	@Override
	public void preDraw(Graphics2D g, int xOffset, int yOffset) {
		updateCoords();

		super.preDraw(g, xOffset, yOffset);
	}

	void updateCoords() {

		setX(borderLeft.getPixelX());
		setY(borderTop.getPixelY());

	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
//		System.out.println(getHeight());
//		System.out.println(getWidth());
		if (drawBG) {
			g.setColor(Color.BLACK);
			g.fillRoundRect(x + spacing.x, y + spacing.x, getWidth() - spacing.x * 2, getHeight() - spacing.x * 2,
					roundCorner, roundCorner);
			g.setColor(getColor());
			g.fillRect(x + borderWidth + spacing.x, y + borderWidth + spacing.x,
					getWidth() - borderWidth * 2 - spacing.x * 2, getHeight() - borderWidth * 2 - spacing.x * 2);
		}
		g.setClip(new Rectangle(x + borderWidth + spacing.x, y + borderWidth + spacing.x,
				getWidth() - borderWidth * 2 - spacing.x * 2, getHeight() - borderWidth * 2 - spacing.x * 2));

		// spacing.x++;
//		borderBot.x.border+=1;
//		test[0]++;
	}

	public void setCornerRoundnes(int round) {
		this.roundCorner = round;
	}

	public int getWidth() {

		return borderRight.getPixelX() - borderLeft.getPixelX();

	}

	public int getHeight() {
		return borderBot.getPixelY() - borderTop.getPixelY();

	}

	@Override
	public void init() {
	}
}
