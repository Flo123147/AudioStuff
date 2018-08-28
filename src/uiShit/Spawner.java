package uiShit;

import java.awt.Graphics2D;

import graphics.Drawable;

public class Spawner extends Drawable{
	public Spawner(int[] pos) {
		super(pos, "UiTest");
		
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		g.fillRoundRect(x, y, 200, 600, 10, 10);
		
	}
}
