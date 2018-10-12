package testingInProgress;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;

import com.jsyn.devices.javasound.MidiDeviceTools;
import com.jsyn.instruments.DualOscillatorSynthVoice;
import com.jsyn.midi.MessageParser;
import com.jsyn.midi.MidiSynthesizer;
import com.jsyn.unitgen.PassThrough;
import com.jsyn.unitgen.UnitVoice;
import com.jsyn.util.MultiChannelSynthesizer;
import com.jsyn.util.VoiceAllocator;
import com.softsynth.math.AudioMath;
import com.softsynth.shared.time.TimeStamp;

import display.Window;
import helper.ControlHelper;
import nodeSystem.GeneratorNode;
import nodeSystem.Node;

public class MidiSynthNode extends GeneratorNode {

	private MyMessageParser myParser;
	private MidiDevice keyboard;

	
	public MidiSynthNode(int[] pos) {
		super(pos, "MidiSynth?");
		PassThrough ps;
		myParser = new MyMessageParser(synth,ps = new PassThrough());
		setUnitGenerator(ps);
	

		keyboard = MidiDeviceTools.findKeyboard();
		System.out.println(keyboard);
		Receiver receiver = new MyReceiver();

		if (keyboard != null) {
			try {
				keyboard.open();
				keyboard.getTransmitter().setReceiver(receiver);
			} catch (MidiUnavailableException e) {
				e.printStackTrace();
			}

		}else {
			setName("NO KEYBOARD");
		}

	}

	class MyReceiver implements Receiver {

		@Override
		public void close() {
			System.out.println("Im Out lol");
		}

		@Override
		public void send(MidiMessage message, long time) {
			long start = System.nanoTime();
			myParser.parse(message.getMessage());
			System.out.println((System.nanoTime() - start) * 0.000001);
		}

	}

	@Override
	public void click(String button, ControlHelper ch) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void control(String name) {
		// TODO Auto-generated method stub

	}
}
