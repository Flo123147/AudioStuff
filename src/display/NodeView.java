package display;

import java.awt.Color;
import java.awt.Graphics2D;

import com.jsyn.unitgen.WhiteNoise;

import audioShit.WhiteNoiseNode;
import helper.SimpleEvent;
import midi.MidiCotrollerNode;
import midi.MidiOutputNode;
import midi.MidiSynthNode;
import nodeSystem.Root;
import uiShit.BasicTimeLine;

public class NodeView extends View {

	private int width, height;

	private MidiSynthNode midiSynth;
	private MidiOutputNode outOutput;

	private BasicTimeLine timeLine;

	private MidiCotrollerNode contNode;

	public NodeView(String name, Window wind, BasicTimeLine timeLine) {
		super(name, wind);
		this.timeLine = timeLine;
		root.addChild(midiSynth = new MidiSynthNode(new int[] { 100, 100 }, "MidiSnth"));
		root.addChild(outOutput = new MidiOutputNode(new int[] { 600, 600 }, "MidiOut"));
		root.addChild(contNode = new MidiCotrollerNode(new int[] { 20, 20 }, "MidiCont"));

	}

	@Override
	void init() {
	
		width = wind.getWidth();
		height = wind.getHeight();

		root.setX(wind.getWidth() / 2);
		root.setY(wind.getHeight() / 2);

		super.init();
	}

	public void setSizes(int leftMiddle, int topMiddle, int width, int height) {

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
		g.drawLine(0, y, width, y);
		g.drawLine(x, 0, x, height);
	}

	public void switchTo() {

		if (!isInitializend()) {
			init();
		}
		wind.switchToView(getName());
	}

	public void switchBack() {
		wind.switchToMainView();
		System.out.println("switching back");
	}

	public MidiSynthNode getSynthNode() {
		// TODO Auto-generated method stub
		return midiSynth;
	}
}
