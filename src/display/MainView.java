package display;

import java.awt.Graphics2D;

import nodeSystem.Node;
import nodes.MainControllerNode;

public class MainView extends View {

	MainControl mc;

	public MainView(String name, Window wind) {
		super(name, wind);
//		mc = new MainControl(wind);
//		addComponent(mc);

//		addComponent(new Node(new int[] { 0, 0 }, "BASE_NODE"));
		addComponent(new MainControllerNode(new int[] { 100, 200 }));
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
