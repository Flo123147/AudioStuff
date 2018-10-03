package display;

import java.awt.Color;
import java.awt.Graphics2D;

import nodes.BasicSequenzerNode;
import nodes.MainControllerNode;
import nodes.OutputNode;
import nodes.SimpleSoundNode;
import nodes.TimerNode;
import testingInProgress.Testinstrument;

public class MainView extends View {

	MainControl mc;

	public MainView(String name, Window wind) {
		super(name, wind);
//		mc = new MainControl(wind);
//		addComponent(mc);
		
//		addNode(new Node(new int[] { 0, 0 }, "BASE_NODE"));
		addNode(new MainControllerNode(new int[] { 100, 200 }));
		addNode(new TimerNode(new int[] { 400, 200 }));
		addNode(new OutputNode(new int[] { 800, 200 }));
		addNode(new BasicSequenzerNode(new int[] { 500, 500 }));
		addNode(new SimpleSoundNode(new int[] { 800, 700 }, new Testinstrument()));
	}

	@Override
	public boolean isDraggable() {
		return true;
	}

	@Override
	public void drawBackG(Graphics2D g, int x, int y) {
		g.setColor(Color.red);
		g.drawOval(x, y, 30, 30);
	}

}
