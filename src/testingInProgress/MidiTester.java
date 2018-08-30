package testingInProgress;

import com.jsyn.midi.MidiSynthesizer;

import audioShit.OutputNode;

public class MidiTester implements Runnable {

	public MidiTester(MidiSynthesizer ms) {
		this.ms = ms;
	}

	public MidiSynthesizer ms;

	@Override
	public void run() {

		for (int i = 12; i <= 94; i++) {
			ms.noteOn(0, i, 100);
			try {
				OutputNode.getSynth().sleepFor(0.5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ms.noteOff(0, i, 100);
		}
	}

}
