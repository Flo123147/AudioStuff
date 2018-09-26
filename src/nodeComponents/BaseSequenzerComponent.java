package nodeComponents;

import java.awt.Rectangle;
import java.awt.Shape;
import java.util.LinkedList;

import helper.Clickable;
import helper.ControlHelper;
import unitGnerators.SimpleSequenzer;

public abstract class BaseSequenzerComponent extends NodeComponent implements Clickable {

	protected SimpleSequenzer seq;
	public LinkedList<LinkedList<Boolean>> tracks;

	public BaseSequenzerComponent(String name, int maxWidth, int maxHeight, SimpleSequenzer simpS) {
		super(name, maxWidth, maxHeight);
		this.seq = simpS;
		seq.setSeqComponent(this);
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

}
