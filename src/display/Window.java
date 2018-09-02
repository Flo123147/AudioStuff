package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JFrame;

import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.ports.UnitInputPort;
import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.UnitGenerator;

import audioShit.Piano;
import audioShit.PianoNode;
import helper.ImHelping;
import helper.KeyMngr;
import helper.ValueContainer;
import uiShit.TimeLinesBox;
import uiShit.UiBorder;
import uiShit.UiBaseElement;

@SuppressWarnings("serial")
public class Window extends JFrame implements Runnable {
	private boolean running;
	private Map<String, View> views;
	private final String MAIN_VIEW_NAME = "MainView";
//	private Kreis[] kreises = new Kreis[3];
	private boolean initialized;

	public static Window widow;
	public static Dragger dragger;
//	public static Root root;
	public static KeyMngr keyMngr;

	private static Synthesizer synth;
	private UnitInputPort mainOutput;
	private LineOut lineOut;

	private ValueContainer<View> currentViewCont;
	private View swichtTo;

	public final UiBorder LEFT, TOP, RIGHT, BOT;

	public Window() {
		views = new HashMap<>();
		Window.widow = this;
		ImHelping.wind = this;
		LEFT = new UiBorder(0, this);
		TOP = new UiBorder(0, this);
		RIGHT = new UiBorder(0, this);
		BOT = new UiBorder(0, this);
	}

	public Map<String, View> getViews() {
		return views;
	}

	public View getCurrentView() {
		return currentViewCont.x;
	}

	public void switchToView(String name) {

		swichtTo = views.get(name);

	}

	private void init() {

		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);
		setSize(getWidth(), getHeight());
		setMinimumSize(new Dimension(getWidth() / 2, getHeight() / 2));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		int topMiddle = getWidth() / 2;
		int leftMiddle = getHeight() / 2;

		currentViewCont = new ValueContainer<View>();
		Dragger dragger = new Dragger(currentViewCont);
		KeyMngr keyMngr = new KeyMngr();
		addKeyListener(keyMngr);

		Window.dragger = dragger;
		Window.keyMngr = keyMngr;

		addMouseListener(dragger);
		addMouseMotionListener(dragger);

		setTitle("Wambo");

		initialized = true;

		synth = JSyn.createSynthesizer();
		lineOut = new LineOut();
		synth.add(lineOut);
		mainOutput = lineOut.input;

		synth.start();
		lineOut.start();
		currentViewCont.x = new MainView(MAIN_VIEW_NAME, this);
//		currentViewCont.value = newNodeView("NVT");
//		((NodeView) currentViewCont.value).setSizes(leftMiddle, topMiddle, getWidth(), getHeight());

		Insets ins = getInsets();
		LEFT.border = 0f;
		TOP.border = 0f;
		RIGHT.border = 1f;
		BOT.border = 1f;

	}

	private void loop() {
		running = true;

		createBufferStrategy(2);
		BufferStrategy bs = getBufferStrategy();

		HashMap<RenderingHints.Key, Object> hints = new HashMap<>();
		hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Font font = new Font(Font.SERIF, Font.PLAIN, 12);

		setBackground(Color.DARK_GRAY);

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
		setVisible(false);
		super.dispose();
		System.exit(0);
	}

	private void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		currentViewCont.x.drawView(g, 0, 0);

		if (swichtTo != null) {
			currentViewCont.x = swichtTo;
			swichtTo = null;
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
//		for (String arg : args) {
//			switch (arg) {
//			case "test1":
//				wind.test();
//				break;
//			case "test2":
//				wind.test2();
//				break;
//
//			case "uitest":
//				wind.uitest();
//				break;
//			default:
//				break;
//			}
//		}
		t.start();

		TimeLinesBox tlb;
		wind.getCurrentView().addComponent(tlb = new TimeLinesBox(new UiBorder[] { new UiBorder(0.3f, wind),
				new UiBorder(0.2f, wind), new UiBorder(0.9f, wind), new UiBorder(0.7f, wind) }));
		tlb.addTimeLine();
	}

	public void jSynthStopped() {

	}

	public UnitInputPort getmainOutput() {
		return mainOutput;
	}

	public static Synthesizer getSynth() {
		return synth;
	}

	public void addToSynth(UnitGenerator unitg) {
		synth.add(unitg);
	}

	public ValueContainer<View> getCurrentViewCont() {
		return currentViewCont;
	}

	public void addView(View view) {
		views.put(view.getName(), view);

	}

	public void switchToMainView() {
		switchToView(MAIN_VIEW_NAME);
	}

}
