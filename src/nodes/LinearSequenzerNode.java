package nodes;

import nodeComponents.ButtonComponent;
import nodeComponents.DisplayComponent;
import nodeComponents.LinearSequenzer;
import helper.ControlHelper;
import nodeSystem.Node;
import unitGnerators.SimpleSequenzer;

public class LinearSequenzerNode extends Node {

	public static final String NAME_ADD_TRACK = "Add Track";
	public static final String NAME_REMOVE_TRACK = "Remove Track";
	public static final String NAME_MODE_TOGGLE = "Toggle Mode";
	public static final String NAME_INCREASE = "+";
	public static final String NAME_DECREASE = "-";
	private static final int START_LENGTH = 4;

	private boolean isPulse;
	private DisplayComponent modeDisp, lenghtDisplay;
	private SimpleSequenzer simpS;

	public LinearSequenzerNode(int[] pos) {
		super(pos, "Sequenzer");

		setUnitGenerator(simpS = new SimpleSequenzer());

		addNodeComponent(NAME_ADD_TRACK, new ButtonComponent(NAME_ADD_TRACK, 100, 20, this), 0, 0);
		addNodeComponent(NAME_MODE_TOGGLE, new ButtonComponent(NAME_MODE_TOGGLE, 100, 20, this), 0, 1);
		addNodeComponent("ModeDisplay", modeDisp = new DisplayComponent("Display", 100, 20), 5, 1);
		addNodeComponent("LenthDisplay", lenghtDisplay = new DisplayComponent("Display", 60, 20), 6, 0);

		addNodeComponent(NAME_ADD_TRACK, new ButtonComponent(NAME_INCREASE, 20, 20, this), 5, 0);
		addNodeComponent(NAME_ADD_TRACK, new ButtonComponent(NAME_DECREASE, 20, 20, this), 9, 0);

		addNodeComponent("Sequenzer", new LinearSequenzer("Sequenzer", 250, 100, simpS), 0, 2);

		modeDisp.setText("Pulse");
		lenghtDisplay.setText("" + START_LENGTH);

		for (int i = 0; i < 4; i++) {
			addTrack();
		}
	}

	private void addTrack() {
		simpS.addTrack();
	}

	@Override
	public void click(String button, ControlHelper ch) {
		System.out.println(button);
		switch (button) {
		case NAME_ADD_TRACK:
			addTrack();
			break;

		case NAME_MODE_TOGGLE:
			if (isPulse) {
				isPulse = false;
				modeDisp.setText("Constant");
				simpS.setPulse(false);
			} else {
				isPulse = true;
				modeDisp.setText("Pulse");
				simpS.setPulse(true);
			}

			break;
		case NAME_INCREASE:
			simpS.lengthPlus();
			lenghtDisplay.setText("" + simpS.getLength());

			break;
		case NAME_DECREASE:
			simpS.lengthMinus();
			lenghtDisplay.setText("" + simpS.getLength());

			break;
		default:
			break;
		}
	}

	@Override
	public void event(String event) {
		// TODO Auto-generated method stub

	}

}
