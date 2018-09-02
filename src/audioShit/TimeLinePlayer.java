package audioShit;

import display.Window;
import helper.SimpleEvent;
import midi.MidiSynthNode;

public class TimeLinePlayer implements Runnable {

	private boolean initialized;
	SimpleEvent[] events;
	private int pointer;
	private boolean paused = true;
	public Object lock = new Object();
	private MidiSynthNode msn;

	public TimeLinePlayer(MidiSynthNode msn) {
		this.msn = msn;
		events = new SimpleEvent[100];
		pointer = 0;
		for (int i = 0; i < 100; i += 2) {
			int note = 30 + ((int) (Math.random() * 60));
			events[i] = new SimpleEvent(0.5, note, true);
			System.out.println(note);
			events[i + 1] = new SimpleEvent(0.0, note, false);
		}
	}

	public void pause() {
		paused = true;
	}

	public void unPause() {
		synchronized (lock) {

			paused = false;
			lock.notify();
		}
	}

	public void playBeginning() {

	}

	private void playLoop() {
		while (!paused) {
			play(events[pointer]);

			try {
				Window.getSynth().sleepFor(events[pointer].sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(pointer);
			pointer++;
			if (pointer >= 100) {
				pointer = 0;
				pause();
			}

		}

		pauseMethod();
	}

	private void play(SimpleEvent simpleEvent) {
		if (simpleEvent.isOn) {
			msn.noteOn(simpleEvent.pitch);
		} else {
			msn.noteOff(simpleEvent.pitch);
		}
	}

	private void pauseMethod() {
		System.out.println("lel-------------------------------------");
		if (paused) {
			synchronized (lock) {
				while (paused) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		playLoop();
	}

	@Override
	public void run() {
		pauseMethod();
	}

	public void add(double time, double duration, int pitch) {
		if (initialized) {

		}
	}
}
