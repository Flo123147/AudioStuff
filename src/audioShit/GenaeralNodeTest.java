package audioShit;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.ports.UnitPort;
import com.jsyn.ports.UnitVariablePort;
import com.jsyn.unitgen.UnitGenerator;

import oldEntries.AudioInEntry;
import oldEntries.AudioOutEntry;
import oldEntries.VariableInputEntry;
import oldNodeSystem.OldNode;
import oldNodeSystem.Slider;

public class GenaeralNodeTest extends OldNode {

	private UnitGenerator ug;

	public GenaeralNodeTest(int[] pos, String name, UnitGenerator ug) {
		super(pos, ug.getClass().getName());
		this.ug = ug;
		wind.getSynth().add(ug);
		ug.start();
		System.out.println(ug.getPorts());
		for (UnitPort port : ug.getPorts()) {
			System.out.println(port.getName());
			if (port instanceof UnitInputPort) {
				UnitInputPort uip = (UnitInputPort) port;
				Slider s;
				for (int i = 0; i < uip.getNumParts(); i++) {
					String eName = port.getName();
					if (uip.getNumParts() != 1) {
						eName += " " + i;
					}
					double minVal = -uip.getMaximum();
					double maxVal = uip.getMaximum();
					boolean isSlider = true;
					switch (port.getName()) {
					case "Frequency":
						minVal = 10;
						maxVal = 8000;
						break;
					case "Amplitude":
						minVal = -1;
						maxVal = 1;
						break;
					case "Input":
					case "InputA":
					case "InputB":
						break;
					default:
						isSlider = false;
						break;

					}
					System.out.println("                          " + uip.getMaximum() + "   " + uip.getMinimum());
					if (isSlider) {
						addEntry(s = new Slider(this, eName, minVal, maxVal));
						s.getRightPorts().output.connect(0, uip, i);
						s.setValue(uip.getDefault());
					} else {
						addEntry(new AudioInEntry(this, eName));
					}
				}
			} else if (port instanceof UnitOutputPort) {
				UnitOutputPort uop = (UnitOutputPort) port;

				for (int i = 0; i < uop.getNumParts(); i++) {
					AudioOutEntry a;
					String eName = uop.getName();
					if (uop.getNumParts() == 2) {
						if (i == 0) {
							eName = eName + " left";
						} else {
							eName = eName + " right";
						}
					}
					addEntry(a = new AudioOutEntry(this, eName));
					uop.connect(i, a.getRightPorts().input, 0);
				}
			} else if (port instanceof UnitVariablePort) {
				UnitVariablePort uvp = (UnitVariablePort) port;
				System.out.println(uvp);
				VariableInputEntry v;
				for (int i = 0; i < uvp.getNumParts(); i++) {
					String eName = port.getName();
					if (uvp.getNumParts() != 1) {
						eName += " " + i;
					}
					addEntry(v = new VariableInputEntry(this, eName));

				}
			}
		}
	}

}
