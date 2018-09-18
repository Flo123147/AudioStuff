package oldNodeSystem;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.UnitGenerator;

import audioShit.AudioOutEntry;
import display.NodeView;
import helper.ImHelping;

public abstract class ClonableNode extends OldNode {

	protected UnitGenerator unitGen;
	protected int uid;



	public ClonableNode(int[] pos, String name) {
		super(pos, name);
		uid = ImHelping.getNextUid();
	}

	@Override
	public void removeConnection(Entry entry, Entry toRemove) {
		NodeView nv = (NodeView) correspondingView;
		nv.voiceConstructor.unConnectUnit(entry.connectedFrom);

		super.removeConnection(entry, toRemove);
	}

	@Override
	public void newConnection(Entry entry, Entry toAdd) {

		NodeView nv = (NodeView) correspondingView;
		nv.voiceConstructor.connectUnit(entry.connectedFrom, toAdd.connectedTo);
		super.newConnection(entry, toAdd);
	}

	public void connectOutwards(UnitOutputPort portOnUGen, Entry entry) {
		connectOutwards(0, portOnUGen, entry, 0);
	}

	public void connectOutwards(int part, UnitOutputPort portOnUGen, Entry entry, int part2) {
		entry.connectedFrom = new String[] { getUnikeName(), portOnUGen.getName() };
		connect(portOnUGen, part, entry.rightPorts.input, part2);
	}

	public void connectInwards(Entry entry, UnitInputPort portOnUGen) {
		connectInwards(0, entry, portOnUGen, 0);
	}

	public void connectInwards(int part, Entry entry, UnitInputPort portOnUGen, int part2) {
		entry.connectedTo = new String[] { getUnikeName(), portOnUGen.getName() };
		connect(entry.rightPorts.output, part, portOnUGen, part2);
	}

	private void connect(UnitOutputPort output, int i, UnitInputPort input, int j) {
		output.connect(i, input, j);
	}

	protected void setUnitGen(UnitGenerator unitGen) {
		this.unitGen = unitGen;
	}

	public UnitGenerator cloneUnitGen() {
		if (unitGen == null)
			return null;
		try {
			UnitGenerator uGen = unitGen.getClass().newInstance();

			return uGen;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

//	@Override
//	public void newConnection(Entry entry, Entry toAdd) {
////		outConnections.add(new Entry[] { entry, toAdd });
//		super.newConnection(entry, toAdd);
//	}

//	@Override
//	public void removeConnection(Entry entry, Entry toRemove) {
////		Entry[] removeThis = null;
////		for (Entry[] entries : outConnections) {
////
////			if (entries[0] == entry && entries[1] == toRemove) {
////				removeThis = entries;
////				break;
////			}
////		}
////
////		if (removeThis != null)
////			outConnections.remove(removeThis);
//
//		super.removeConnection(entry, toRemove);
//	}

	public String getUnikeName() {
		System.out.println(uid + "   " + unitGen);
		return uid + "-" + unitGen.getClass().getSimpleName();
	}

}
