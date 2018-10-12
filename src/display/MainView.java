package display;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;

import com.jsyn.devices.javasound.MidiDeviceTools;
import com.jsyn.instruments.DualOscillatorSynthVoice;
import com.jsyn.instruments.NoiseHit;
import com.jsyn.instruments.SubtractiveSynthVoice;
import com.jsyn.instruments.WaveShapingVoice;
import com.jsyn.midi.MidiSynthesizer;
import com.jsyn.util.MultiChannelSynthesizer;

import nodes.AdderNode;
import nodes.BasicSequenzerNode;
import nodes.MainControllerNode;
import nodes.OutputNode;
import nodes.SimpleSoundNode;
import nodes.TimerNode;
import testingInProgress.MidiSynthNode;
import testingInProgress.MySimpleTestVoice;
import testingInProgress.Testinstrument;

public class MainView extends View {

	MainControl mc;

	private MultiChannelSynthesizer multiSynth;

	private MidiSynthesizer msynth;

	private MidiDevice keyboard;

	public static long TEMPTIME;

	public MainView(String name, Window wind) {
		super(name, wind);
//		mc = new MainControl(wind);
//		addComponent(mc);

		addNode(new MainControllerNode(new int[] { 100, 200 }));
		addNode(new TimerNode(new int[] { 400, 200 }));
		addNode(new OutputNode(new int[] { 800, 200 }));
		addNode(new BasicSequenzerNode(new int[] { 500, 500 }));
		addNode(new SimpleSoundNode(new int[] { 1000, 0 }, new Testinstrument(50)));
		addNode(new SimpleSoundNode(new int[] { 1000, 200 }, new Testinstrument(100)));
		addNode(new SimpleSoundNode(new int[] { 1000, 400 }, new Testinstrument(250)));
		addNode(new SimpleSoundNode(new int[] { 1000, 600 }, new Testinstrument(500)));
		addNode(new SimpleSoundNode(new int[] { 1000, 800 }, new Testinstrument(1000)));

		addNode(new AdderNode(new int[] { 700, 100 }));
		addNode(new AdderNode(new int[] { 700, 100 }));
		addNode(new AdderNode(new int[] { 700, 100 }));
		
		addNode(new MidiSynthNode(new int[] {100,-4}));
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
