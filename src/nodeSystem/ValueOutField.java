package nodeSystem;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import com.jsyn.unitgen.PassThrough;
import audioShit.OutputNode;
import graphics.Drawable;

public class ValueOutField extends Drawable{
	
	private int height, width;
	public PassThrough valueIn;
	public ValueOutField(int[] pos, String name,int width,int height) {
		super(pos, name + "TextField");
		
		valueIn = new PassThrough();
		OutputNode.getSynth().add(valueIn);
		valueIn.start();
		
		this.width = width;
		this.height = height;
	
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
		g.setColor(Color.white);
		
		String text = "" + (float)valueIn.output.get();
		FontMetrics fm = g.getFontMetrics();
		int hOffset = (height/2)+(fm.getAscent()/2);
		
		g.setClip(x, y, width, height);
		g.drawString(text, x+3, y+hOffset);
		g.setClip(null);
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setWidth(int width) {
		this.width = width;
	}


}
