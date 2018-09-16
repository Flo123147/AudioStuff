package display;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;

import com.jsyn.midi.MidiSynthesizer;
import com.jsyn.util.MultiChannelSynthesizer;
import audioShit.UnitVoiceConstructor;
import audioShit.ReaderNode;
import audioShit.SineOscillatorNode;
import graphics.Drawable;
import midi.MidiCotrollerNode;
import midi.MidiOutputNode;
import nodeSystem.ClonableNode;
import uiShit.BasicTimeLine;

public class NodeView extends View {

	private int width, height;

	private MidiSynthesizer midiSynthesizer;
	private MultiChannelSynthesizer multiChannelSynthesizer;

	private MidiOutputNode outputNode;
	private BasicTimeLine timeLine;
	private MidiCotrollerNode contNode;

	public UnitVoiceConstructor voiceConstructor;

	private boolean isReady;

	private boolean hasChanged = true;

	public NodeView(String name, Window wind, BasicTimeLine timeLine) {
		super(name, wind);
		this.timeLine = timeLine;
		voiceConstructor = new UnitVoiceConstructor(this);
		addComponent(outputNode = new MidiOutputNode(new int[] { -400, 0 }, "MidiOut"));
		addComponent(contNode = new MidiCotrollerNode(new int[] { 20, 20 }, "MidiCont"));
		addComponent(new SineOscillatorNode(new int[] { -100, -100 }));
		addComponent(new ReaderNode(new int[] { -200, 0 }, "reader"));

	}

	public void startThisShit() {

		if (multiChannelSynthesizer != null) {
			multiChannelSynthesizer.getOutput().disconnectAll();

		}

		multiChannelSynthesizer = new MultiChannelSynthesizer();

		multiChannelSynthesizer.setup(Window.getSynth(), 0, 1, 12, voiceConstructor.voiceDesc());

		midiSynthesizer = new MidiSynthesizer(multiChannelSynthesizer);
		multiChannelSynthesizer.getOutput().connect(0, wind.getMainOutput(), 1);
		multiChannelSynthesizer.getOutput().connect(0, wind.getMainOutput(), 0);
		isReady = true;
	}

	public void mute() {
		if (multiChannelSynthesizer != null) {
			multiChannelSynthesizer.setVolume(0, 0);
		}

	}

	public void unmute() {
		if (multiChannelSynthesizer != null) {
			multiChannelSynthesizer.setVolume(0, 1);
		}
	}

	@Override
	public void addComponent(Drawable comp) {
		voiceConstructor.addNode(comp);
		super.addComponent(comp);
	}

	public void noteOn(int channel, int pitch, int velocity) {
//		System.out.println("try to play on Channel: " + channel + " |pitch: " + pitch + " |velocity: " + velocity);
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

	public boolean hasChanged() {
		return hasChanged;
	}

}
