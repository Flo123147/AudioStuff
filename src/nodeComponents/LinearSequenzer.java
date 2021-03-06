package nodeComponents;

import java.awt.Color;
import java.awt.Graphics2D;

import nodes.BasicSequenzerNode;

public class LinearSequenzer extends BaseSequenzerComponent {

	private int thingSize, thingyBorderWidth;

	public LinearSequenzer(String name, int maxWidth, int maxHeight, BasicSequenzerNode seqNode) {
		super(name, maxWidth, maxHeight, seqNode);

	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		super.draw(g, x, y);
		int currentStep = getStep();

		int nrOfTracks = tracks.size();
		int length = getLength();

		thingSize = Math.min(getWidth() / length, getHeight() / nrOfTracks);
		thingyBorderWidth = (int) (thingSize * 0.1);
		if (thingyBorderWidth == 0)
			thingyBorderWidth = 1;

		for (int i = 0; i < length; i++) {
			for (int t = 0; t < nrOfTracks; t++) {
				g.setColor(Color.BLACK);
				g.fillRoundRect(x + i * thingSize, y + t * thingSize, thingSize, thingSize, 2, 2);

				if (i == currentStep) {
					if (hasToTrigger(t, i)) {
						g.setColor(Color.CYAN);
					} else {
						g.setColor(Color.red);
					}
				} else {
					if (hasToTrigger(t, i)) {
						g.setColor(Color.darkGray);
					} else {
						g.setColor(Color.lightGray);
					}
				}
				g.fillRoundRect(x + i * thingSize + thingyBorderWidth, y + t * thingSize + thingyBorderWidth,
						thingSize - thingyBorderWidth * 2, thingSize - thingyBorderWidth * 2, 2, 2);

			}

		}

	}

	@Override
	public void itwasClicked(int mLx, int mLy) {
		int track = mLy / thingSize;
		int step = mLx / thingSize;
		try {
			toggle(track, step);
		} catch (IndexOutOfBoundsException e) {
		}
	}

}
