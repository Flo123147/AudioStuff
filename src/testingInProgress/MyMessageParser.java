package testingInProgress;

import com.jsyn.Synthesizer;
import com.jsyn.instruments.DualOscillatorSynthVoice;
import com.jsyn.midi.MessageParser;
import com.jsyn.unitgen.PassThrough;
import com.jsyn.unitgen.UnitVoice;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

public class MyMessageParser extends MessageParser {
	private static final double MAX_VELOCITY = 127;
	private MyVoiceAllocator voiceAllocator;

	public MyMessageParser(Synthesizer synth, PassThrough outputTo) {
		UnitVoice[] voices = new UnitVoice[8];
		for (int i = 0; i < voices.length; i++) {
			voices[i] = new DualOscillatorSynthVoice();
			System.out.println(voices[i].getUnitGenerator().getSynthesizer());
			synth.add(voices[i].getUnitGenerator());
			voices[i].getOutput().connect(outputTo.input);
		}
		voiceAllocator = new MyVoiceAllocator(voices);
	}

	@Override
	public void noteOff(int channel, int pitch, int velocity) {
		voiceAllocator.noteOff(pitch);
	}

	@Override
	public void noteOn(int channel, int pitch, int velocity) {
        double amplitude = velocity * (1.0 / MAX_VELOCITY);

		voiceAllocator.noteOn(pitch, amplitude);
	}
}
