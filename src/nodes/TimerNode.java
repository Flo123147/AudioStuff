package nodes;

import com.jsyn.Synthesizer;

import display.Window;
import helper.ControlHelper;
import nodeSystem.Node;
import nodeSystem.NodeAudioOutPort;

public class TimerNode extends Node {

	private Synthesizer synth;
	public double bpm;
	private double timeBetweenBeats;
	private double timeOffset;

	private boolean running;

	private Timer timer;
	private double currBeat;

	public TimerNode(int[] pos) {

		super(pos, "Timer");
		addControlInPort("Start");
		addControlInPort("Stop");

		addControlOutPort("Beat");

		currBeat = 0;
		bpm = 480;
		timeBetweenBeats = 1 / (bpm / 60);
//		System.out.println(timeBetweenBeats);
		synth = Window.getSynth();

		timer = new Timer();
	}

	private void sleepTillNexTBeat() {
		while (running) {
			controlPort("Beat");
			try {
				synth.sleepUntil(timeOffset + (currBeat * timeBetweenBeats));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currBeat++;
		}
	}

	@Override
	public void click(String button, ControlHelper ch) {

	}

	@Override
	protected void control(String name) {
		switch (name) {
		case "Start":
			if (!running) {
				running = true;
				timeOffset = synth.getCurrentTime();
				Thread t = new Thread(timer);
				t.start();
			}
			break;
		case "Stop":
			if (running) {
				running = false;
				currBeat = 0;
			}
			break;
		default:
			break;
		}

	}

	class Timer implements Runnable {
//		public Synthesizer synth;

//		public Timer() {
//			
//		}

		@Override
		public void run() {
			sleepTillNexTBeat();
		}

	}
}
