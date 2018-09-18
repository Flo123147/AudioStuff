package audioShit;

import java.util.HashMap;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.Circuit;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.UnitGenerator;
import com.jsyn.unitgen.UnitVoice;
import com.softsynth.shared.time.TimeStamp;

import testingInProgress.MyPassTrough;
import unitGnerators.ControllerUnit;
import unitGnerators.MyOscillator;
import unitGnerators.MyVarRateReader;

public class MyUnitVoice extends Circuit implements UnitVoice {

	public static final String ConnectFromController = "connFromcontroller", ConnectToOut = "connToOut";

	private ControllerUnit controller;
//	private String controllerName;
	private UnitOutputPort output;
//	private String outName;

	private HashMap<String, UnitGenerator> myUGens;

	private MyPassTrough outPS;

//	public enum VoiceState {
//		Idle, Holding, Releasing
//	}

//	public VoiceState state;

	SineOscillator sine;

	private MyVarRateReader reader;

	public MyUnitVoice(HashMap<String, UnitGenerator> uGens, HashMap<String[], String[]> connections) {
		setEnabled(false);
		add(controller = new ControllerUnit());
		add(outPS = new MyPassTrough());
		addPort(output = outPS.output);

		myUGens = new HashMap<>();

//		test();

		try {
			setup(uGens, connections);
//			test(uGens);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void test(HashMap<String, UnitGenerator> uGens) throws InstantiationException, IllegalAccessException {
		System.out.println(uGens.values());
		for (UnitGenerator g : uGens.values()) {
			if (g instanceof MyVarRateReader) {
				System.out.println("ReaderFound");
				MyVarRateReader mvrr = (MyVarRateReader) g;
				reader = mvrr.getClass().newInstance();
			} else if (g instanceof MyOscillator) {
				System.out.println("OssieFound");
				MyOscillator mo = (MyOscillator) g;
				sine = mo.getClass().newInstance();
			}
		}

		add(sine);
		sine.output.connect(outPS.input);

		controller.freq.connect(sine.frequency);

		add(reader);
		reader.output.connect(sine.amplitude);

		controller.trigger.connect(reader.trigger);

		setEnabled(true);
	}

	@Override
	public UnitOutputPort getOutput() {
		return output;
	}

	@Override
	public void noteOn(double frequency, double amplitude, TimeStamp timeStamp) {
		System.out.println("Note ON");
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
		System.out.println(myUGens.values());

		for (String[] a : connections.keySet()) {
			String[] b = connections.get(a);
			System.out.println(a[0] + " " + a[1] + "   " + b[0] + " " + b[1]);
		}

		for (String[] key : connections.keySet()) {
			System.out.println("--------------------");
			UnitOutputPort startConnectionFrom = null;
			String connectFrom = key[0];
			String connectFromPart = key[1];

			System.out.println("Finding start part: " + connectFrom + "    " + connectFromPart);

			switch (connectFrom) {
			case ConnectFromController:
				startConnectionFrom = (UnitOutputPort) controller.getPortByName(connectFromPart);
				break;

			default:
				for (String key2 : myUGens.keySet()) {
					if (connectFrom.compareTo(key2) == 0) {
						startConnectionFrom = (UnitOutputPort) myUGens.get(connectFrom).getPortByName(connectFromPart);
						break;
					}
				}
				break;
			}
			System.out.println("Found Start? " + startConnectionFrom);
			if (startConnectionFrom != null) {
				UnitInputPort makeconnectionTo = null;

				String connectTo = connections.get(key)[0];
				String connectToPart = connections.get(key)[1];

				
				System.out.println("Finding end part: " + connectTo + "    " + connectToPart);

				switch (connectTo) {

				case ConnectToOut:
//					System.out.println("connToOut");
					makeconnectionTo = outPS.input;
					break;
				default:
					for (String key2 : myUGens.keySet()) {

//						System.out.println(connectTo + "    " + connectToPart + "     " + key2 + "           "
//								+ (connectTo.compareTo(key2)));

						if (connectTo.compareTo(key2) == 0) {
							makeconnectionTo = (UnitInputPort) myUGens.get(key2).getPortByName(connectToPart);
//							System.out.println("FOUND");
							break;
						}
					}
					break;

				}
				System.out.println("Found End? " + makeconnectionTo);
				if (makeconnectionTo != null) {
					System.out.println("connecting: " + connectFrom + " part " + connectFromPart + " to: " + connectTo
							+ " part " + connectToPart);
//					System.out.println(connectionStart.getName() + "   connection to    " + connectionTo.getName());
					startConnectionFrom.connect(makeconnectionTo);
				} else {
					System.out.println("Fail");
				}
			}

		}
		System.out.println("MyUnitVoice constructed-----------------------------------------");

		setEnabled(true);
	}

}