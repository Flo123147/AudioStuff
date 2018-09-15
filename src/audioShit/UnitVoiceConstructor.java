package audioShit;

import java.util.HashMap;

import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.ImpulseOscillator;
import com.jsyn.unitgen.SawtoothOscillator;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.SquareOscillator;
import com.jsyn.unitgen.TriangleOscillator;
import com.jsyn.unitgen.UnitGenerator;
import com.jsyn.unitgen.UnitOscillator;
import com.jsyn.unitgen.UnitVoice;
import com.jsyn.unitgen.VariableRateMonoReader;
import com.jsyn.util.VoiceDescription;
import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry;

import display.NodeView;
import display.Window;
import graphics.Drawable;
import nodeSystem.Node;

public class UnitVoiceConstructor {

	private boolean underCostruction = true;

	NodeView nodeView;

	private Window window;

	HashMap<String, UnitGenerator> uGens;
	HashMap<String[], String[]> connections;

	public UnitVoiceConstructor(NodeView nodeView) {
		window = Window.widow;
		uGens = new HashMap<>();
		connections = new HashMap<>();
		this.nodeView = nodeView;

		System.out.println("Generating some Stuff-------------------------------------");

		UnitOscillator ossie;
		uGens.put("Sine", ossie = new SineOscillator());
		MyVarRateReader reader;
		uGens.put("Reader", reader = new MyVarRateReader());

		connections.put(new String[] { "Sine", ossie.output.getName() }, new String[] { MyUnitVoice.ConnectToOut, "" });
		connections.put(new String[] { MyUnitVoice.ConnectionFromFreq, "" },
				new String[] { "Sine", ossie.frequency.getName() });
		connections.put(new String[] { MyUnitVoice.ConnectionFromTrigger, "" },
				new String[] { "Reader", reader.trigger.getName() });
		connections.put(new String[] { "Reader", reader.output.getName() },
				new String[] { "Sine", ossie.amplitude.getName() });
	}

	public void addNode(Drawable comp) {
		if (comp instanceof Node) {
			System.out.println("yaaay");
		}
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
