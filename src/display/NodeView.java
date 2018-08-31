package display;

import java.awt.Color;
import java.awt.Graphics2D;

import audioShit.MidiOutputNode;
import midi.MidiCotrollerNode;

public class NodeView extends View {

	private int leftMiddle, topMiddle;;
	private int width, height;

	private MidiCotrollerNode midiContNode;
	private MidiOutputNode outOutput;

	public NodeView(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public void setSizes(int leftMiddle, int topMiddle, int width, int height) {
		this.leftMiddle = leftMiddle;
		this.topMiddle = topMiddle;
		this.width = width;
		this.height = height;
	}

	@Override
	public boolean isDraggable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void drawBackG(Graphics2D g, int x, int y) {
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(0, leftMiddle + y, width, leftMiddle + y);
		g.drawLine(topMiddle + x, 0, topMiddle + x, height);
	}

}
