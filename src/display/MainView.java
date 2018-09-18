package display;

import java.awt.Graphics2D;

public class MainView extends View {
	
	MainControl mc;
	
	public MainView(String name,Window wind) {
		super(name, wind);
		mc = new MainControl(wind);
		addComponent(mc);
	}

	@Override
	public boolean isDraggable() {
		return true;
	}

	@Override
	public void drawBackG(Graphics2D g, int x, int y) {
		// TODO Auto-generated method stub

	}

}
