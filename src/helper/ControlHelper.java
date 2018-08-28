package helper;

import java.awt.event.MouseEvent;

public class ControlHelper {

	private boolean shift;

	private enum DragDirection {
		Horizontal, Vertical
	}

	private DragDirection dragDir;

	private int lastX, lastY;
	private int xOnPanel, yOnPanel;
	private int startXOnPanel, startYOnPanel;

	private int dX, dY;

	public ControlHelper() {
		// TODO Auto-generated constructor stub
	}

	public void update(MouseEvent e) {

		xOnPanel = e.getX();
		yOnPanel = e.getY();

		dX = e.getX() - lastX;
		dY = e.getY() - lastY;
		lastX = e.getX();
		lastY = e.getY();

		if (Math.abs(dY) > Math.abs(dX)) {
			dragDir = DragDirection.Vertical;
		} else {
			dragDir = DragDirection.Horizontal;
		}

		shift = e.isShiftDown();

//		System.out.println(dX + "   " + dY);
	}

	public int getDeltaX() {
		return dX;
	}

	public int getDeltaY() {
		return dY;
	}

	public boolean isShift() {
		// TODO Auto-generated method stub
		return shift;
	}

	public boolean draggingHorizontal() {
		return (dragDir == DragDirection.Horizontal);
	}

	public int getXOnPanel() {
		return xOnPanel;
	}

	public int getyOnPanel() {
		return yOnPanel;
	}

	public int getStartXOnPanel() {
		return startXOnPanel;
	}

	public void setStartXOnPanel(int starXOnScreen) {
		this.startXOnPanel = starXOnScreen;
	}

	public int getStartYOnPanel() {
		return startYOnPanel;
	}

	public void setStartYOnPanel(int startYOnScreen) {
		this.startYOnPanel = startYOnScreen;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
