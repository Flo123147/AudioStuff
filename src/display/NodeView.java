package display;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;

import com.jsyn.midi.MidiSynthesizer;
import com.jsyn.util.MultiChannelSynthesizer;

import audioShit.GeneralCircit;
import audioShit.ReaderNode;
import audioShit.SineOscillatorNode;
import graphics.Drawable;
import midi.MidiCotrollerNode;
import midi.MidiOutputNode;
import nodeSystem.Node;
import testingInProgress.ClonableNode;
import uiShit.BasicTimeLine;

public class NodeView extends View {

	private int width, height;

	private MidiSynthesizer midiSynthesizer;
	private MultiChannelSynthesizer multiChannelSynthesizer;

	private MidiOutputNode outputNode;
	private BasicTimeLine timeLine;
	private MidiCotrollerNode contNode;

	public GeneralCircit gc;

	private boolean isReady;

	public NodeView(String name, Window wind, BasicTimeLine timeLine) {
		super(name, wind);
		this.timeLine = timeLine;

		root.addChild(outputNode = new MidiOutputNode(new int[] { -400, 0 }, "MidiOut"));
		root.addChild(contNode = new MidiCotrollerNode(new int[] { 20, 20 }, "MidiCont"));
		root.addChild(new SineOscillatorNode(new int[] { -100, -100 }));
		root.addChild(new ReaderNode(new int[] { -200, 0 }, "reader"));
		midiSynthesizer = new MidiSynthesizer(multiChannelSynthesizer);
		gc = new GeneralCircit(this);
	}

	public void noteOn(int channel, int pitch, int velocity) {
		if (isReady)
			midiSynthesizer.noteOn(channel, pitch, velocity);
	}

	public void noteOff(int channel, int pitch, int velocity) {
		if (isReady)
			midiSynthesizer.noteOff(channel, pitch, velocity);
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



	public void switchBack() {
		wind.switchToMainView();
		System.out.println("switching back");
	}

	public MidiSynthesizer getMidiSynth() {
		// TODO Auto-generated method stub
		return midiSynthesizer;
	}

	public LinkedList<ClonableNode> getNodes() {
		LinkedList<Drawable> childs = root.getChildren();
		LinkedList<ClonableNode> nodes = new LinkedList<>();
		for (Drawable d : childs) {
			if (d instanceof ClonableNode) {
				nodes.add((ClonableNode) d);
			}
		}
		return nodes;
	}

	public MidiOutputNode getOutput() {
		return outputNode;
	}

}
