package audioShit;

import com.jsyn.data.SegmentedEnvelope;
import com.jsyn.data.SequentialData;
import com.jsyn.unitgen.VariableRateMonoReader;
import com.softsynth.shared.time.TimeStamp;

import helper.Triggerable;
import oldEntries.AudioOutEntry;
import oldNodeSystem.ClonableNode;
import oldNodeSystem.OldNode;
import oldNodeSystem.Slider;
import unitGnerators.MyVarRateReader;

public class ReaderNode extends ClonableNode implements Triggerable {

	public AudioOutEntry out;
	public Slider amplitudeSlider;
	public Slider rateSlider;	
	public TriggerEntry trigger;
	
	private MyVarRateReader reader;

	private SegmentedEnvelope myTestThingy;

	public ReaderNode(int[] pos, String name) {
		super(pos, name);

		setUnitGen(reader = new MyVarRateReader());
		wind.addToSynth(reader);
		reader.start();

		addEntry(trigger = new TriggerEntry(this, "Trigger", this));
		connectInwards(trigger, reader.trigger);

		addEntry(out = new AudioOutEntry(this, "Output"));
//		reader.output.connect(out.getRightPorts().input);
		connectOutwards(reader.output, out);

		addEntry(amplitudeSlider = new Slider(this, "Amplitude", 0, 1));
		amplitudeSlider.setValue(reader.amplitude.getDefault());
//		amplitudeSlider.getRightPorts().output.connect(reader.amplitude);
		connectInwards(amplitudeSlider, reader.amplitude);

		addEntry(rateSlider = new Slider(this, "Rate", 0, 10));
		rateSlider.setValue(reader.rate.getDefault());
//		rateSlider.getRightPorts().output.connect(reader.rate);
		connectInwards(rateSlider, reader.rate);

		double[] data = { 0.02, 1, 0.3, 0.8, 0.5, 0.7, 0.7, 0.55, 0.9, 0.4, 1.1, 0.3, 1.3, 0.2, 1.5, 0.1, 1.9, 0

		};
		myTestThingy = new SegmentedEnvelope(data);

//		reader.setEnabled(false);

	}

	@Override
	public void triggerOn() {
		System.out.println("o----------n");
		reader.dataQueue.clear();
		reader.dataQueue.queue(myTestThingy, 0, 2);
		reader.dataQueue.queueLoop(myTestThingy, 0, 2);

	}

	@Override
	public void triggerHold() {

	}

	@Override
	public void triggerOff() {
		System.out.println("o----------------------f-f");
		reader.dataQueue.queue(myTestThingy, 2, 7);

	}
}