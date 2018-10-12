package nodes;

import nodeComponents.BaseSequenzerComponent;
import nodeComponents.ButtonComponent;
import nodeComponents.DisplayComponent;
import nodeComponents.LinearSequenzer;
import helper.ControlHelper;
import nodeSystem.Node;

public class BasicSequenzerNode extends Node {

	public static final String NAME_ADD_TRACK = "Add Track";
	public static final String NAME_INCREASE = "+";
	public static final String NAME_DECREASE = "-";
	private static final int START_LENGTH = 5,START_TRACKS = 4;
	private static final String TRIGGER_NAME = "Trigger";

	private DisplayComponent lenghtDisplay;
	private BaseSequenzerComponent sequenzer;

	public BasicSequenzerNode(int[] pos) {
		super(pos, "Sequenzer");

		addNodeComponent(NAME_ADD_TRACK, new ButtonComponent(NAME_ADD_TRACK, 100, 20, this), 0, 0);
		addNodeComponent("LenthDisplay", lenghtDisplay = new DisplayComponent("Display", 60, 20), 6, 0);

		addNodeComponent(NAME_ADD_TRACK, new ButtonComponent(NAME_INCREASE, 20, 20, this), 5, 0);
		addNodeComponent(NAME_ADD_TRACK, new ButtonComponent(NAME_DECREASE, 20, 20, this), 9, 0);

		addNodeComponent("Sequenzer", sequenzer = new LinearSequenzer("Sequenzer", 250, 200, this), 0, 2);

		addControlInPort("Step");


		lenghtDisplay.setText("" + START_LENGTH);

		for (int i = 0; i < START_TRACKS; i++) {
			addTrack();
		}
		for (int i = 1; i < START_LENGTH; i++) {
			sequenzer.lengthPlus();
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
