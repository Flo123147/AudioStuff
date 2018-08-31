package audioShit;

import com.jsyn.data.SegmentedEnvelope;
import com.jsyn.data.SequentialData;
import com.jsyn.unitgen.VariableRateMonoReader;
import com.softsynth.shared.time.TimeStamp;

import nodeSystem.Node;
import nodeSystem.Slider;

public class ReaderNode extends Node implements Triggerable {

	private AudioOutEntry out;
	private VariableRateMonoReader reader;
	private Slider amplitudeSlider;
	private Slider rateSlider;
	private TriggerEntry trigger;
	private SegmentedEnvelope myTestThingy;

	public ReaderNode(int[] pos, String name) {
		super(pos, name);

		reader = new VariableRateMonoReader();
		MidiOutputNode.getSynth().add(reader);
		reader.start();
		
		addEntry(trigger = new TriggerEntry(this, "Trigger", this));

		addEntry(out = new AudioOutEntry(this, "Output"));
		reader.output.connect(out.getRightPorts().input);

		addEntry(amplitudeSlider = new Slider(this, "Amplitude", 0, 1));
		amplitudeSlider.setValue(reader.amplitude.getDefault());
		amplitudeSlider.getRightPorts().output.connect(reader.amplitude);

		addEntry(rateSlider = new Slider(this, "Rate", 0, 10));
		rateSlider.setValue(reader.rate.getDefault());
		rateSlider.getRightPorts().output.connect(reader.rate);

		double[] data = { 
				0.02, 1,
				0.3, 0.8,
				0.5, 0.7,
				0.7, 0.55,
				0.9, 0.4,
				1.1, 0.3,
				1.3, 0.2, 
				1.5, 0.1, 
				1.9, 0

		};
		myTestThingy = new SegmentedEnvelope(data);

	}

	@Override
	public void triggerOn() {
		System.out.println("o----------n");
		reader.dataQueue.clear();
		reader.dataQueue.queue(myTestThingy,0,2);
		reader.dataQueue.queueLoop(myTestThingy,0,2);

	}

	@Override
	public void triggerHold() {


	}
	@Override
	public void triggerOff() {
		System.out.println("o----------------------f-f");
		reader.dataQueue.queue(myTestThingy, 2,7);

	}

	
}