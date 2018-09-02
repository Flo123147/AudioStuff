package testingInProgress;

import com.jsyn.midi.MidiSynthesizer;

import midi.MidiOutputNode;

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
				MidiOutputNode.getSynth().sleepFor(0.5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ms.noteOff(0, i, 100);
		}
	}

}
