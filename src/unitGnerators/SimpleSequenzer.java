package unitGnerators;

import java.util.LinkedList;

import com.jsyn.ports.UnitOutputPort;
import nodeComponents.BaseSequenzerComponent;

public class SimpleSequenzer extends MyBaseUnit {

	LinkedList<LinkedList<Boolean>> tracks;

	private int size, length;

	private boolean isPulse;

	public BaseSequenzerComponent seqComp;

	private int step;

	public UnitOutputPort[] outTrigger;
	public OutTriggerUnit[] outTriggerUnits;

	LinkedList<OutTriggerUnit> turnOffNextStep;

	public static final int MAX_TRACKS = 20;

	public SimpleSequenzer() {
		isPulse = true;
		tracks = new LinkedList<>();
		size = 0;
		length = 4;
		step = -1;
		outTrigger = new UnitOutputPort[MAX_TRACKS];
		outTriggerUnits = new OutTriggerUnit[MAX_TRACKS];
		turnOffNextStep = new LinkedList<>();
	}

	@Override
	protected void baseTrigger() {

		for (OutTriggerUnit tu : turnOffNextStep) {
			tu.constantOff();
		}
		turnOffNextStep.clear();

		step++;
		if (step >= length) {
			step = 0;
		}
		for (int i = 0; i < size; i++) {
			if (hasToTrigger(i, step)) {
				System.out.println(i + "   " + step);
				if (isPulse) {
					outTriggerUnits[i].pulse();
				} else {
					outTriggerUnits[i].constantOn();
					turnOffNextStep.add(outTriggerUnits[i]);
				}
			}
		}
	}

	public void addTrack() {
		LinkedList<Boolean> newTrack;
		tracks.add(newTrack = new LinkedList<Boolean>());

		OutTriggerUnit tu = new OutTriggerUnit();
		tu.output.setName("Trigger " + (size + 1));
		add(tu);

		outTriggerUnits[size] = tu;

		UnitOutputPort uop = tu.output;
		addPortRuntime(uop);
		outTrigger[size] = uop;

		node.setAutoPorts(new String[] { uop.getName() });
		size++;

		for (int i = 0; i < getLength(); i++) {
			newTrack.add(false);
		}

	}

	public void setPulse(boolean isPulse) {
		this.isPulse = isPulse;
	}

	public void setSeqComponent(BaseSequenzerComponent sequenzerComponent) {
		this.seqComp = sequenzerComponent;
		sequenzerComponent.tracks = this.tracks;
	}

	public int getLength() {
		// TODO Auto-generated method stub
		return length;
	}

	public int getStep() {
		// TODO Auto-generated method stub
		return step;
	}

	public void lengthPlus() {
		length++;
		if (length > MAX_TRACKS) {
			length = MAX_TRACKS;
		}
		for (LinkedList<Boolean> track : tracks) {
			if (track.size() < length)
				track.add(false);
		}
	}

	public void lengthMinus() {
		length--;
		if (length <= 0)
			length = 1;
	}

	public boolean hasToTrigger(int t, int i) {
		return tracks.get(t).get(i);
	}

}
