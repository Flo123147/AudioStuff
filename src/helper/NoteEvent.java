package helper;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

import audioShit.TimeLinePlayer;
import display.Draggable;

public class NoteEvent extends Draggable {
	public double start, end;
	public int pitch;

	public ValueContainer<Integer> pixelPerSecondCont;
	private int width, height;
	private int deltaY;

	public SimpleEvent startEvent, endEvent;

	public NoteEvent(double start, double end, int pitch, ValueContainer<Integer> pixelPerSec) {
		super(new int[] { (int) (start * pixelPerSec.x), pitch * 20 }, "NoteEvent");
		this.pixelPerSecondCont = pixelPerSec;

//		setY(0);

		this.width = (int) ((end - start) * pixelPerSecondCont.x);
		this.height = 20;

		this.start = start;
		this.end = end;
		this.pitch = pitch;

		setColor(Color.RED);
	}

	@Override
	protected void dragStart() {
		deltaY = 0;
		super.dragStart();
	}

	@Override
	protected void move(int dX, int dY, ControlHelper ch) {
		System.out.println("lel");
		start += dX / pixelPerSecondCont.x;
		end += dX / pixelPerSecondCont.x;

		startEvent.time = start;
		endEvent.time = end;

		deltaY += dY;

		if (deltaY >= 20) {
			dY = 20;
			deltaY = 0;
			pitch++;
		} else if (deltaY <= -20) {
			dY = -20;
			deltaY = 0;
			pitch--;
		} else {
			dY = 0;
		}

		startEvent.pitch = endEvent.pitch = this.pitch;

		super.move(dX, dY, ch);
	}

	@Override
	protected Shape getCollider() {
		return new Rectangle(getX(), getY(), width, height);
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		g.fillRect(x, y, width, height);
	}

}
