package audioShit;

import java.util.HashMap;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.Circuit;
import com.jsyn.unitgen.PassThrough;
import com.jsyn.unitgen.UnitGenerator;
import com.jsyn.unitgen.UnitVoice;
import com.softsynth.shared.time.TimeStamp;

import unitGnerators.ControllerUnit;

public class MyUnitVoice extends Circuit implements UnitVoice {

	public static final String ConnectFromController = "connFromcontroller", ConnectToOut = "connToOut";

	private ControllerUnit controller;
//	private String controllerName;
	private UnitOutputPort output;
//	private String outName;

	private HashMap<String, UnitGenerator> myUGens;

	private PassThrough outPS;

	public enum VoiceState {
		Idle, Holding, Releasing
	}

	public VoiceState state;

	public MyUnitVoice() {
		setEnabled(false);
		add(controller = new ControllerUnit());
		addPort(output = new UnitOutputPort());
		add(outPS = new PassThrough());

		outPS.output = this.output;

		myUGens = new HashMap<>();
	}

	@Override
	public UnitOutputPort getOutput() {
		return output;
	}

	@Override
	public void noteOn(double frequency, double amplitude, TimeStamp timeStamp) {
//		System.out.println("Note ON");
		controller.trigger(frequency, amplitude, timeStamp);
	}

	@Override
	public void noteOff(TimeStamp timeStamp) {
//		System.out.println("Note OFF");
		controller.off(timeStamp);
	}

	public void setup(HashMap<String, UnitGenerator> uGens, HashMap<String[], String[]> connections)
			throws InstantiationException, IllegalAccessException {
		for (String key : uGens.keySet()) {
			UnitGenerator u = uGens.get(key).getClass().newInstance();
			add(u);
			myUGens.put(key, u);
		}
		System.out.println("Start-----------------------");
//		System.out.println(myUGens.values());
//		System.out.println(connections);
		for (String[] key : connections.keySet()) {
			UnitOutputPort connectionStart = null;

			switch (key[0]) {
			case ConnectFromController:
				connectionStart = controller.trigger;
				break;

			default:
				for (String key2 : myUGens.keySet()) {
					if (key[0] == key2) {
						connectionStart = (UnitOutputPort) myUGens.get(key[0]).getPortByName(key[1]);
						break;
					}
				}
				break;
			}

			if (connectionStart != null) {
				UnitInputPort connectionTo = null;
				switch (connections.get(key)[0]) {

				case ConnectToOut:
					connectionTo = outPS.input;
					break;
				default:
					for (String key2 : myUGens.keySet()) {
						System.out.println(connections.get(key)[0] + "    " + connections.get(key)[1] +"     "+ key2 +"           " + (connections.get(key)[0] == key2) );

						if (connections.get(key)[0] == key2) {
							connectionTo = (UnitInputPort) myUGens.get(key2).getPortByName(connections.get(key)[1]);
							break;
						} 
					}
					break;

				}
				System.out.println(connectionTo);
				if (connectionTo != null) {
					System.out.println(connectionStart.getName() + "   connection to    " + connectionTo.getName());
					connectionStart.connect(connectionTo);
				}
			}

		}
		System.out.println("End-----------------------");

		setEnabled(true);
	}

}