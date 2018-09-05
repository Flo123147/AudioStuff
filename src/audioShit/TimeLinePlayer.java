package audioShit;

import java.util.ArrayList;

import display.NodeView;
import display.Window;
import helper.SimpleEvent;

public class TimeLinePlayer implements Runnable {

	ArrayList<SimpleEvent> events;
	private int pointer;
	private boolean paused = true;
	public Object lock = new Object();
	private NodeView corrView;

	public TimeLinePlayer(NodeView corrView) {
		this.corrView = corrView;
		events = new ArrayList<>();
		pointer = 0;
		for (int i = 0; i < 100; i++) {
			int note = 30 + ((int) (Math.random() * 60));
			events.add(new SimpleEvent(0.2, note, true));

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
			play(events.get(pointer));

			try {
				Window.getSynth().sleepFor(events.get(pointer).sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(pointer);
			pointer++;
			if (pointer >= events.size()) {
				pointer = 0;
				pause();
			}

		}

		pauseMethod();
	}

	private void play(SimpleEvent simpleEvent) {
		if (simpleEvent.isOn) {
			corrView.noteOn(0, simpleEvent.pitch, 100);
		} else {
			corrView.noteOff(0, simpleEvent.pitch, 100);
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

	public void add(double time, int pitch) {
		//TODO  add new Events To event Thingy
	}
}
