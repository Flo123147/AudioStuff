package nodeSystem;

import java.awt.Graphics2D;

import display.View;
import graphics.Drawable;

public class Root extends Drawable {

	public Root(String name) {
		super(new int[] { 0, 0 }, name + "Root");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		correspondingView.drawBackG(g, x, y);

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
