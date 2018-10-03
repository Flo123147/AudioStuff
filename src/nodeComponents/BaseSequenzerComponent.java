package nodeComponents;

import java.awt.Rectangle;
import java.awt.Shape;
import java.util.LinkedList;

import helper.Clickable;
import helper.ControlHelper;
import nodes.BasicSequenzerNode;

public abstract class BaseSequenzerComponent extends NodeComponent implements Clickable {

	protected BasicSequenzerNode nodeSeq;
	public LinkedList<LinkedList<Boolean>> tracks;

	public static int MAX_TRACKS = 10, MAX_LENGTH = 20;

	private int nrOfTracks;
	private int length;

	private int currentStep;
	private boolean isPulse;

	public BaseSequenzerComponent(String name, int maxWidth, int maxHeight, BasicSequenzerNode nodeSeq) {
		super(name, maxWidth, maxHeight);
		this.nodeSeq = nodeSeq;
		tracks = new LinkedList<>();

		nrOfTracks = 0;
		length = 1;

		currentStep = -1;
	}

	@Override
	public void init() {
		correspondingView.addClickable(this);
		super.init();
	}

	public void toggle(int track, int step) throws IndexOutOfBoundsException {
		tracks.get(track).set(step, !tracks.get(track).get(step));
	}

	@Override
	public Shape getCollider() {
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void clicked(ControlHelper ch) {
		int mLx = ch.getStartXOnPanel() - getX();
		int mLy = ch.getStartYOnPanel() - getY();
		itwasClicked(mLx, mLy);
	}

	public abstract void itwasClicked(int mLx, int mLy);

	public boolean hasToTrigger(int t, int i) {
		return tracks.get(t).get(i);
	}

	public void addTrack() {
		if (nrOfTracks < MAX_TRACKS) {
			LinkedList<Boolean> newTrack;
			tracks.add(newTrack = new LinkedList<>());
			nrOfTracks++;
			for (int i = 0; i < length; i++) {
				newTrack.add(false);
			}
		}
	}

	public void lengthPlus() {
		if (length < MAX_LENGTH) {
			length++;

			for (LinkedList<Boolean> track : tracks) {
				while (track.size() < length) {
					track.add(false);
				}
			}
		}

	}

	public void lengthMinus() {
		if (length > 1) {
			length--;
		}

	}

	public void setConstant() {
		isPulse = false;
	}

	public void setPulse() {
		isPulse = true;

	}

	public int getLength() {
		// TODO Auto-generated method stub
		return length;
	}

	public int getStep() {
		return currentStep;
	}

}
