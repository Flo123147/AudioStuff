package audioShit;

import java.util.HashMap;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.Circuit;
import com.jsyn.unitgen.UnitGenerator;
import com.jsyn.unitgen.UnitVoice;
import com.softsynth.shared.time.TimeStamp;

import unitGnerators.ControllerUnit;

public class MyUnitVoice extends Circuit implements UnitVoice {

	public static final String ConnectionFromTrigger = "connFromTrigger", ConnectionFromFreq = "connFromFreq";
	private ControllerUnit controller;
	private HashMap<String, UnitGenerator> myUGens;
//	private HashMap<String[], String[]> myConnections;

	@Override
	public UnitOutputPort getOutput() {
		add(controller = new ControllerUnit());
		return null;
	}

	@Override
	public void noteOn(double frequency, double amplitude, TimeStamp timeStamp) {
		controller.on(frequency, amplitude, timeStamp);

	}

	@Override
	public void noteOff(TimeStamp timeStamp) {
		controller.off(timeStamp);
	}

	public void setup(HashMap<String, UnitGenerator> uGens, HashMap<String[], String[]> connections)
			throws InstantiationException, IllegalAccessException {
		for (String key : uGens.keySet()) {
			UnitGenerator u = uGens.get(key).getClass().newInstance();
			add(u);
			myUGens.put(key, u);
		}
		for (String[] key : connections.keySet()) {
			UnitOutputPort connectionStart = null;

			switch (key[0]) {
			case ConnectionFromTrigger:
				connectionStart = controller.trigger;
				break;
			case ConnectionFromFreq:
				connectionStart = controller.freq;
				break;
			default:
				for (String key2 : myUGens.keySet()) {
					if(key[0] == key2) {
						connectionStart = (UnitOutputPort) myUGens.get(key[0]).getPortByName(key[1]);
					}
				}
				break;
			}

			if (connectionStart != null) {
				for (String key2 : myUGens.keySet()) {
					if (connections.get(key)[0] == key2) {
						UnitInputPort connectionTo = (UnitInputPort) myUGens.get(key2)
								.getPortByName(connections.get(key)[1]);
						if (connectionTo != null)
							connectionStart.connect(connectionTo);
					}
				}
			}
		}

	}

}
