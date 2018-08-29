package nodeSystem;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import com.jsyn.unitgen.PassThrough;
import audioShit.OutputNode;
import graphics.Drawable;
import helper.ValueContainer;

public class ValueOutField extends Drawable {

	private int height, width;
	public PassThrough valueIn;
	public ValueContainer valueC;
	private boolean useCont;

	public ValueOutField(int[] pos, String name, int width, int height) {
		super(pos, name + "TextField");

		valueIn = new PassThrough();
		OutputNode.getSynth().add(valueIn);
		valueIn.start();

		this.width = width;
		this.height = height;

	}

	public void useValueVontainer(boolean useCont) {
		this.useCont = useCont;
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
		g.setColor(Color.white);

		String text = "";
		if (!useCont) {
			text = "" + (float) valueIn.output.get();
		} else {
			text = "" + valueC.value;
		}

		FontMetrics fm = g.getFontMetrics();
		int hOffset = (height / 2) + (fm.getAscent() / 2);

		g.setClip(x, y, width, height);
		g.drawString(text, x + 3, y + hOffset);
		g.setClip(null);
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
