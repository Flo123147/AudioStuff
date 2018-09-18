package oldNodeSystem;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import com.jsyn.unitgen.PassThrough;

import graphics.Drawable;
import helper.ValueContainer;

public class ValueOutField<Type> extends Drawable {

	private int height, width;
	public PassThrough valueIn;
	public ValueContainer<Type> valueC;
	private boolean useCont;
	private boolean outPutChanged;
	private Object lastText;

	public ValueOutField(int[] pos, String name) {
		super(pos, name + "TextField");

		valueC = new ValueContainer<Type>();

		valueIn = new PassThrough();
		wind.addToSynth(valueIn);
		valueIn.start();
		height = 20;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
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
			text = "" + valueC.x;
		}

		if(text.equals(lastText)) {
			outPutChanged = true;
		}
		
		FontMetrics fm = g.getFontMetrics();
		if (outPutChanged) {
			Rectangle2D bounds = fm.getStringBounds(text, g);
			this.width = (int) (bounds.getWidth());
			this.height = (int) (bounds.getHeight());
			outPutChanged = false;
		}
		int hOffset = (height / 2) + (fm.getAscent() / 2);

		g.setClip(x, y, width, height);
		g.drawString(text, x + 3, y + hOffset);
//		g.setClip(null);
		
		lastText = text;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public void init() {
	}

}
