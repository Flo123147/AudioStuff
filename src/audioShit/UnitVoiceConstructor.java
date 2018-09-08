package audioShit;

import java.util.HashMap;

import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.UnitGenerator;
import com.jsyn.unitgen.UnitVoice;
import com.jsyn.util.VoiceDescription;
import display.NodeView;
import display.Window;
import graphics.Drawable;
import nodeSystem.Node;

public class UnitVoiceConstructor {

	private boolean underCostruction = true;

	NodeView nodeView;

	private Window window;

	private UnitOutputPort circuitOut;

	HashMap<String, UnitGenerator> uGens;
	HashMap<String[], String[]> connections;

	public UnitVoiceConstructor(NodeView nodeView) {
		window = Window.widow;
		uGens = new HashMap<>();
		connections = new HashMap<>();
		this.nodeView = nodeView;
		this.circuitOut = new UnitOutputPort();
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
			super("Genaeral Temp Name", null);
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
			return null;
		}

		@Override
		public String getVoiceClassName() {
			// TODO Auto-generated method stub
			return null;
		}

	}
}
