package uiShit;

import java.awt.Insets;

import display.Window;

public class UiBorder {
	public float border;
	public final int LEFT = 0, TOP = 1, RIGHT = 2, BOT = 3;
	private Window wind;

	public UiBorder(float border, Window wind) {
		this.border = border;
		this.wind = wind;
	}

	public int getPixelX() {
		Insets i = wind.getInsets();
		return (int) (border * (wind.getWidth() - i.left - i.right)) + i.left;
	}

	public int getPixelY() {
		Insets i = wind.getInsets();
		return (int) (border * (wind.getHeight() - i.top - i.bottom)) + i.top;
	}
}
