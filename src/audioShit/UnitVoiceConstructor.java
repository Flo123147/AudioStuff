package audioShit;

import java.util.HashMap;
import com.jsyn.unitgen.UnitGenerator;
import com.jsyn.unitgen.UnitVoice;
import com.jsyn.util.VoiceDescription;
import display.NodeView;
import display.Window;
import graphics.Drawable;
import midi.MidiCotrollerNode;
import midi.MidiOutputNode;
import nodeSystem.ClonableNode;
import nodeSystem.Node;

public class UnitVoiceConstructor {

	private boolean underCostruction = true;

	NodeView nodeView;

	private Window window;

	HashMap<String, UnitGenerator> uGens;
	HashMap<String[], String[]> connections;

	private MidiCotrollerNode controllerNode;

	private MidiOutputNode outNode;

	public UnitVoiceConstructor(NodeView nodeView) {
		window = Window.widow;
		uGens = new HashMap<>();
		connections = new HashMap<>();
		this.nodeView = nodeView;

//		System.out.println("Generating some Stuff-------------------------------------");
//
//		UnitOscillator ossie;
//		uGens.put("Sine", ossie = new SineOscillator());
//		MyVarRateReader reader;
//		uGens.put("Reader", reader = new MyVarRateReader());
//
//		connections.put(new String[] { "Sine", ossie.output.getName() }, new String[] { MyUnitVoice.ConnectToOut, "" });
//		connections.put(new String[] { MyUnitVoice.ConnectionFromFreq, "" },
//				new String[] { "Sine", ossie.frequency.getName() });
//		connections.put(new String[] { MyUnitVoice.ConnectionFromTrigger, "" },
//				new String[] { "Reader", reader.trigger.getName() });
//		connections.put(new String[] { "Reader", reader.output.getName() },
//				new String[] { "Sine", ossie.amplitude.getName() });
	}

	public void connectUnit(String from, String portFrom, String to, String portTo) {
		connections.put(new String[] { from, portFrom }, new String[] { to, portTo });
	}

	public void connectUnit(String[] connectedFrom, String[] connectedTo) {
		connections.put(connectedFrom, connectedTo);
	}

	public void addNode(Drawable comp) {

		if (comp instanceof Node) {
			if (comp instanceof MidiCotrollerNode) {
				this.controllerNode = (MidiCotrollerNode) comp;
			} else if (comp instanceof MidiOutputNode) {
				this.outNode = (MidiOutputNode) comp;
			} else if (comp instanceof ClonableNode) {
				ClonableNode cn = (ClonableNode) comp;
				uGens.put(cn.getUnikeName(), cn.cloneUnitGen());
			}
		}
		System.out.println(uGens);
	}

	public VoiceDescription voiceDesc() {
		// TODO Auto-generated method stub
		return new MyVoiceDescription();
	}

	private class MyVoiceDescription extends VoiceDescription {

		public MyVoiceDescription() {
			super("Genaeral Temp Name", new String[] {});
			// TODO Auto-generated constructor stub
		}

		@Override
		public String[] getTags(int presetIndex) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public UnitVoice createUnitVoice() {
			MyUnitVoice uv = new MyUnitVoice();
			try {
				uv.setup(uGens, connections);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return uv;
		}

		@Override
		public String getVoiceClassName() {
			// TODO Auto-generated method stub
			return null;
		}

	}

}
