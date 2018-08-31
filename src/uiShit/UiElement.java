package uiShit;

import java.awt.Color;
import java.awt.Graphics2D;

import graphics.Drawable;
import helper.ValueContainer;

public class UiElement extends Drawable {

	private static int[] test;
	private UiBorder borderLeft, borderRight, borderTop, borderBot;
	private int roundCorner = 10;
	private int borderWidth = 3;
	private ValueContainer<Integer> spacing = new ValueContainer<>(1);
	
	//LEFT = 0,TOP = 1,RIGHT = 2,BOT = 3
	public UiElement(UiBorder[] borders) {

		super(test = new int[] { borders[0].border, borders[1].border }, "UiTest");
		borderLeft = borders[0];
		borderTop = borders[1];
		borderRight = borders[2];
		borderBot = borders[3];
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		g.setColor(Color.BLACK);
		g.fillRoundRect(x + spacing.x, y + spacing.x, getWidth() - spacing.x * 2, getHeight() - spacing.x * 2,
				roundCorner, roundCorner);
		g.setColor(Color.WHITE);
		g.fillRoundRect(x + borderWidth + spacing.x, y + borderWidth + spacing.x,
				getWidth() - borderWidth * 2 - spacing.x * 2, getHeight() - borderWidth * 2 - spacing.x * 2,
				roundCorner, roundCorner);
//		spacing.x++;
//		borderBot.x.border+=1;
//		test[0]++;
	}

	public void setCornerRoundnes(int round) {
		this.roundCorner = round;
	}

	public int getWidth() {
		return borderRight.border - borderLeft.border;
	}

	public int getHeight() {
		return borderBot.border - borderTop.border;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}
}
