package audioShit;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

import com.jsyn.midi.MidiConstants;

import display.NodeView;
import graphics.Drawable;
import helper.Empty;
import helper.NoteEvent;
import helper.SimpleEvent;
import helper.ValueContainer;

public class TimeLinePlayer extends Drawable implements Runnable {

	private static final double LOOK_BACK = 0.05;
	private static final double TIME_STEP = 0.005;
	private static final long TIME_STEP_MILIS = (long) (TIME_STEP * 1000);
	LinkedList<NoteEvent> notes;
	PriorityQueue<SimpleEvent> toPlay;
	private boolean paused = true;
	public Object lock = new Object();
	private NodeView corrView;

	private double currentTime;
	private long currentTimeMilis;

	//private boolean recording;

	private HashMap<Integer, SimpleEvent> currentlyRecordingPitches;
	private boolean startFromBeginning;
	public ValueContainer<Integer> pixelPerSecondCont;

	private Drawable offsetter;

	private int displayOctave;
	private int octaveHeight = 12 * 20;

	public TimeLinePlayer(int[] pos, String name, NodeView corrView) {
		super(pos, name);
		this.corrView = corrView;
		notes = new LinkedList<>();
		toPlay = new PriorityQueue<>();
		currentlyRecordingPitches = new HashMap<>();
		currentTime = 0;

		pixelPerSecondCont = new ValueContainer<Integer>(80);
		displayOctave = 4;

		addChild(offsetter = new Empty(new int[] { 0, displayOctave * octaveHeight }, "Empty"));

	}

	public void test1() {
		stop();
		try {
			Thread.sleep(600);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clear();
		displayOctave = 6;
		for (int i = 0; i < 100; i+=2) {
			int p = (6 * 12) + (int) (Math.random() * 5);
			double start = (double) i / 4;
			double length = 0.24;
			addNoteByPitch(p, start, length);
			addNoteByPitch(p + 3, start, length);
			addNoteByPitch(p + 3 + 4, start, length);
			p = (6 * 12) + (int) (Math.random() * 12);
			addNoteByPitch(p, start+0.25, length);
		}
	}

	public void tetris() {
		stop();
		clear();
		double length = 0.3;

		double x = 0;
		displayOctave = 5;

		addNote('E', 5, x * length, 2 * length);
		x++;
		x++;
		addNote('B', 4, x * length, length);
		x++;
		addNote('C', 5, x * length, length);
		x++;
		addNote('D', 5, x * length, 2 * length);
		x++;
		x++;
		addNote('C', 5, x * length, length);
		x++;
		addNote('B', 4, x * length, length);
		x++;
		addNote('A', 4, x * length, length);
	}

	private void clear() {
		offsetter.removeAllChildren();
		notes = new LinkedList<>();
		toPlay = new PriorityQueue<>();
	}

	public void draw(Graphics2D g, int x, int y) {
		offsetter.setX((int) -(currentTime * pixelPerSecondCont.x));
		offsetter.setY((displayOctave+1) * octaveHeight);


	}

	public void recordStart(char note, int octave) {
		int pitch = getPitch(note, octave);
		SimpleEvent startEvent;
		toPlay.add(startEvent = new SimpleEvent(pitch, true, currentTime));
		currentlyRecordingPitches.put(pitch, startEvent);

	}

	public void setOctaveToDisplay(int octave) {
		displayOctave = octave;
		offsetter.setY(-displayOctave * octaveHeight);
	}

	public void recordEnd(char note, int octave) {
		int pitch = getPitch(note, octave);
		if (currentlyRecordingPitches.get(pitch) != null) {
			SimpleEvent startEvent = currentlyRecordingPitches.get(pitch);
			SimpleEvent endEvent = new SimpleEvent(pitch, false, currentTime);
			toPlay.add(endEvent);

			NoteEvent nEvent;
			notes.add(nEvent = new NoteEvent(startEvent.time, currentTime, pitch, pixelPerSecondCont));

			nEvent.startEvent = startEvent;
			nEvent.endEvent = endEvent;

			offsetter.addChild(nEvent);
		}
	}

	private void addNoteByPitch(int pitch, double startTime, double length) {

		NoteEvent nEvent;
		notes.add(nEvent = new NoteEvent(startTime, startTime + length, pitch, pixelPerSecondCont));

		SimpleEvent startEvent;
		toPlay.add(startEvent = new SimpleEvent(pitch, true, startTime));
		SimpleEvent endEvent;
		toPlay.add(endEvent = new SimpleEvent(pitch, false, startTime + length));

		nEvent.startEvent = startEvent;
		nEvent.endEvent = endEvent;

		offsetter.addChild(nEvent);
	}

	public void addNote(char note, int octave, double startTime, double length) {

		int pitch = getPitch(note, octave);

		addNoteByPitch(pitch, startTime, length);

	}

	private void playLoop(long startTime) throws InterruptedException {
		PriorityQueue<SimpleEvent> played = new PriorityQueue<>();
		System.out.println(toPlay);

		if (!startFromBeginning) {
			currentTimeMilis = startTime;
			currentTime = currentTimeMilis * 0.001;
		} else {
			startFromBeginning = false;
		}

		System.out.println("Starting Loop at: " + currentTime + "     " + currentTimeMilis);
		long deltaTime = 0;
		while (!paused) {
			long lastTime = System.nanoTime();

			SimpleEvent next = toPlay.peek();

			if (next != null) {
				boolean closeEvent;
				do {
					closeEvent = false;
					if (next.time <= currentTime + TIME_STEP) {
						if (next.isOn == false || next.time >= currentTime - LOOK_BACK) {
							// TODO Perhaps wait 1 to 4 milis
							play(next.pitch, next.isOn);
						}
						played.add(toPlay.poll());
					}

					if (toPlay.peek() != null && toPlay.peek().time < currentTime + TIME_STEP) {
						closeEvent = true;
						next = toPlay.peek();
					}
				} while (closeEvent);
			}

			Thread.sleep(TIME_STEP_MILIS);
			deltaTime = System.nanoTime() - lastTime;
			currentTimeMilis += deltaTime / 1000000;
			currentTime = currentTimeMilis * 0.001;

		}
		toPlay.addAll(played);
		paused = true;
		pauseMethod(currentTimeMilis);
	}

	private void play(int pitch, boolean isOn) {
		if (isOn) {
			corrView.noteOn(0, pitch, 127);
		} else {
			corrView.noteOff(0, pitch, 127);
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

	public void stop() {
		paused = true;
		startFromBeginning = true;
		currentTime = 0;
		currentTimeMilis = 0;
		offsetter.setX(0);
	}

	private void pauseMethod(long pauseTime) {
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
		try {
			playLoop(pauseTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		pauseMethod(0);
	}

	private int getPitch(char note, int octave) {
		int pitch = Notes.OCTACE * (octave + 1);

		note = Character.toUpperCase(note);
		switch (note) {
		case 'A':
			pitch += Notes.BASE_A;
			break;
		case 'B':
			pitch += Notes.BASE_B;
			break;
		case 'C':
			pitch += Notes.BASE_C;
			break;
		case 'D':
			pitch += Notes.BASE_D;
			break;
		case 'E':
			pitch += Notes.BASE_E;
			break;
		case 'F':
			pitch += Notes.BASE_F;
			break;
		case 'G':
			pitch += Notes.BASE_G;
			break;
		default:
			break;
		}
		System.out
				.println(note + "   " + octave + "   " + pitch + "    " + MidiConstants.convertPitchToFrequency(pitch));
		return pitch;
	}

	public LinkedList<NoteEvent> getNotes() {
		// TODO Auto-generated method stub
		return notes;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	public int getOctaveHeight() {
		// TODO Auto-generated method stub
		return octaveHeight;
	}
}
