package display;

import java.util.LinkedList;

import oldNodeSystem.ButtonEntry;
import oldNodeSystem.OldNode;
import uiShit.Button;
import uiShit.ClickReciever;
import uiShit.UiBaseElement;
import uiShit.UiBorder;

public class MainControl extends OldNode implements ClickReciever {

	private static final String PLAY = "Play", STOP = "Stop", ADD_TRACK = "Add Track", PAUSE_UNPAUSE = "Pause/Unpause",
			ADD_RYTHM = "Add Rythm";

	private boolean isPaused = true, isPlaying = false;

	public MainControl(Window wind) {
		super(new int[] { 0, 0 }, "Main Control");
		LinkedList<Button> butts = new LinkedList<>();

		butts.add(new Button(new int[] { 0, 0 }, PLAY, this));
		butts.add(new Button(new int[] { 0, 0 }, STOP, this));
		butts.add(new Button(new int[] { 0, 0 }, ADD_TRACK, this));
		butts.add(new Button(new int[] { 0, 0 }, ADD_RYTHM, this));
		butts.add(new Button(new int[] { 0, 0 }, PAUSE_UNPAUSE, this));

		addEntry(new ButtonEntry(this, this, butts,"Controls"));
	}

	@Override
	public void click(String button) {
		switch (button) {
		case PLAY:

			break;
		case STOP:

			break;
		case ADD_TRACK:

			break;
		case PAUSE_UNPAUSE:
			if (isPaused) {
				isPaused = false;
			} else {
				isPaused = true;
			}
			break;
		case ADD_RYTHM:

			break;
		default:
			break;
		}

	}

}
