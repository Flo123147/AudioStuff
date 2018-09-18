package graphics;

import java.awt.Graphics2D;


public class Rect extends Drawable{

	private int width,height;
	public Rect(int[] pos,int width,int height,String name) {
		super(pos,name);
		this.width = width;
		this.height = height;		
	}






	@Override
	protected void draw(Graphics2D g, int x, int y) {
		g.setColor(baseColor);
		g.fillRoundRect(x, y, width, height, 20, 20);
	}






	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
