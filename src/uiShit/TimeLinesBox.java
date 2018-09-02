package uiShit;

import java.util.LinkedList;

public class TimeLinesBox extends UiBaseElement {

	private LinkedList<BasicTimeLine> timeLines;

	public TimeLinesBox(UiBorder[] borders) {
		super(borders);
		timeLines = new LinkedList<>();
	}

	@Override
	void updateCoords() {
		for (BasicTimeLine bt : timeLines) {
			int totalBorder = borderWidth + spacing.x;
			bt.setWidth(getWidth() - 1 - 2 * totalBorder);
			bt.setX(totalBorder);
			bt.setY(totalBorder);
		}
		super.updateCoords();
	}

	public void addTimeLine() {

		BasicTimeLine child;
		addChild(child = new BasicTimeLine(new int[] { 0, 0 }, "Test", this));
		child.setWidth(getWidth());
		timeLines.add(child);
	}

	public void reAddTimeLine(BasicTimeLine basicTimeLine) {
		addChild(basicTimeLine);
	}

}
