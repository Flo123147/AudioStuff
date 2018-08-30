package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.JFrame;

import com.jsyn.instruments.DrumWoodFM;
import com.jsyn.instruments.DualOscillatorSynthVoice;
import com.jsyn.unitgen.*;

import audioShit.AbsoluteNode;
import audioShit.AdderNode;
import audioShit.DelayNode;
import audioShit.GenaeralNodeTest;
import audioShit.MidiSynthNode;
import audioShit.OutputNode;
import audioShit.ReaderNode;
import audioShit.SineOscillatorNode;
import audioShit.PianoNode;
import audioShit.WhiteNoiseNode;
import graphics.Drawable;
import graphics.Kreis;
import graphics.Rect;
import helper.ImHelping;
import nodeSystem.KeyTestNode;
import nodeSystem.Root;
import testingInProgress.MidiTester;
import uiShit.Spawner;

@SuppressWarnings("serial")
public class Window extends JFrame implements Runnable {
	private boolean running;
	private LinkedList<Drawable> rootParts;

	private Kreis[] kreises = new Kreis[3];
	private boolean initialized;

	public static Window widow;
	public static Dragger dragger;
	public static Root root;
	public static OutputNode outputNode;
	public static KeyMngr keyMngr;

	public Window() {
		rootParts = new LinkedList<Drawable>();
		Window.widow = this;
		ImHelping.wind = this;
	}

	private void init() {

		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);
		setSize(getWidth(), getHeight());
		setMinimumSize(new Dimension(getWidth(), getHeight()));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Root root = new Root(new int[2], "root");
		int topMiddle = getWidth() / 2;
		int leftMiddle = getHeight() / 2;
		root.setSizes(leftMiddle, topMiddle, getWidth(), getHeight());

		Dragger dragger = new Dragger(root);
		KeyMngr keyMngr = new KeyMngr();
		addKeyListener(keyMngr);

		Window.dragger = dragger;
		Window.root = ImHelping.root = root;
		Window.keyMngr = keyMngr;

		addMouseListener(dragger);
		addMouseMotionListener(dragger);

		setTitle("Wambo");

		rootParts.add(root);
		initialized = true;
	}

	private void test2() {
		outputNode = new OutputNode(new int[] { 1100, 500 }, "Output");
//		WhiteNoiseNode wnn = new WhiteNoiseNode(new int[] { 100, 100 }, "White Noise");
//		SineOscillatorNode son = new SineOscillatorNode(new int[] { 700, 800 });
//		son.setFreq(8f);
//		SineOscillatorNode son2 = new SineOscillatorNode(new int[] { 700, 900 });
//		son2.setFreq(300f);
//
//		DelayNode delayNode = new DelayNode(new int[] { 500, 620 }, "Delay");
//
//		AdderNode an = new AdderNode(new int[] { 55, 88 }, "AdderTest");
//
//		GenaeralNodeTest gnt = new GenaeralNodeTest(new int[] { 77, 77 }, "wuutwuuut", new Maximum());
//
//		GenaeralNodeTest gnt2 = new GenaeralNodeTest(new int[] { 77, 77 }, "wuutwuuut", new DualOscillatorSynthVoice());
//
//		AbsoluteNode ann = new AbsoluteNode(new int[] { 666, 666 }, "Basolute");
//
//		ReaderNode rn = new ReaderNode(new int[] { 676, 676 }, "Reader");
//
////		KeyTestNode ktn = new KeyTestNode(new int[] {987,77}, "KeyOutTest"); 

		MidiSynthNode msn = new MidiSynthNode(new int[] { 300, 300 }, "Midi --Synth Test");

		PianoNode tn = new PianoNode(new int[] { 900, 600 }, "Piano");

		MidiTester nidiTester;
		Thread t = (new Thread(nidiTester = new MidiTester(msn.getMidisynth())));
		t.start();
	}

	private void uitest() {
		Spawner sp = new Spawner(new int[] { 20, 20 });
		rootParts.add(sp);
		System.out.println("-lel");
	}

	@SuppressWarnings("unused")
	private void test() {
		Rect rrr;
		Rect rrr2;

		rootParts.add(rrr = new Rect(new int[] { 20, 40 }, 100, 800, "R"));

		kreises[0] = new Kreis(new int[] { 100, 100 }, 20, "K1");
		kreises[1] = new Kreis(new int[] { 1000, 1000 }, 20, "K2");
		rrr2 = new Rect(new int[] { 20, 20 }, 60, 760, "Ri");

		rrr2.setColor(Color.GREEN);
		Kreis k3 = new Kreis(new int[] { 30, 30 }, 30, "Kred");
		kreises[2] = k3;
		rrr.addChild(k3);

		k3.setColor(Color.pink);

		rrr.addChild(rrr2);

	}

	private void loop() {
		running = true;

		createBufferStrategy(2);
		BufferStrategy bs = getBufferStrategy();

		HashMap<RenderingHints.Key, Object> hints = new HashMap<>();
		hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Font font = new Font(Font.SERIF, Font.PLAIN, 12);

		while (running) {

			do {
				do {
					Graphics2D g = (Graphics2D) bs.getDrawGraphics();
					g.setFont(font);
					g.setRenderingHints(hints);// antialias on
					g.setColor(Color.DARK_GRAY);
					g.fillRect(0, 0, getWidth(), getHeight());
					draw(g);
					g.dispose();

				} while (bs.contentsRestored());

				bs.show();

			} while (bs.contentsLost());

			try {
				Thread.sleep(1000 / 60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		outputNode.stop();
		setVisible(false);
		super.dispose();
		System.exit(0);
	}

	private void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		for (Drawable d : rootParts) {
			d.preDraw(g, 0, 0);
		}
	}

	@Override
	public void run() {
		if (!initialized) {
			init();
		}
		loop();
	}

	@Override
	public void dispose() {
		System.out.println("exit");
		running = false;
	}

	public static void main(String[] args) {
		Window wind;
		Thread t = (new Thread(wind = new Window()));
		wind.init();
		for (String arg : args) {
			switch (arg) {
			case "test1":
				wind.test();
				break;
			case "test2":
				wind.test2();
				break;

			case "uitest":
				wind.uitest();
				break;
			default:
				break;
			}
		}
		t.start();
	}

	public void jSynthStopped() {

	}

}
