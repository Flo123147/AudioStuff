package testingInProgress;

import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.Circuit;
import com.jsyn.unitgen.SawtoothOscillator;
import com.jsyn.unitgen.SquareOscillator;
import com.jsyn.unitgen.TriangleOscillator;
import com.jsyn.unitgen.UnitOscillator;
import com.jsyn.unitgen.UnitVoice;
import com.jsyn.util.VoiceDescription;
import com.softsynth.shared.time.TimeStamp;

import display.MainView;

public class MySimpleTestVoice extends Circuit implements UnitVoice {

	private static MyVoiceDesc voiceDescription;
	private UnitOutputPort output;
	private UnitOscillator osci;

	public MySimpleTestVoice() {
		add(osci = new SawtoothOscillator());
		output = osci.output;
	}

	@Override
	public UnitOutputPort getOutput() {
		// TODO Auto-generated method stub
		return output;
	}

	@Override
	public void noteOn(double frequency, double amplitude, TimeStamp timeStamp) {
		osci.frequency.set(frequency);
		osci.amplitude.set(amplitude);
//		System.out.println(synthesisEngine.getUsage());
		System.out.println((System.nanoTime()-MainView.TEMPTIME)*0.000001);
	}

	@Override
	public void noteOff(TimeStamp timeStamp) {
		osci.amplitude.set(0);
		System.out.println((System.nanoTime()-MainView.TEMPTIME)*0.000001);


	}

	
	static class MyVoiceDesc extends VoiceDescription {

		public MyVoiceDesc() {
			super(MySimpleTestVoice.class.getSimpleName(), new String[] {});
			// TODO Auto-generated constructor stub
		}

		@Override
		public String[] getTags(int presetIndex) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public UnitVoice createUnitVoice() {
			// TODO Auto-generated method stub
			return new MySimpleTestVoice();
		}

		@Override
		public String getVoiceClassName() {
			// TODO Auto-generated method stub
			return null;
		}

	}
	public static VoiceDescription getVoiceDescription() {
		 if (voiceDescription == null) {
			voiceDescription = new MyVoiceDesc();
	        }
	        return voiceDescription;
	}

}
