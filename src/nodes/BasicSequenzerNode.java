package nodes;

import nodeComponents.BaseSequenzerComponent;
import nodeComponents.ButtonComponent;
import nodeComponents.DisplayComponent;
import nodeComponents.LinearSequenzer;
import helper.ControlHelper;
import nodeSystem.Node;

public class BasicSequenzerNode extends Node {

	public static final String NAME_ADD_TRACK = "Add Track";
	public static final String NAME_REMOVE_TRACK = "Remove Track";
	public static final String NAME_MODE_TOGGLE = "Toggle Mode (UNUSED)";
	public static final String NAME_INCREASE = "+";
	public static final String NAME_DECREASE = "-";
	private static final int START_LENGTH = 1;
	private static final String TRIGGER_NAME = "Trigger";

	private boolean isPulse;
	private DisplayComponent modeDisp, lenghtDisplay;
	private BaseSequenzerComponent sequenzer;

	public BasicSequenzerNode(int[] pos) {
		super(pos, "Sequenzer");

		addNodeComponent(NAME_ADD_TRACK, new ButtonComponent(NAME_ADD_TRACK, 100, 20, this), 0, 0);
		addNodeComponent(NAME_MODE_TOGGLE, new ButtonComponent(NAME_MODE_TOGGLE, 100, 20, this), 0, 1);
		addNodeComponent("ModeDisplay", modeDisp = new DisplayComponent("Display", 100, 20), 5, 1);
		addNodeComponent("LenthDisplay", lenghtDisplay = new DisplayComponent("Display", 60, 20), 6, 0);

		addNodeComponent(NAME_ADD_TRACK, new ButtonComponent(NAME_INCREASE, 20, 20, this), 5, 0);
		addNodeComponent(NAME_ADD_TRACK, new ButtonComponent(NAME_DECREASE, 20, 20, this), 9, 0);

		addNodeComponent("Sequenzer", sequenzer = new LinearSequenzer("Sequenzer", 250, 200, this), 0, 2);

		addControlInPort("Step");

		modeDisp.setText("Pulse");
		lenghtDisplay.setText("" + START_LENGTH);

		for (int i = 0; i < 4; i++) {
			addTrack();
		}
	}

	public void step() {
		sequenzer.step();
	}

	private void addTrack() {
		if (sequenzer.addTrack())
			addControlOutPort(TRIGGER_NAME + " " + sequenzer.getTrackCount());
	}

	public void triggerTrack(int i) {
		controlPort(TRIGGER_NAME + " " + (i+1));
	}

	@Override
	public void click(String button, ControlHelper ch) {
//		System.out.println(button);
		switch (button) {
		case NAME_ADD_TRACK:
			addTrack();
			break;

		case NAME_MODE_TOGGLE:
			if (isPulse) {
				isPulse = false;
				modeDisp.setText("Constant");
				sequenzer.setConstant();
			} else {
				isPulse = true;
				modeDisp.setText("Pulse");
				sequenzer.setPulse();
			}

			break;
		case NAME_INCREASE:
			sequenzer.lengthPlus();
			lenghtDisplay.setText("" + sequenzer.getLength());

			break;
		case NAME_DECREASE:
			sequenzer.lengthMinus();
			lenghtDisplay.setText("" + sequenzer.getLength());

			break;
		default:
			break;
		}
	}

	@Override
	protected void control(String name) {
		if (name.equals("Step")) {
			step();
		}

	}

}